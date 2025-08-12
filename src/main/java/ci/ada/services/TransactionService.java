package ci.ada.services;

import ci.ada.dao.AccountDao;
import ci.ada.dao.TransactionDao;
import ci.ada.models.Account;
import ci.ada.models.Transaction;
import ci.ada.observer.TransactionObserver;
import ci.ada.strategy.TransactionStrategy;
import ci.ada.strategy.TransactionStrategyFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionService {

    private final TransactionDao transactionDao = new TransactionDao();
    private final AccountDao accountDao = new AccountDao();

    // Liste des observers
    private final List<TransactionObserver> observers = new ArrayList<>();

    // Méthodes pour gérer les observers
    public void addObserver(TransactionObserver observer) {
        observers.add(observer);
    }

    /*public void removeObserver(TransactionObserver observer) {
        observers.remove(observer);
    }*/

    // NOtifier les observers
    private void notifyTransactionCreated(Transaction transaction) {
        for (TransactionObserver observer : observers) {
            observer.onTransactionRecorded(transaction);
        }
    }


     // Enregistre une transaction et met à jour le solde du compte associé via une strategyyy
     public Transaction recordTransaction(Transaction transaction, Long destinataireCompteId) {
         Account emetteur = accountDao.readById(transaction.getAccount().getId());
         if (emetteur == null) {
             System.out.println("Compte émetteur introuvable, transaction annulée.");
             return null;
         }

         Account destinataire = null;
         if (transaction.getTransactionType() == ci.ada.models.enumeration.TransactionType.VIREMENT) {
             if (destinataireCompteId == null) {
                 System.out.println("Compte destinataire obligatoire pour un virement.");
                 return null;
             }
             destinataire = accountDao.readById(destinataireCompteId);
             if (destinataire == null) {
                 System.out.println("Compte destinataire introuvable, transaction annulée.");
                 return null;
             }
         }

         TransactionStrategy strategy;
         try {
             strategy = TransactionStrategyFactory.getStrategy(transaction.getTransactionType());
         } catch (IllegalArgumentException e) {
             System.out.println("Type de transaction inconnu : " + transaction.getTransactionType());
             return null;
         }

         if (transaction.getTransactionType() == ci.ada.models.enumeration.TransactionType.VIREMENT) {
             // Cast sûr car la factory doit retourner la bonne stratégie
             ci.ada.strategy.VirementStrategy virementStrategy = (ci.ada.strategy.VirementStrategy) strategy;
             float[] newBalances = virementStrategy.calculateNewBalances(emetteur, destinataire, transaction.getAmount());

             if (newBalances == null) {
                 System.out.println("Solde insuffisant, virement annulé.");
                 return null;
             }

             emetteur.setBalance(newBalances[0]);
             destinataire.setBalance(newBalances[1]);

             if (accountDao.updateAccount(emetteur) == null || accountDao.updateAccount(destinataire) == null) {
                 System.out.println("Erreur mise à jour des comptes, virement annulé.");
                 return null;
             }
         } else {
             float newBalance = strategy.calculateNewBalance(emetteur, transaction.getAmount());
             if (newBalance < 0) {
                 System.out.println("Solde insuffisant, transaction annulée.");
                 return null;
             }
             emetteur.setBalance(newBalance);
             if (accountDao.updateAccount(emetteur) == null) {
                 System.out.println("Erreur mise à jour du compte, transaction annulée.");
                 return null;
             }
         }

         transaction.setTransactionDate(new Date());
         Transaction savedTransaction = transactionDao.createTransaction(transaction);
         if (savedTransaction == null) {
             System.out.println("Erreur enregistrement transaction.");
             return null;
         }

         notifyTransactionCreated(savedTransaction);

         return savedTransaction;
     }


    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionDao.getTransactionsByAccount(accountId);
    }

    public List<Transaction> searchTransactions(Long accountId, String type) {
        return transactionDao.searchTransactions(accountId, type);
    }

    /*public void generateAndSendInvoice(Transaction transaction) {
        System.out.println("Facture générée et envoyée par email (simulation) pour transaction id " + transaction.getId());
    }*/
}

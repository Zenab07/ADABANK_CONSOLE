package ci.ada.interfaces;

import ci.ada.factory.TransactionFactory;
import ci.ada.models.Account;
import ci.ada.models.Transaction;
import ci.ada.models.enumeration.TransactionType;
import ci.ada.observer.EmailNotificationObserver;
import ci.ada.services.TransactionService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InterfaceGestionTransaction {
    //private final TransactionService transactionService = new TransactionService();

    private final TransactionService transactionService;

    public InterfaceGestionTransaction() {
        transactionService = new TransactionService();
        // Ajout de l'observer au service
        transactionService.addObserver(new EmailNotificationObserver());
    }


    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n\t##########################################");
            System.out.println("\t#         GESTION DES TRANSACTIONS        #");
            System.out.println("\t###########################################");
            System.out.println("\t# 1. Enregistrer une transaction          #");
            System.out.println("\t# 2. Historique des transactions          #");
            System.out.println("\t# 3. Recherche multicritères transactions #");
            System.out.println("\t# 0. Retour                               #");
            System.out.println("\t###########################################");
            System.out.print("Votre choix : ");

            int choix = Integer.parseInt(scanner.nextLine());

            switch (choix) {
                case 1:
                    enregistrerTransaction(scanner);
                    break;
                case 2:
                    afficherHistorique(scanner);
                    break;
                case 3:
                    rechercheTransactions(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void enregistrerTransaction(Scanner scanner) {
        try {
            System.out.println("\n--- Enregistrement d'une transaction ---");

            System.out.print("ID compte : ");
            Long accountId = Long.parseLong(scanner.nextLine());

            System.out.print("Type de transaction (VIREMENT, DEPOT, RETRAIT) : ");
            TransactionType transactiontype = TransactionType.valueOf(scanner.nextLine().toUpperCase());

            System.out.print("Montant : ");
            float amount = Float.parseFloat(scanner.nextLine());

            System.out.print("Description : ");
            String description = scanner.nextLine();

            Long destinataireCompteId = null;
            if (transactiontype == TransactionType.VIREMENT) {
                System.out.print("ID compte destinataire : ");
                destinataireCompteId = Long.parseLong(scanner.nextLine());
            }

            // Création de la transaction via la factory
            Transaction transaction = TransactionFactory.create(transactiontype, amount, accountId, description);
            transaction.setTransactionDate(new Date());

            Transaction t = transactionService.recordTransaction(transaction, destinataireCompteId);
            if (t != null) {
                System.out.println("Transaction enregistrée avec succès");
                // transactionService.generateAndSendInvoice(t);
            } else {
                System.out.println("Erreur lors de l'enregistrement.");
            }
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }


    private void afficherHistorique(Scanner scanner) {
        try {
            System.out.println("\n--- Historique des transactions ---");

            System.out.print("ID compte : ");
            Long accountId = Long.parseLong(scanner.nextLine());

            List<Transaction> transactions = transactionService.getTransactionHistory(accountId);
            if (transactions.isEmpty()) {
                System.out.println("Aucune transaction trouvée.");
            } else {
                for (Transaction t : transactions) {
                    System.out.println(formatTransaction(t));
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private void rechercheTransactions(Scanner scanner) {
        try {
            System.out.println("\n--- Recherche multicritères des transactions ---");

            System.out.print("ID compte : ");
            Long accountId = Long.parseLong(scanner.nextLine());

            /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.print("Date début (yyyy-MM-dd) : ");
            String debutStr = scanner.nextLine();
            Date dateDebut = debutStr.isEmpty() ? new Date(0) : sdf.parse(debutStr);

            System.out.print("Date fin (yyyy-MM-dd) : ");
            String finStr = scanner.nextLine();
            Date dateFin = finStr.isEmpty() ? new Date() : sdf.parse(finStr);

            System.out.print("Montant min : ");
            String minStr = scanner.nextLine();
            float montantMin = minStr.isEmpty() ? 0f : Float.parseFloat(minStr);

            System.out.print("Montant max : ");
            String maxStr = scanner.nextLine();
            float montantMax = maxStr.isEmpty() ? Float.MAX_VALUE : Float.parseFloat(maxStr);*/

            System.out.print("Type de transaction : ");
            String typeStr = scanner.nextLine();
            String transactiontype = typeStr.isEmpty() ? null : typeStr;

            List<Transaction> transactions = transactionService.searchTransactions(accountId, transactiontype);
            if (transactions.isEmpty()) {
                System.out.println("Aucune transaction trouvée.");
            } else {
                for (Transaction t : transactions) {
                    System.out.println(formatTransaction(t));
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    private String formatTransaction(Transaction t) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("[%s] %s %.2f FCFA - %s", sdf.format(t.getTransactionDate()), t.getTransactionType(), t.getAmount(), t.getDescription());
    }
}

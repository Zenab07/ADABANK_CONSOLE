package ci.ada.observer;

import ci.ada.models.Transaction;

public class EmailNotificationObserver implements TransactionObserver {

    @Override
    public void onTransactionRecorded(Transaction transaction) {
        // Simulation de l'envoi de l'email
        System.out.println(" Email envoyé : " + transaction.getTransactionType() + " de " + transaction.getAmount() +
                " " + "FCFA enregistrée pour le compte de " + transaction.getAccount().getId());
    }
}
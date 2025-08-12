package ci.ada.observer;

import ci.ada.models.Transaction;

public interface TransactionObserver {
    void onTransactionRecorded(Transaction transaction);
}

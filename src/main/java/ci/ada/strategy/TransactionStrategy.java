package ci.ada.strategy;

import ci.ada.models.Account;
import ci.ada.models.Transaction;

public interface TransactionStrategy {
    float calculateNewBalance(Account account, float amount);
    // Pour virement (nouvelle surcharge)
    default float[] calculateNewBalances(Account sender, Account receiver, float amount) {
        throw new UnsupportedOperationException("Cette stratégie ne supporte pas les virements");
    }
}

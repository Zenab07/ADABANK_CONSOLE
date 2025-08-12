package ci.ada.strategy;

import ci.ada.models.Account;
import ci.ada.models.Transaction;

public class DepotStrategy implements TransactionStrategy {

    @Override
    public float calculateNewBalance(Account account, float amount) {
        return account.getBalance() + amount;
    }
}

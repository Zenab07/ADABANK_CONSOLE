package ci.ada.strategy;

import ci.ada.models.Account;

public class RetraitStrategy implements TransactionStrategy {

    @Override
    public float calculateNewBalance(Account account, float amount) {
        float newBalance = account.getBalance() - amount;
        return newBalance >= 0 ? newBalance : -1f;  // -1 c'est pour indiquer que le solde est insuffisant pour effectuer la transactin
    }
}

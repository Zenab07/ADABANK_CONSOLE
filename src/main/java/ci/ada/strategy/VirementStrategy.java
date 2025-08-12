package ci.ada.strategy;

import ci.ada.models.Account;

public class VirementStrategy implements TransactionStrategy {

    @Override
    public float calculateNewBalance(Account account, float amount) {
        throw new UnsupportedOperationException("Utilisez calculateNewBalances pour les virements");
    }

    @Override
    public float[] calculateNewBalances(Account sender, Account receiver, float amount) {
        float senderNewBalance = sender.getBalance() - amount;

        if (senderNewBalance < 0) {
            return null; // solde insuffisant
        }

        float receiverNewBalance = receiver.getBalance() + amount;
        return new float[]{senderNewBalance, receiverNewBalance};
    }
}

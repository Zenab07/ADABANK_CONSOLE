package ci.ada.strategy;

import ci.ada.models.enumeration.TransactionType;

public class TransactionStrategyFactory {

    public static TransactionStrategy getStrategy(TransactionType type) {
        switch (type) {
            case DEPOT:
                return new DepotStrategy();
            case RETRAIT:
                return new RetraitStrategy();
            case VIREMENT:
                return new VirementStrategy();
            default:
                throw new IllegalArgumentException("Type de transaction inconnu: " + type);
        }
    }
}

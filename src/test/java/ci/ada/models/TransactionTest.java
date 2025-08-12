package ci.ada.models;

import ci.ada.models.enumeration.TransactionType;
import org.junit.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionTest {

    private static final Long ID = 1L;
    private static final Float AMOUNT = 2500.50f;
    private static final TransactionType TRANSACTION_TYPE = TransactionType.DEPOT;
    private static final Date TRANSACTION_DATE = new Date();
    private static final String DESCRIPTION = "Premier depôt";
    private static final Account ACCOUNT = new Account();

    @Test
    public void shouldSetAndGetFieldsCorrectly() {
        Transaction transaction = new Transaction();

        transaction.setId(ID);
        transaction.setAmount(AMOUNT);
        transaction.setTransactionType(TRANSACTION_TYPE);
        transaction.setTransactionDate(TRANSACTION_DATE);
        transaction.setDescription(DESCRIPTION);
        transaction.setAccount(ACCOUNT);

        assertEquals(ID, transaction.getId());
        assertEquals(AMOUNT, transaction.getAmount());
        assertEquals(TRANSACTION_TYPE, transaction.getTransactionType());
        assertEquals(TRANSACTION_DATE, transaction.getTransactionDate());
        assertEquals(DESCRIPTION, transaction.getDescription());
        assertEquals(ACCOUNT, transaction.getAccount());
    }
}

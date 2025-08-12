package ci.ada.factory;

import ci.ada.models.Transaction;
import ci.ada.models.Account;
import ci.ada.models.enumeration.TransactionType;

import java.util.Date;

public class TransactionFactory {

    public static Transaction create(TransactionType transactiontype, float amount, Long accountId, String description) {
        Transaction t = new Transaction();
        t.setTransactionType(transactiontype);
        t.setAmount(amount);

        Account account = new Account();
        account.setId(accountId);
        t.setAccount(account);

        t.setDescription(description);
        t.setTransactionDate(new Date());
        return t;
    }
}

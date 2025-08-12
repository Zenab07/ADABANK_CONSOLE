package ci.ada.models;

import ci.ada.models.enumeration.CompteType;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountTest {

    private static final Long ID = 1L;
    private static final Integer COMPTE_NUMBER = 12345;
    private static final Float BALANCE = 1500.75f;
    private static final CompteType COMPTE = CompteType.EPARGNE;
    private static final Date OPENING_DATE = new Date();
    private static final String STATUS = "ACTIVE";
    private static final Customer CUSTOMER = new Customer();

    private Account account;

    @Test
    public void assertThatFieldsAreEquals() {
        Account account = new Account();

        account.setId(ID);
        account.setCompteNumber(COMPTE_NUMBER);
        account.setBalance(BALANCE);
        account.setCompteType(COMPTE);
        account.setOpeningDate(OPENING_DATE);
        account.setStatus(STATUS);
        account.setCustomer(CUSTOMER);

        assertEquals(ID, account.getId());
        assertEquals(COMPTE_NUMBER, account.getCompteNumber());
        assertEquals(BALANCE, account.getBalance());
        assertEquals(COMPTE, account.getCompteType());
        assertEquals(OPENING_DATE, account.getOpeningDate());
        assertEquals(STATUS, account.getStatus());
        assertEquals(CUSTOMER, account.getCustomer());
    }
}
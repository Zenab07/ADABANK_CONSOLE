package ci.ada.models;

import org.junit.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {

    private static final Long ID = 1L;
    private static final String FIRSTNAME = "Paul";
    private static final String LASTNAME = "Dupont";
    private static final String EMAIL = "paul.dupont@example.com";
    private static final String PHONE = "+22501020304";
    private static final Date REGISTRY_DATE = new Date();
    private static final Bank BANK = new Bank();

    @Test
    public void shouldSetAndGetFieldsCorrectly() {
        Customer customer = new Customer();

        customer.setId(ID);
        customer.setFirstname(FIRSTNAME);
        customer.setLastname(LASTNAME);
        customer.setEmail(EMAIL);
        customer.setPhone(PHONE);
        customer.setRegistryDate(REGISTRY_DATE);
        customer.setBank(BANK);

        assertEquals(ID, customer.getId());
        assertEquals(FIRSTNAME, customer.getFirstname());
        assertEquals(LASTNAME, customer.getLastname());
        assertEquals(EMAIL, customer.getEmail());
        assertEquals(PHONE, customer.getPhone());
        assertEquals(REGISTRY_DATE, customer.getRegistryDate());
        assertEquals(BANK, customer.getBank());
    }
}

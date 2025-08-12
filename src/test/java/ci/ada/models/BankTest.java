package ci.ada.models;

import org.junit.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {

    private static final Long ID = 1L;
    private static final String NAME = "AdaBank";
    private static final String COUNTRY = "Côte d'Ivoire";
    private static final String CITY = "Abidjan";
    private static final Date CREATION_DATE = new Date();
    private static final Integer NUMBER_OF_CUSTOMERS = 1500;

    @Test
    public void assertThatFieldsAreEquals() {
        Bank bank = new Bank();

        bank.setId(ID);
        bank.setName(NAME);
        bank.setCountry(COUNTRY);
        bank.setCity(CITY);
        bank.setCreationDate(CREATION_DATE);
        bank.setNumberOfCustomers(NUMBER_OF_CUSTOMERS);

        assertEquals(ID, bank.getId());
        assertEquals(NAME, bank.getName());
        assertEquals(COUNTRY, bank.getCountry());
        assertEquals(CITY, bank.getCity());
        assertEquals(CREATION_DATE, bank.getCreationDate());
        assertEquals(NUMBER_OF_CUSTOMERS, bank.getNumberOfCustomers());
    }
}

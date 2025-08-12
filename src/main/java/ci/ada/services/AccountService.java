package ci.ada.services;

import ci.ada.dao.AccountDao;
import ci.ada.models.Account;
import ci.ada.models.Customer;
import ci.ada.models.enumeration.CompteType;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class AccountService {
    private AccountDao accountDao = new AccountDao();

    public Account openAccount(Customer customer, CompteType compteType) {
        /*
        Account account = new Account();
        account.setCompteNumber(generateCompteNumber());
        account.setBalance(0f);
        account.setCompteType(compteType);
        account.setOpeningDate(new Date());
        account.setStatus("ACTIF");
        account.setCustomer(customer);

        return accountDao.createAccount(account);
        */

        // DESIGN PATTERN BUILDER
        Account account = new Account.Builder()
                .setCompteNumber(generateCompteNumber())
                .setBalance(0f)
                .setCompteType(compteType)
                .setOpeningDate(new Date())
                .setStatus("ACTIF")
                .setCustomer(customer)
                .build();

        return accountDao.createAccount(account);
    }

    public boolean closeAccount(int compteNumber) {
        Account account = accountDao.readByCompteNumber(compteNumber);
        if (account == null) {
            System.out.println("Compte non trouvé.");
            return false;
        }
        if ("FERME".equalsIgnoreCase(account.getStatus())) {
            System.out.println("Compte déjà fermé.");
            return false;
        }
        account.setStatus("FERME");
        return accountDao.updateAccountStatus(compteNumber, "FERME");
    }

    public List<Account> getAccountsByClientId(Long clientId) {
        return accountDao.findByClientId(clientId);
    }

    public Account getAccountDetails(int compteNumber) {
        return accountDao.readByCompteNumber(compteNumber);
    }

    private int generateCompteNumber() {
        // pour générer un numéro de compte aléatoire entre 100000 et 999999
        return 100000 + new Random().nextInt(900000);
    }
}

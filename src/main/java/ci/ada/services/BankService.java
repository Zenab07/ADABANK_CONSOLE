package ci.ada.services;

import ci.ada.dao.BankDao;
import ci.ada.models.Bank;

import java.util.List;

public class BankService {

    private final BankDao bankDao = new BankDao();

    // Création simple d'une banque (sans email)
    public Bank createBank(Bank bank) {
        return bankDao.createBank(bank);
    }

    // Recherche banques par pays et ville
    public List<Bank> searchBanks(String country, String city) {
        return bankDao.searchBanks(country, city);
    }

    // Récupérer les top 15 banques
    public List<Bank> getTop15BanksByClientCount() {
        return bankDao.getTop15BanksByClientCount();
    }
}

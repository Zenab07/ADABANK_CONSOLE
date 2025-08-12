package ci.ada.services;

import ci.ada.dao.BankDao;
import ci.ada.models.Bank;

import java.util.Date;
import java.util.List;

public class BankService {

    private final BankDao bankDao = new BankDao();

    public Bank getBankByName(String name) {
        return bankDao.findByName(name);
    }

    // Création d'une banque
    public Bank createBank(String name, String country, String city) {
        Bank bank = new Bank.Builder()
                .name(name)
                .country(country)
                .city(city)
                .creationDate(new Date())
                .build();

        return bankDao.createBank(bank);
    }

    // Recherche banque par pays et ville
    public List<Bank> searchBanks(String country, String city) {
        return bankDao.searchBanks(country, city);
    }

    // Récupérer les top 15 banques
    public List<Bank> getTop15BanksByClientCount() {
        return bankDao.getTop15BanksByClientCount();
    }
}

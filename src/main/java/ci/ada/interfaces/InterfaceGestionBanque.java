package ci.ada.interfaces;

import ci.ada.models.Bank;
import ci.ada.services.BankService;

import java.util.List;
import java.util.Scanner;

public class InterfaceGestionBanque {
    private final BankService bankService;
    private final Scanner scanner;

    public InterfaceGestionBanque(Scanner scanner, BankService bankService) {
        this.scanner = scanner;
        this.bankService = bankService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n\t##########################################");
            System.out.println("\t#          GESTION DES BANQUES           #");
            System.out.println("\t##########################################");
            System.out.println("\t# 1. Enregistrer une nouvelle banque     #");
            System.out.println("\t# 2. Afficher top 15 banques par clients #");
            System.out.println("\t# 3. Rechercher banques par pays/ville   #");
            System.out.println("\t# 0. Retour au menu principal            #");
            System.out.println("\t##########################################");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    enregistrerBanque();
                    break;
                case 2:
                    afficherTopBanques();
                    break;
                case 3:
                    rechercherBanques();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void enregistrerBanque() {
        System.out.println("\n--- Enregistrement d'une nouvelle banque ---");
        System.out.print("Nom : ");
        String name = scanner.nextLine();

        System.out.print("Pays : ");
        String country = scanner.nextLine();

        System.out.print("Ville : ");
        String city = scanner.nextLine();


        Bank createdBank = bankService.createBank(name, country, city);
        if (createdBank != null) {
            System.out.println("Banque créée avec succès : " + createdBank.getName());
        } else {
            System.out.println("Erreur lors de la création de la banque.");
        }
    }

    private void afficherTopBanques() {
        System.out.println("\n--- Top 15 banques par nombre de clients ---");
        List<Bank> banks = bankService.getTop15BanksByClientCount();
        if (banks.isEmpty()) {
            System.out.println("Aucune banque trouvée.");
        } else {
            for (Bank bank : banks) {
                System.out.println(bank.getName() + " (" + bank.getNumberOfCustomers() + " clients)");
            }
        }
    }

    private void rechercherBanques() {
        System.out.println("\n--- Recherche des banques ---");
        System.out.print("Pays (laisser vide si aucun) : ");
        String country = scanner.nextLine();

        System.out.print("Ville (laisser vide si aucun) : ");
        String city = scanner.nextLine();

        List<Bank> banks = bankService.searchBanks(country, city);
        if (banks.isEmpty()) {
            System.out.println("Aucune banque trouvée.");
        } else {
            for (Bank bank : banks) {
                System.out.println(bank.getName() + " - " + bank.getCountry() + ", " + bank.getCity());
            }
        }
    }
}

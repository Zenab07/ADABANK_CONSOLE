package ci.ada;

import ci.ada.services.*;
import ci.ada.dao.*;
import ci.ada.models.Bank;

import java.util.List;
import java.util.Scanner;


public class App
{
    public static void main( String[] args )
    {
        Scanner saisie = new Scanner(System.in);
        BankService bankService = new BankService();

        System.out.println("\t********************************************************************************************");
        System.out.println("\t*                               BIENVENUE SUR ADABANK                                      *");
        System.out.println("\t********************************************************************************************");

        while (true) {
            System.out.println("\n\t##########################################");
            System.out.println("\t#              MENU PRINCIPAL            #");
            System.out.println("\t##########################################");
            System.out.println("\t# 1. Inscription                         #");
            System.out.println("\t# 2. Connexion                           #");
            System.out.println("\t# 3. Gestion des banques                 #");
            System.out.println("\t# 0. Quitter                             #");
            System.out.println("\t##########################################");
            System.out.print("Votre choix : ");

            int choix = saisie.nextInt();
            saisie.nextLine();

            switch (choix) {
                case 1:
                    // registrationService.registerUser(saisie);
                    System.out.println("Fonction inscription non implémentée ici.");
                    break;

                case 2:
                    // loginService.login(saisie);
                    System.out.println("Fonction connexion non implémentée ici.");
                    break;

                case 3:
                    gestionBanquesMenu(saisie, bankService);
                    break;

                case 0:
                    System.out.println("Merci d’avoir utilisé ADABANK. À bientôt !");
                    saisie.close();
                    return;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void gestionBanquesMenu(Scanner saisie, BankService bankService) {
        while (true) {
            System.out.println("\n\t##########################################");
            System.out.println("\t#          GESTION DES BANQUES           #");
            System.out.println("\t##########################################");
            System.out.println("\t# 1. Enregistrer une nouvelle banque     #");
            System.out.println("\t# 2. Afficher top 15 banques par clients #");
            System.out.println("\t# 3. Rechercher banques par pays/ville   #");
            System.out.println("\t# 0. Retour au menu principal             #");
            System.out.println("\t##########################################");
            System.out.print("Votre choix : ");

            int choix = saisie.nextInt();
            saisie.nextLine();

            switch (choix) {
                case 1:
                    enregistrerBanque(saisie, bankService);
                    break;

                case 2:
                    afficherTopBanques(bankService);
                    break;

                case 3:
                    rechercherBanques(saisie, bankService);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private static void enregistrerBanque(Scanner saisie, BankService bankService) {
        System.out.println("\n--- Enregistrement d'une nouvelle banque ---");
        System.out.print("Nom : ");
        String name = saisie.nextLine();

        System.out.print("Pays : ");
        String country = saisie.nextLine();

        System.out.print("Ville : ");
        String city = saisie.nextLine();

        Bank bank = new Bank();
        bank.setName(name);
        bank.setCountry(country);
        bank.setCity(city);

        Bank createdBank = bankService.createBank(bank);
        if (createdBank != null) {
            System.out.println("Banque créée avec succès : " + createdBank.getName());
        } else {
            System.out.println("Erreur lors de la création de la banque.");
        }
    }

    private static void afficherTopBanques(BankService banqueService) {
        System.out.println("\n--- Top 15 banques par nombre de clients ---");
        List<Bank> banks = banqueService.getTop15BanksByClientCount();
        if (banks.isEmpty()) {
            System.out.println("Aucune banque trouvée.");
        } else {
            for (Bank bank : banks) {
                System.out.println(bank.getName() + " (" + bank.getNumberOfCustomers() + " clients)");
            }
        }
    }

    private static void rechercherBanques(Scanner saisie, BankService bankService) {
        System.out.println("\n--- Recherche des banques ---");
        System.out.print("Pays (laisser vide si aucun) : ");
        String country = saisie.nextLine();

        System.out.print("Ville (laisser vide si aucun) : ");
        String city = saisie.nextLine();

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


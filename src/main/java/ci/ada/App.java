package ci.ada;

import ci.ada.interfaces.InterfaceGestionBanque;
import ci.ada.interfaces.InterfaceGestionClient;
import ci.ada.interfaces.InterfaceGestionCompte;
import ci.ada.interfaces.InterfaceGestionTransaction;
import ci.ada.services.*;

import java.util.Scanner;


public class App
{
    private static final String ADMIN_LOGIN = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    public static void main( String[] args )
    {
        Scanner saisie = new Scanner(System.in);
        BankService bankService = new BankService();

        CustomerService customerService = new CustomerService();
        InterfaceGestionBanque interfaceGestionBanque = new InterfaceGestionBanque(saisie, bankService);
        InterfaceGestionClient interfaceGestionClient = new InterfaceGestionClient(saisie, customerService, bankService);
        InterfaceGestionCompte interfaceGestionCompte = new InterfaceGestionCompte();
        InterfaceGestionTransaction interfaceGestionTransaction = new InterfaceGestionTransaction();


        System.out.println("\t********************************************************************************************");
        System.out.println("\t*                               BIENVENUE SUR ADABANK                                      *");
        System.out.println("\t********************************************************************************************");

        while (!loginAdmin(saisie)) {
            System.out.println(" Identifiants incorrects. Veuillez réessayer.\n");
        }

        while (true) {
            System.out.println("\n\t##########################################");
            System.out.println("\t#              MENU PRINCIPAL            #");
            System.out.println("\t##########################################");
            System.out.println("\t# 1. Gestion des banques                 #");
            System.out.println("\t# 2. Gestion des clients                 #");
            System.out.println("\t# 3. Gestion des comptes                 #");
            System.out.println("\t# 4. Gestion des transactions            #");
            System.out.println("\t# 0. Quitter                             #");
            System.out.println("\t##########################################");
            System.out.print("Votre choix : ");

            int choix = saisie.nextInt();
            saisie.nextLine();

            switch (choix) {
                case 1:
                    interfaceGestionBanque.menu();
                    break;

                case 2:
                    interfaceGestionClient.menu();
                    break;

                case 3:
                    interfaceGestionCompte.menu(saisie);
                    break;

                case 4:
                    interfaceGestionTransaction.menu(saisie);
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

    private static boolean loginAdmin(Scanner saisie) {
        System.out.println("\n\t##########################################");
        System.out.println("\t#         Connexion Administrateur       #");
        System.out.println("\t##########################################");
        System.out.print("\t# Login : ");
        String login = saisie.nextLine();

        System.out.print("\t# Mot de passe : ");
        String password = saisie.nextLine();
        System.out.println("\t##########################################");


        if (ADMIN_LOGIN.equals(login) && ADMIN_PASSWORD.equals(password)) {
            System.out.println("✅ Connexion réussie. Bienvenue, " + login + " !");
            return true;
        } else {
            return false;
        }
    }

    /*private static void gestionBanquesMenu(Scanner saisie, BankService bankService) {
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
    }*/
}


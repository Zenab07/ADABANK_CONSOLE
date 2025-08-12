package ci.ada.interfaces;

import ci.ada.models.Account;
import ci.ada.models.Customer;
import ci.ada.models.enumeration.CompteType;
import ci.ada.services.AccountService;

import java.util.List;
import java.util.Scanner;

public class InterfaceGestionCompte {
    private AccountService accountService = new AccountService();

    public void menu(Scanner scanner) {
        while (true) {
            System.out.println("\n\t##########################################");
            System.out.println("\t#           GESTION DES COMPTES          #");
            System.out.println("\t##########################################");
            System.out.println("\t# 1. Ouvrir un compte                    #");
            System.out.println("\t# 2. Clôturer un compte                  #");
            System.out.println("\t# 3. Liste comptes par client            #");
            System.out.println("\t# 4. Détails d'un compte                 #");
            System.out.println("\t# 0. Retour                              #");
            System.out.println("\t##########################################");
            System.out.print("Votre choix : ");

            int choix;
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide.");
                continue;
            }

            switch (choix) {
                case 1:
                    ouvrirCompte(scanner);
                    break;
                case 2:
                    cloturerCompte(scanner);
                    break;
                case 3:
                    listerComptesParClient(scanner);
                    break;
                case 4:
                    afficherDetailsCompte(scanner);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void ouvrirCompte(Scanner scanner) {
        System.out.println("\n--- Ouvrir un compte ---");
        try {
            System.out.print("ID client : ");
            Long clientId = Long.parseLong(scanner.nextLine());

            System.out.print("Type de compte (COURANT, EPARGNE) : ");
            String typeStr = scanner.nextLine();
            CompteType compteType = CompteType.valueOf(typeStr.toUpperCase());

            Customer customer = new Customer();
            customer.setId(clientId);

            Account account = accountService.openAccount(customer, compteType);
            if (account != null) {
                System.out.println("Compte ouvert avec succès. Numéro de compte: " + account.getCompteNumber());
            } else {
                System.out.println("Erreur lors de l'ouverture du compte.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    private void cloturerCompte(Scanner scanner) {
        System.out.println("\n--- Clôturer un compte ---");
        try {
            System.out.print("Numéro du compte à clôturer : ");
            int compteNumber = Integer.parseInt(scanner.nextLine());

            boolean success = accountService.closeAccount(compteNumber);
            if (success) {
                System.out.println("Compte clôturé avec succès.");
            } else {
                System.out.println("Erreur lors de la clôture du compte.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Numéro de compte invalide.");
        }
    }

    private void listerComptesParClient(Scanner scanner) {
        System.out.println("\n--- Liste des comptes par client ---");
        try {
            System.out.print("ID client : ");
            Long clientId = Long.parseLong(scanner.nextLine());

            List<Account> comptes = accountService.getAccountsByClientId(clientId);
            if (comptes.isEmpty()) {
                System.out.println("Aucun compte trouvé pour ce client.");
            } else {
                for (Account a : comptes) {
                    System.out.println("Compte n°" + a.getCompteNumber() + " - Solde : " + a.getBalance() + " - Statut : " + a.getStatus());
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("ID client invalide.");
        }
    }

    private void afficherDetailsCompte(Scanner scanner) {
        System.out.println("\n--- Détails d'un compte ---");
        try {
            System.out.print("Numéro du compte : ");
            int compteNumber = Integer.parseInt(scanner.nextLine());

            Account account = accountService.getAccountDetails(compteNumber);
            if (account != null) {
                System.out.println("Numéro de compte : " + account.getCompteNumber());
                System.out.println("Type de compte : " + account.getCompteType());
                System.out.println("Solde : " + account.getBalance());
                System.out.println("Statut : " + account.getStatus());
                System.out.println("Ouvert le : " + account.getOpeningDate());
                // Si tu as une date de clôture, tu peux l'ajouter ici
            } else {
                System.out.println("Compte non trouvé.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Numéro de compte invalide.");
        }
    }
}

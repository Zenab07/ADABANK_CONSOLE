package ci.ada.interfaces;

import ci.ada.models.Customer;
import ci.ada.models.Bank;
import ci.ada.services.BankService;
import ci.ada.services.CustomerService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class InterfaceGestionClient {

    private final CustomerService customerService;
    private final BankService bankService;
    private final Scanner scanner;

    public InterfaceGestionClient(Scanner scanner, CustomerService customerService, BankService bankService) {
        this.scanner = scanner;
        this.customerService = customerService;
        this.bankService = bankService;
    }

    public void menu() {
        while (true) {
            System.out.println("\n\t##########################################");
            System.out.println("\t#           GESTION DES CLIENTS          #");
            System.out.println("\t##########################################");
            System.out.println("\t# 1. Inscription client                  #");
            System.out.println("\t# 2. Liste clients par banque            #");
            System.out.println("\t# 3. Recherche multicritères clients    #");
            System.out.println("\t# 0. Retour au menu principal            #");
            System.out.println("\t##########################################");
            System.out.print("Votre choix : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    inscrireClient();
                    break;
                case 2:
                    listerClientsParBanque();
                    break;
                case 3:
                    rechercheMultiClients();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private void inscrireClient() {
        System.out.println("\n--- Inscription d'un client ---");

        System.out.print("Prénom : ");
        String firstName = scanner.nextLine();

        System.out.print("Nom : ");
        String lastName = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Téléphone : ");
        String phone = scanner.nextLine();

        System.out.print("Nom de la banque : ");
        String bankName = scanner.nextLine();

        Bank bank = bankService.getBankByName(bankName);
        if (bank == null) {
            System.out.println("Erreur : Banque introuvable avec ce nom.");
            return;
        }

        // Utilisation du builder dans le service pour créer le customer
        Customer createdClient = customerService.registerCustomer( firstName, lastName, email, phone, new Date(), bank );

        // On associe la banque après création (ou adapte le builder pour la gérer)
        if (createdClient != null) {
            createdClient.setBank(bank);
            System.out.println("Client créé avec succès : " + createdClient.getFirstname() + " " + createdClient.getLastname());
        } else {
            System.out.println("Erreur lors de la création du client.");
        }
    }

    private void listerClientsParBanque() {
        System.out.print("\nNom de la banque : ");
        String bankName = scanner.nextLine();

        Bank bank = bankService.getBankByName(bankName);
        if (bank == null) {
            System.out.println("Banque introuvable.");
            return;
        }

        List<Customer> clients = customerService.getCustomersByBank(bank.getId());
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé pour cette banque.");
        } else {
            System.out.println("\nListe des clients :");
            for (Customer c : clients) {
                System.out.println(c.getFirstname() + " " + c.getLastname() + " - " + c.getEmail());
            }
        }
    }

    private void rechercheMultiClients() {
        System.out.println("\n--- Recherche multicritères clients ---");
        System.out.print("Nom (laisser vide si aucun) : ");
        String name = scanner.nextLine();

        System.out.print("Email (laisser vide si aucun) : ");
        String email = scanner.nextLine();

        System.out.print("Numéro client (laisser vide si aucun) : ");
        String id = scanner.nextLine();

        List<Customer> clients = customerService.searchCustomers(name, email, id);
        if (clients.isEmpty()) {
            System.out.println("Aucun client trouvé.");
        } else {
            System.out.println("\nRésultats de la recherche :");
            for (Customer c : clients) {
                System.out.println(c.getId() + " - " + c.getFirstname() + " " + c.getLastname() + " - " + c.getEmail());
            }
        }
    }
}

package ci.ada.services;

import ci.ada.dao.CustomerDao;
import ci.ada.models.Bank;
import ci.ada.models.Customer;
import ci.ada.services.impl.EmailServiceImpl;
import ci.ada.services.interfaces.EmailService;


import java.util.Date;
import java.util.List;

public class CustomerService {
    private CustomerDao customerDao = new CustomerDao();
    private EmailService emailService = new EmailServiceImpl();

    public Customer registerCustomer(String firstname, String lastname, String email, String phone, Date registryDate, Bank bank) {
        Customer customer = new Customer.Builder()
                .setFirstname(firstname)
                .setLastname(lastname)
                .setEmail(email)
                .setPhone(phone)
                .setRegistryDate(registryDate)
                .setBank(bank)
                .build();

        customerDao.createCustomer(customer);

        System.out.println("✅ Client enregistré avec succès !");

        String subject = "Bienvenue chez nous";
        String body = "Bonjour M/Mme/Mlle " + firstname + ",\n\n" +
                "Bienvenue dans notre banque " + bank.getName() + " !\n" +
                "Nous sommes ravis de vous compter parmi nos clients.\n\n" +
                "Cordialement,\nL’équipe " + bank.getName();

        emailService.sendEmail(email, subject, body);

        return customer;
    }

    public List<Customer> getCustomersByBank(Long bankId) {
        return customerDao.findByBankId(bankId);
    }

    public List<Customer> searchCustomers(String name, String email, String id) {
        return customerDao.searchCustomers(name, email, id);
    }
}

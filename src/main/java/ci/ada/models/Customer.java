package ci.ada.models;

import java.util.Date;

/**
 * Représente un client dans le système ADABANK.
 *
 * Un client est rattaché à une banque et possède
 * des informations personnelles comme son nom, prénom,
 * email, numéro de téléphone, et la date d'enregistrement.
 *
 * Cette classe inclut un pattern Builder interne pour
 * simplifier la création d'instances de {@link Customer}.
 *
 */
public class Customer {

    /** Identifiant unique du client (généré par la base de données). */
    private Long id;

    /** Prénom du client. */
    private String firstname;

    /** Nom de famille du client. */
    private String lastname;

    /** Adresse email du client. */
    private String email;

    /** Numéro de téléphone du client. */
    private String phone;

    /** Date d'enregistrement du client dans le système. */
    private Date registryDate;

    /** Banque à laquelle le client est affilié. */
    private Bank bank;

    /**
     * Retourne la banque associée au client.
     * @return banque affiliée.
     */
    public Bank getBank() {
        return bank;
    }

    /**
     * Définit la banque associée au client.
     * @param bank banque affiliée.
     */
    public void setBank(Bank bank) {
        this.bank = bank;
    }

    /**
     * Retourne l'identifiant unique du client.
     * @return id du client.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique du client.
     * @param id identifiant du client.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le prénom du client.
     * @return firstname prénom.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Définit le prénom du client.
     * @param firstname prénom.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Retourne le nom de famille du client.
     * @return lastname nom de famille.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Définit le nom de famille du client.
     * @param lastname nom de famille.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Retourne l'adresse email du client.
     * @return email adresse email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'adresse email du client.
     * @param email adresse email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne le numéro de téléphone du client.
     * @return phone numéro de téléphone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Définit le numéro de téléphone du client.
     * @param phone numéro de téléphone.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retourne la date d'enregistrement du client.
     * @return registryDate date d'enregistrement.
     */
    public Date getRegistryDate() {
        return registryDate;
    }

    /**
     * Définit la date d'enregistrement du client.
     * @param registryDate date d'enregistrement.
     */
    public void setRegistryDate(Date registryDate) {
        this.registryDate = registryDate;
    }

    /**
     * Builder interne pour construire un objet {@link Customer}.
     */
    public static class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String email;
        private String phone;
        private Date registryDate;
        private Bank bank;

        /**
         * Définit l'identifiant du client.
         * @param id identifiant unique.
         * @return Builder instance courante.
         */
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Définit le prénom du client.
         * @param firstname prénom.
         * @return Builder instance courante.
         */
        public Builder setFirstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        /**
         * Définit le nom de famille du client.
         * @param lastname nom de famille.
         * @return Builder instance courante.
         */
        public Builder setLastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        /**
         * Définit l'adresse email du client.
         * @param email adresse email.
         * @return Builder instance courante.
         */
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        /**
         * Définit le numéro de téléphone du client.
         * @param phone numéro de téléphone.
         * @return Builder instance courante.
         */
        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Définit la date d'enregistrement du client.
         * @param registryDate date d'enregistrement.
         * @return Builder instance courante.
         */
        public Builder setRegistryDate(Date registryDate) {
            this.registryDate = registryDate;
            return this;
        }

        /**
         * Définit la banque associée au client.
         * @param bank banque affiliée.
         * @return Builder instance courante.
         */
        public Builder setBank(Bank bank) {
            this.bank = bank;
            return this;
        }

        /**
         * Construit et retourne une instance de {@link Customer}.
         * @return Customer instance créée.
         */
        public Customer build() {
            Customer customer = new Customer();
            customer.setId(id);
            customer.setFirstname(firstname);
            customer.setLastname(lastname);
            customer.setEmail(email);
            customer.setPhone(phone);
            customer.setRegistryDate(registryDate);
            customer.setBank(bank);
            return customer;
        }
    }
}

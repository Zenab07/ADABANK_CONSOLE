package ci.ada.models;

import ci.ada.models.enumeration.CompteType;
import java.util.Date;

/**
 * Représente un compte bancaire dans le système.
 *
 * <p>Un compte est lié à un {@link Customer} et contient des informations
 * telles que le numéro de compte, le solde, le type, la date d'ouverture et le statut.</p>
 *
 * <p>La classe inclut un {@link Builder} pour faciliter la création d'instances
 * de manière fluide.</p>
 */
public class Account {

    /** Identifiant unique du compte (clé primaire en base de données) */
    private Long id;

    /** Numéro unique attribué au compte */
    private Integer compteNumber;

    /** Solde actuel du compte */
    private Float balance;

    /** Type de compte (ex. COURANT, EPARGNE) défini par {@link CompteType} */
    private CompteType compteType;

    /** Date d'ouverture du compte */
    private Date openingDate;

    /** Statut actuel du compte (ex. ACTIF, BLOQUÉ, FERMÉ) */
    private String status;

    /** Client propriétaire de ce compte */
    private Customer customer;

    // ---------- GETTERS & SETTERS ----------

    /** @return Le client propriétaire du compte */
    public Customer getCustomer() {
        return customer;
    }

    /** @param customer Définit le client propriétaire du compte */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /** @return L'identifiant unique du compte */
    public Long getId() {
        return id;
    }

    /** @param id Définit l'identifiant unique du compte */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return Le numéro du compte */
    public Integer getCompteNumber() {
        return compteNumber;
    }

    /** @param compteNumber Définit le numéro du compte */
    public void setCompteNumber(Integer compteNumber) {
        this.compteNumber = compteNumber;
    }

    /** @return Le solde actuel du compte */
    public Float getBalance() {
        return balance;
    }

    /** @param balance Définit le solde du compte */
    public void setBalance(Float balance) {
        this.balance = balance;
    }

    /** @return Le type du compte */
    public CompteType getCompteType() {
        return compteType;
    }

    /** @param compteType Définit le type du compte */
    public void setCompteType(CompteType compteType) {
        this.compteType = compteType;
    }

    /** @return La date d'ouverture du compte */
    public Date getOpeningDate() {
        return openingDate;
    }

    /** @param openingDate Définit la date d'ouverture du compte */
    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    /** @return Le statut actuel du compte */
    public String getStatus() {
        return status;
    }

    /** @param status Définit le statut actuel du compte */
    public void setStatus(String status) {
        this.status = status;
    }

    // ---------- BUILDER PATTERN ----------
    /**
     * Classe interne pour implémenter le pattern Builder
     * permettant de créer un objet {@link Account} de manière fluide.
     */
    public static class Builder {
        private Long id;
        private Integer compteNumber;
        private Float balance;
        private CompteType compteType;
        private Date openingDate;
        private String status;
        private Customer customer;

        /** @param id Définit l'identifiant du compte */
        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        /** @param compteNumber Définit le numéro du compte */
        public Builder setCompteNumber(Integer compteNumber) {
            this.compteNumber = compteNumber;
            return this;
        }

        /** @param balance Définit le solde du compte */
        public Builder setBalance(Float balance) {
            this.balance = balance;
            return this;
        }

        /** @param compteType Définit le type du compte */
        public Builder setCompteType(CompteType compteType) {
            this.compteType = compteType;
            return this;
        }

        /** @param openingDate Définit la date d'ouverture du compte */
        public Builder setOpeningDate(Date openingDate) {
            this.openingDate = openingDate;
            return this;
        }

        /** @param status Définit le statut du compte */
        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        /** @param customer Définit le propriétaire du compte */
        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        /**
         * Construit l'objet {@link Account} final à partir des valeurs fournies.
         *
         * @return Une nouvelle instance de {@link Account}
         */
        public Account build() {
            Account account = new Account();
            account.setId(this.id);
            account.setCompteNumber(this.compteNumber);
            account.setBalance(this.balance);
            account.setCompteType(this.compteType);
            account.setOpeningDate(this.openingDate);
            account.setStatus(this.status);
            account.setCustomer(this.customer);
            return account;
        }
    }
}

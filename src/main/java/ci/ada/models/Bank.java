package ci.ada.models;

import java.util.Date;

/**
 * Représente une banque dans le système ADABANK.
 *
 * Cette classe contient les informations principales d'une banque,
 * telles que son nom, sa localisation, sa date de création et
 * le nombre de clients enregistrés.
 *
 * Elle utilise un pattern Builder interne pour faciliter
 * la création d'instances de manière fluide.
 *
 * @author MZ
 * @version 1.0
 */
public class Bank {

    /** Identifiant unique de la banque (généré par la base de données). */
    private Long id;

    /** Nom officiel de la banque. */
    private String name;

    /** Pays dans lequel la banque est enregistrée. */
    private String country;

    /** Ville où se trouve le siège ou l'agence principale. */
    private String city;

    /** Date de création de la banque dans le système. */
    private Date creationDate;

    /** Nombre total de clients enregistrés dans cette banque. */
    private Integer numberOfCustomers;

    /**
     * Retourne l'identifiant unique de la banque.
     * @return id identifiant unique.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique de la banque.
     * @param id identifiant unique.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le nom de la banque.
     * @return name nom de la banque.
     */
    public String getName() {
        return name;
    }

    /**
     * Définit le nom de la banque.
     * @param name nom de la banque.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retourne le pays de la banque.
     * @return country pays de la banque.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Définit le pays de la banque.
     * @param country pays de la banque.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Retourne la ville de la banque.
     * @return city ville de la banque.
     */
    public String getCity() {
        return city;
    }

    /**
     * Définit la ville de la banque.
     * @param city ville de la banque.
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retourne la date de création de la banque.
     * @return creationDate date de création.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Définit la date de création de la banque.
     * @param creationDate date de création.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Retourne le nombre de clients enregistrés.
     * @return numberOfCustomers nombre de clients.
     */
    public Integer getNumberOfCustomers() {
        return numberOfCustomers;
    }

    /**
     * Définit le nombre de clients enregistrés.
     * @param numberOfCustomers nombre de clients.
     */
    public void setNumberOfCustomers(Integer numberOfCustomers) {
        this.numberOfCustomers = numberOfCustomers;
    }

    /**
     * Builder interne pour construire un objet {@link Bank}
     * de manière fluide et lisible.
     */
    public static class Builder {
        private Long id;
        private String name;
        private String country;
        private String city;
        private Date creationDate;
        private Integer numberOfCustomers;

        /**
         * Définit l'identifiant unique.
         * @param id identifiant unique.
         * @return Builder instance courante.
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Définit le nom de la banque.
         * @param name nom de la banque.
         * @return Builder instance courante.
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Définit le pays de la banque.
         * @param country pays de la banque.
         * @return Builder instance courante.
         */
        public Builder country(String country) {
            this.country = country;
            return this;
        }

        /**
         * Définit la ville de la banque.
         * @param city ville de la banque.
         * @return Builder instance courante.
         */
        public Builder city(String city) {
            this.city = city;
            return this;
        }

        /**
         * Définit la date de création de la banque.
         * @param creationDate date de création.
         * @return Builder instance courante.
         */
        public Builder creationDate(Date creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        /**
         * Définit le nombre de clients.
         * @param numberOfCustomers nombre de clients.
         * @return Builder instance courante.
         */
        public Builder numberOfCustomers(Integer numberOfCustomers) {
            this.numberOfCustomers = numberOfCustomers;
            return this;
        }

        /**
         * Construit et retourne une instance de {@link Bank}.
         * @return Bank instance créée.
         */
        public Bank build() {
            Bank bank = new Bank();
            bank.setId(this.id);
            bank.setName(this.name);
            bank.setCountry(this.country);
            bank.setCity(this.city);
            bank.setCreationDate(this.creationDate);
            bank.setNumberOfCustomers(this.numberOfCustomers);
            return bank;
        }
    }
}

package ci.ada.models;

import ci.ada.models.enumeration.TransactionType;
import java.util.Date;

/**
 * Représente une transaction financière dans le système ADABANK.
 * <p>
 * Une transaction est liée à un compte {@link Account} et peut correspondre
 * à différentes opérations : dépôt, retrait, transfert, etc.
 * Chaque transaction contient un montant, un type, une date et une description.
 * </p>
 *
 * @author MZ
 * @version 1.0
 */
public class Transaction {

    /** Identifiant unique de la transaction (généré par la base de données). */
    private Long id;

    /** Montant de la transaction. */
    private Float amount;

    /** Type de la transaction (dépôt, retrait, transfert, etc.). */
    private TransactionType transactionType;

    /** Date à laquelle la transaction a été effectuée. */
    private Date transactionDate;

    /** Description ou motif de la transaction. */
    private String description;

    /** Compte associé à cette transaction. */
    private Account account;

    /**
     * Retourne le compte associé à cette transaction.
     * @return compte lié à la transaction.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Définit le compte associé à cette transaction.
     * @param account compte lié à la transaction.
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    /**
     * Retourne l'identifiant unique de la transaction.
     * @return identifiant de la transaction.
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant unique de la transaction.
     * @param id identifiant de la transaction.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retourne le montant de la transaction.
     * @return montant de la transaction.
     */
    public Float getAmount() {
        return amount;
    }

    /**
     * Définit le montant de la transaction.
     * @param amount montant de la transaction.
     */
    public void setAmount(Float amount) {
        this.amount = amount;
    }

    /**
     * Retourne le type de la transaction.
     * @return type de transaction.
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Définit le type de la transaction.
     * @param transactionType type de transaction.
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Retourne la date à laquelle la transaction a été effectuée.
     * @return date de transaction.
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Définit la date à laquelle la transaction a été effectuée.
     * @param transactionDate date de transaction.
     */
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    /**
     * Retourne la description ou le motif de la transaction.
     * @return description de la transaction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description ou le motif de la transaction.
     * @param description description de la transaction.
     */
    public void setDescription(String description) {
        this.description = description;
    }
}

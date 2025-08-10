package ci.ada.models;

import ci.ada.models.enumeration.CompteType;

import java.util.Date;

public class Account {
    private Long id;
    private Integer compteNumber;
    private Float balance;
    private CompteType compteType;
    private Date openingDate;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCompteNumber() {
        return compteNumber;
    }

    public void setCompteNumber(Integer compteNumber) {
        this.compteNumber = compteNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public CompteType getCompteType() {
        return compteType;
    }

    public void setCompteType(CompteType compteType) {
        this.compteType = compteType;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
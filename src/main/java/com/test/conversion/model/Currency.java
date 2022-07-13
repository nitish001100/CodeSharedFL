package com.test.conversion.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "currency")
public class Currency {

    private String currency;
    private String amount;

    public Currency() {}

    public Currency(String currency, String amount) {
        this.currency = currency;
        this.amount = amount;
    }

    @Id
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
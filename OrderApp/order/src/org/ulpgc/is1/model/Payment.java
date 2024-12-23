package org.ulpgc.is1.model;

import java.time.LocalDate;

public class Payment {
    private final LocalDate date;
    private final int amount;
    private final PaymentType type;

    public Payment(LocalDate date, int amount, PaymentType type) {
        this.date = date;
        this.amount = amount;
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public PaymentType getType() {
        return type;
    }
}

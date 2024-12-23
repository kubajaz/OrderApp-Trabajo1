package org.ulpgc.is1.model;

import java.time.LocalDate;

public class PromotionalDiscount {
    private final int code;
    private final LocalDate from;
    private final LocalDate to;
    private final int discount;

    public PromotionalDiscount(int code, LocalDate from, LocalDate to, int discount) {
        this.code = code;
        this.from = from;
        this.to = to;
        this.discount = discount;
    }

    public int getCode() {
        return code;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public int getDiscount() {
        return discount;
    }

    // Sprawdzanie, czy zniżka jest aktualna
    public boolean isValid(LocalDate date) {
        return date.isAfter(from) && date.isBefore(to);
    }
}

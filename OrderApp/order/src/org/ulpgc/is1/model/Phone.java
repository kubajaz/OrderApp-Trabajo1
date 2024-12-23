package org.ulpgc.is1.model;

public class Phone {
    private final String number;

    public Phone(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public boolean isValid() {
        return number.matches("\\d{9}");
    }
}

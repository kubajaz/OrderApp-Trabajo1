package org.ulpgc.is1.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final String surname;
    private final Phone phone;
    private final List<Address> addresses = new ArrayList<>();

    public Customer(String name, String surname, Phone phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Phone getPhone() {
        return phone;
    }

    public void addAddress(Address address) {
        if (!addresses.contains(address)) {
            addresses.add(address);
        }
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}

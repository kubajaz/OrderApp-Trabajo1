package org.ulpgc.is1.model;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private static int NEXT_ID = 0;
    private final int id;
    private final LocalDate date;
    private final Address deliveryAddress;
    private StatusValue status = StatusValue.InProcess;
    private final Customer customer;
    private final List<OrderItem> items;
    private final PromotionalDiscount discount;
    private Payment payment;


    public Order(Customer customer, LocalDate date, Address deliveryAddress, List<OrderItem> items, PromotionalDiscount discount) {
        this.id = NEXT_ID++;
        this.customer = customer;
        this.date = date;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
        this.discount = discount;
    }

    public int price() {
        int total = items.stream().mapToInt(item -> item.getFood().getPrice() * item.getQuantity()).sum();
        if (discount != null) {
            total -= total * discount.getDiscount() / 100;
        }
        return total;
    }

    public void pay(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + "\nCustomer: " + customer.getName() + " " + customer.getSurname() +
                "\nDelivery Address: " + deliveryAddress +
                "\nItems: " + items +
                "\nTotal Price: $" + price() +
                "\nStatus: " + status;
    }
}

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

    public Payment getPayment() {
        return payment;
    }

    @Override
    public String toString() {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("******************************************\n")
                .append(customer.getName()).append(" ").append(customer.getSurname())
                .append(" (").append(customer.getPhone().getNumber()).append(")\n")
                .append("******************************************\n\n")
                .append("--------------------------------------------------------------------\n")
                .append("Order ").append(id).append("\n")
                .append("--------------------------------------------------------------------\n")
                .append("*) Order data:\n")
                .append("|- Code: ").append(id).append("\n")
                .append("|- Made on: ").append(date).append("\n")
                .append("|- Place of the order: ").append(deliveryAddress).append("\n")
                .append("|- State: ").append(status).append("\n")
                .append("|- Payment information: ").append(payment != null ? payment.getAmount() : "Not paid yet").append("\n")
                .append("|- Discount code: ").append(discount != null
                        ? "{" + discount.getCode() + ", " + discount.getFrom() + ", "
                        + discount.getTo() + ", " + discount.getDiscount() + "%}"
                        : "None").append("\n")
                .append("|- Total: $").append(price()).append("\n\n")
                .append("------------------------------------------------------------------\n")
                .append("Order items:\n");

        for (OrderItem item : items) {
            orderDetails.append("________________________________________\n")
                    .append("-) ").append(item.getQuantity()).append(" units of:\n")
                    .append("|- Code: ").append(item.getFood().getNumber()).append("\n")
                    .append("|- Name: ").append(item.getFood().getName()).append("\n")
                    .append("|- Description: ").append(item.getFood().getDescription()).append("\n")
                    .append("|- Price: $").append(item.getFood().getPrice()).append("\n\n");
        }
        return orderDetails.toString();
    }
}

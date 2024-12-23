package org.ulpgc.is1.model;

import java.time.LocalDate;
import java.util.*;

public class OrderManager {
    private final List<Customer> customers = new ArrayList<>();
    private final List<Food> foods = new ArrayList<>();
    private final List<PromotionalDiscount> discounts = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        }
    }

    public Customer getCustomer(String phone) {
        for (Customer c : customers) {
            if (c.getPhone().getNumber().equals(phone)) {
                return c;
            }
        }
        return null;
    }

    public void addFood(Food food) {
        if (!foods.contains(food)) {
            foods.add(food);
        }
    }

    public void addPromotionalDiscount(PromotionalDiscount discount) {
        if (!discounts.contains(discount)) {
            discounts.add(discount);
        }
    }

    // Metoda do złożenia zamówienia
    public Order order(Customer customer, LocalDate date, Address deliveryAddress, PaymentType paymentType, PromotionalDiscount promotionalDiscount, List<Integer> idFood, List<Integer> amount) {
        // Sprawdzanie, czy zniżka jest aktualna
        PromotionalDiscount validDiscount = null;
        if (promotionalDiscount != null && promotionalDiscount.isValid(date)) {
            validDiscount = promotionalDiscount;
        }

        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < idFood.size(); i++) {
            Food food = null;
            for (Food f : foods) {
                if (f.getNumber() == idFood.get(i)) {
                    food = f;
                    break;
                }
            }
            if (food != null) {
                items.add(new OrderItem(food, amount.get(i)));
            }
        }

        Order order = new Order(customer, date, deliveryAddress, items, validDiscount);
        Payment payment = new Payment(LocalDate.now(), order.price(), paymentType);
        order.pay(payment);
        orders.add(order);
        return order;
    }

    // Metoda do uzyskania listy zamówień dla klienta
    public List<Order> getCustomerOrderList(Customer customer) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    // Metoda do płacenia za zamówienie
    public void payOrder(Order order, Payment payment) {
        if (order != null) {
            order.pay(payment);
        }
    }
}

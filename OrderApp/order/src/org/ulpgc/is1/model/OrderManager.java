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

    public Food getFood(int id) {
        for (Food food : foods) {
            if (food.getNumber() == id) {
                return food;
            }
        }
        return null;
    }

    public void addPromotionalDiscount(PromotionalDiscount discount) {
        if (!discounts.contains(discount)) {
            discounts.add(discount);
        }
    }

    public PromotionalDiscount getPromotionalDiscount(int code) {
        for (PromotionalDiscount discount : discounts) {
            if (discount.getCode() == code) {
                return discount;
            }
        }
        return null;
    }

    public Order order(Customer customer, LocalDate date, Address deliveryAddress, PromotionalDiscount promotionalDiscount, List<Integer> idFood, List<Integer> amount) {
        PromotionalDiscount validDiscount = null;
        if (promotionalDiscount != null && promotionalDiscount.isValid(date)) {
            validDiscount = promotionalDiscount;
        }

        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < idFood.size(); i++) {
            Food food = getFood(idFood.get(i));
            if (food != null) {
                items.add(new OrderItem(food, amount.get(i)));
            }
        }

        Order order = new Order(customer, date, deliveryAddress, items, validDiscount);
        orders.add(order);
        return order;
    }

    public void payOrder(Order order, PaymentType paymentType) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null.");
        }
        if (order.getPayment() != null) {
            throw new IllegalStateException("Order has already been paid.");
        }

        Payment payment = new Payment(LocalDate.now(), order.price(), paymentType);
        order.pay(payment);
    }

    public List<Order> getCustomerOrderList(Customer customer) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getCustomer().equals(customer)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }
}

package org.ulpgc.is1.control;

import org.ulpgc.is1.model.*;

import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        OrderManager manager = new OrderManager();

        // Tworzenie klientów
        Customer customer1 = new Customer("Magnus", "Carlsen", new Phone("123456789"));
        Customer customer2 = new Customer("Hikaru", "Nakamura", new Phone("987654321"));
        manager.addCustomer(customer1);
        manager.addCustomer(customer2);

        // Dodanie adresów
        Address address1 = new Address("Main Street", 123, 10100, "Warsaw");
        Address address2 = new Address("High Street", 456, 20200, "Las Palmas");
        customer1.addAddress(address1);
        customer1.addAddress(address2);

        // Tworzenie jedzenia
        Food food1 = new Food(1, "Pizza", "Delicious cheese pizza", 12);
        Food food2 = new Food(2, "Burger", "Juicy beef burger", 10);
        Food food3 = new Food(3, "Salad", "Fresh garden salad", 8);
        manager.addFood(food1);
        manager.addFood(food2);
        manager.addFood(food3);

        // Tworzenie zniżek
        PromotionalDiscount discount1 = new PromotionalDiscount(1, LocalDate.now().minusDays(10), LocalDate.now().minusDays(1), 15);
        PromotionalDiscount discount2 = new PromotionalDiscount(2, LocalDate.now().minusDays(1), LocalDate.now().plusDays(10), 20);
        manager.addPromotionalDiscount(discount1);
        manager.addPromotionalDiscount(discount2);

        // Tworzenie zamówienia
        List<Integer> foodIds = Arrays.asList(1, 3); // Pizza and Salad
        List<Integer> quantities = Arrays.asList(2, 1); // 2x Pizza, 1x Salad
        Order order = manager.order(customer1, LocalDate.now(), address2, discount2, foodIds, quantities);

        System.out.println("Zamówienie złożone:");
        System.out.println(order);

        // Dokonanie płatności
        manager.payOrder(order, PaymentType.Card);
        System.out.println("Płatność dokonana.");
        System.out.println(order);
    }
}

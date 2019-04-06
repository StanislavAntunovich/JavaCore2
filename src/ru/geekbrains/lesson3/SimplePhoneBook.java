package ru.geekbrains.lesson3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SimplePhoneBook {
    private Map<String, HashSet<String>> phoneBook;

    public SimplePhoneBook() {
        phoneBook = new HashMap<>();
    }

    public SimplePhoneBook(Map<String, HashSet<String>> phoneBook) {
        this.phoneBook = phoneBook;
    }

    public void add(String lastName, String phone) {
        HashSet<String> phones = phoneBook.getOrDefault(lastName, new HashSet<>());
        phones.add(phone);
        phoneBook.put(lastName, phones);
    }

    public void get(String lastName) {
        if (phoneBook.containsKey(lastName)) {
            System.out.printf("Абонент: %s, Номер(а): %s%n", lastName, phoneBook.get(lastName));
        } else {
            System.out.printf("Абонент %s не найден%n", lastName);
        }
    }

}

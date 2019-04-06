package ru.geekbrains.lesson3;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClass {

    public static void arrayManipulations() {
        List<String> wordsArr = Arrays.asList("a", "b", "c", "d", "b", "c", "b", "a", "b", "e", "n", "t", "n");

        Map<String, Integer> words = new HashMap<>();

        for (String w : wordsArr) {
            words.put(w, words.getOrDefault(w, 0) + 1);
        }

        System.out.println("Слова из которых состоит массив (без дубликатов): " + words.keySet());
        System.out.println("Уникальные слова: " + words.keySet().stream()
                .filter(v -> words.get(v) == 1)
                .collect(Collectors.toList()));

        for (String w : words.keySet()) {
            System.out.printf("Слово %s встречается %d раз%n", w, words.get(w));
        }
    }

    public static void phoneBookManipulations() {
        SimplePhoneBook book = new SimplePhoneBook();
        book.add("Пупкин", "222-3-222");
        book.add("Пупкин", "222-3-2211");
        book.add("Сипухин", "222-4-2211");

        book.get("Сипухин");
        book.get("Пупкин");
        book.get("Пышкин");
    }

    public static void main(String[] args) {
        arrayManipulations();
        System.out.println();
        phoneBookManipulations();
    }
}

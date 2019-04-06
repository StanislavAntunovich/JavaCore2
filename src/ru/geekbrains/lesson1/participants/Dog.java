package ru.geekbrains.lesson1.participants;

import ru.geekbrains.lesson1.enums.Color;

public class Dog extends Animal {

    public Dog(String name, Color color, int age, int runDistance, int jumpHeight, int swimDistance) {
        super("Собака", name, color, age, runDistance, jumpHeight, swimDistance);
    }
}

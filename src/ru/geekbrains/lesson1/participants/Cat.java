package ru.geekbrains.lesson1.participants;


import ru.geekbrains.lesson1.enums.Color;

public class Cat extends Animal {

    public Cat(String name, Color color, int age, int runDistance, int jumpHeight) {
        super("Кот", name, color, age, runDistance, jumpHeight, 0);
    }


}

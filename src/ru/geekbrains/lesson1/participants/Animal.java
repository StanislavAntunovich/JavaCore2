package ru.geekbrains.lesson1.participants;

import ru.geekbrains.lesson1.enums.Color;

public abstract class Animal implements Participant {

    private String type;

    private String name;
    private Color color;
    private int age;

    private boolean isOnDistance;

    protected int runDistance;
    protected int jumpHeight;
    protected int swimDistance;

    public Animal(String type, String name, Color color, int age, int runDistance, int jumpHeight, int swimDistance) {
        this.type = type;
        this.name = name;
        this.color = color;
        this.age = age;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
        this.isOnDistance = true;
    }


    @Override
    public boolean isOnDistance() {
        return isOnDistance;
    }

    @Override
    public void run(int distance) {
        if (!isOnDistance)
            return;
        if (distance > runDistance) {
            isOnDistance = false;
            System.out.println(type + " " + name + " не пробежал и сошел с дистанции");
            return;
        }
        System.out.println(type + " " + name + " успешно пробежал дистанцию");
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance)
            return;
        if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println(type + " " + name + " не перепрыгнул и сошел с дистанции");
            return;
        }
        System.out.println(type + " " + name + " успешно перепрыгнул");
    }

    @Override
    public void swim(int distance) {
        if (!isOnDistance)
            return;
        if (swimDistance == 0) {
            System.out.println(type + " " + name + " не умеет платавть и сходит с дистанции");
            isOnDistance = false;
            return;
        }
        if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println(type + " " + name + " не проплыл и сошел с дистанции");
            return;
        }
        System.out.println(type + " " + name + " успешно проплыл дистанцию");
    }

    public void getInfo() {
        System.out.println(String.format("%s, имя: %s, возраст: %s, цвет: %s", type, name, age, color.getName()));
    }
}

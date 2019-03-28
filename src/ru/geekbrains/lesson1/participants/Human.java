package ru.geekbrains.lesson1.participants;

public class Human implements Participant {
    private String name;
    private int age;

    private boolean isOnDistance;

    private int runDistance;
    private int jumpHeight;
    private int swimDistance;

    public Human(String name, int age, int runDistance, int jumpHeight, int swimDistance) {
        this.name = name;
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
            System.out.println("Человек " + name + " не пробежал и сошел с дистанции");
            return;
        }
        System.out.println("Человек " + name + " успешно пробежал дистанцию");
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance)
            return;
        if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println("Человек " + name + " не перепрыгнул и сошел с дистанции");
            return;
        }
        System.out.println("Человек " + name + " успешно перепрыгнул");
    }

    @Override
    public void swim(int distance) {
        if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println("Человек " + name + " не проплыл и сошел с дистанции");
            return;
        }
        System.out.println("Человек " + name + " успешно проплыл дистанцию");
    }

    public void getInfo() {
        System.out.println(String.format("Человек, имя: %s, возраст: %s", name, age));
    }

}

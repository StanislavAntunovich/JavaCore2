package ru.geekbrains.lesson1.participants;

public class Robot implements Participant{
    private String model;
    private String version;

    private boolean isOnDistance;

    private int runDistance;
    private int jumpHeight;

    public Robot(String model, String version, int runDistance, int jumpHeight, int swimDistance) {
        this.model = model;
        this.version = version;
        this.runDistance = runDistance;
        this.jumpHeight = jumpHeight;
        this.swimDistance = swimDistance;
        this.isOnDistance = true;
    }

    private int swimDistance;


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
            System.out.println("Робот " + model + " не пробежал и сошел с дистанции");
            return;
        }
        System.out.println("Робот " + model + " успешно пробежал дистанцию " + distance + " метров");
    }

    @Override
    public void jump(int height) {
        if (!isOnDistance)
            return;
        if (height > jumpHeight) {
            isOnDistance = false;
            System.out.println("Робот " + model + " не перепрыгнул и сошел с дистанции");
            return;
        }
        System.out.println("Робот " + model + " успешно перепрыгнул " + height + " метра(ов)");
    }

    @Override
    public void swim(int distance) {
        if (distance > swimDistance) {
            isOnDistance = false;
            System.out.println("Робот " + model + " не проплыл и сошел с дистанции");
            return;
        }
        System.out.println("Робот " + model + " успешно проплыл дистанцию " + distance + " метров");
    }

    public void getInfo() {
        System.out.println(String.format("Робот, модель: %s, версия: %s", model, version));
    }
}

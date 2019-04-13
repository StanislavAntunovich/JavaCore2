package ru.geekbrains.lesson5;

import java.util.Arrays;

public class MainClass {
    static final int SIZE = 10_000_000;
    static final int HALF_SIZE = SIZE / 2;

    private static float[] makeArray() {
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1.0f);
        return arr;
    }

    private static void fillArray() {
        float[] arr = makeArray();
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long finish = System.currentTimeMillis();

        System.out.println("Время выполнения в одном потоке: " + (finish - start));
    }

    private static void fillArrayWithThreads() {
        float[] arr = makeArray();
        long start = System.currentTimeMillis();

        float[] a1 = new float[HALF_SIZE];
        float[] a2 = new float[HALF_SIZE];
        System.arraycopy(arr, 0, a1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, a2, 0, HALF_SIZE);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                a1[i] = (float) (a1[i]
                        * Math.sin(0.2f + i / 5)
                        * Math.cos(0.2f + i / 5)
                        * Math.cos(0.4f + i / 2));
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < HALF_SIZE; i++) {
                a2[i] = (float) (a2[i]
                        * Math.sin(0.2f + (HALF_SIZE + i) / 5)
                        * Math.cos(0.2f + (HALF_SIZE + i) / 5)
                        * Math.cos(0.4f + (HALF_SIZE + i) / 2));}
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);

        long end = System.currentTimeMillis();

        System.out.println("Время выполнения в двух потоках: " + (end - start));
    }

    public static void main(String[] args) {
        fillArray();
        fillArrayWithThreads();

    }

}

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
        calcAndFill(arr, SIZE, false);
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
            calcAndFill(a1, HALF_SIZE, false);
        });

        Thread t2 = new Thread(() -> {
            calcAndFill(a2, HALF_SIZE, true);
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

    /**
     * @param array        array that will be filled with values counted by formula;
     * @param size         size of to be filled array;
     * @param isSecondPart if array is second part of separated to be filled array, i in formula incrementing by size of array's part
     */
    private static void calcAndFill(float[] array, int size, boolean isSecondPart) {
        int incrementer = isSecondPart ? size : 0;
        for (int i = 0; i < size; i++) {
            array[i] = (float) (array[i]
                    * Math.sin(0.2f + (incrementer + i) / 5f)
                    * Math.cos(0.2f + (incrementer + i) / 5f)
                    * Math.cos(0.4f + (incrementer + i) / 2f));
        }
    }

    public static void main(String[] args) {
        fillArray();
        fillArrayWithThreads();
    }

}

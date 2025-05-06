package ru.itis.aisd403.radix_sort;

public class RadixSort {
    public static void radixSort(int[] arr) {
        long startTime = System.nanoTime();
        int max = Radix.getMax(arr);
        int iterations = 0;

        for (int exp = 1; max / exp > 0; exp *= 10) {
            iterations += Radix.countSort(arr, exp);
        }

        System.out.println("Длина входных данных: " + arr.length +
                " Время (мс): " + (double)(System.nanoTime() - startTime) / 1000000 +
                " Количество итераций: " + iterations);
    }
}
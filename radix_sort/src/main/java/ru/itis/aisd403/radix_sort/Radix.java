package ru.itis.aisd403.radix_sort;

public class Radix {
    public static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num >max) max = num;
        }
        return max;
    }

    public static int countSort(int[] arr, int exp) {
        int iterations = 0;
        int[] output = new int[arr.length];
        int[] count = new int[10];

        for (int num : arr) {
            count[(num / exp) % 10]++;
            iterations++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            iterations++;
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
            iterations += 3;
        }

        System.arraycopy(output, 0, arr, 0, arr.length);
        return iterations + arr.length;
    }
}
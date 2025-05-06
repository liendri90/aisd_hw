package ru.itis.aisd403.radix_sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("data.txt"))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                int[] array = Arrays.stream(row.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                RadixSort.radixSort(array);
                System.out.println(Arrays.toString(array));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

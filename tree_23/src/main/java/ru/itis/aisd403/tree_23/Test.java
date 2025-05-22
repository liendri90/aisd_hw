package ru.itis.aisd403.tree_23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void testPerformance(String filename) {
        List<int[]> testData = loadTestData(filename);

        System.out.println("Размер,Вставка(нс),Поиск(нс),Удаление(нс)");

        for (int[] data : testData) {
            TwoThreeTree tree = new TwoThreeTree();

            long insertStart = System.nanoTime();
            for (int key : data) {
                tree.insert(key);
            }
            long insertTime = System.nanoTime() - insertStart;

            long searchStart = System.nanoTime();
            for (int key : data) {
                tree.contains(key);
            }
            long searchTime = System.nanoTime() - searchStart;

            long removeStart = System.nanoTime();
            for (int key : data) {
                tree.remove(key);
            }
            long removeTime = System.nanoTime() - removeStart;

            System.out.printf("%d,%d,%d,%d%n",
                    data.length, insertTime, searchTime, removeTime);
        }
    }

    private static List<int[]> loadTestData(String filename) {
        List<int[]> testData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                int size = Integer.parseInt(parts[0]);
                String[] elements = parts[1].split(",");

                int[] data = new int[size];
                for (int i = 0; i < size; i++) {
                    data[i] = Integer.parseInt(elements[i]);
                }
                testData.add(data);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        return testData;
    }
}
package ru.itis.aisd403.tree_23;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DataGenerator.generateDemoFile("tree_operations_data.txt", 10, 1000, 20);

        processAllDatasets("tree_operations_data.txt");
    }

    private static void processAllDatasets(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int datasetCounter = 0;

            while ((line = reader.readLine()) != null) {
                datasetCounter++;
                System.out.println("\n=== Набор #" + datasetCounter + " ===");

                // Парсинг данных
                String[] parts = line.split(":");
                int size = Integer.parseInt(parts[0]);
                int[] data = Arrays.stream(parts[1].split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();

                // Вывод информации о наборе
                System.out.println("Количество элементов: " + size);

                // Создание и работа с деревом
                TwoThreeTree tree = new TwoThreeTree();

                // Добавление элементов
                long insertTime = measureOperation("Добавление", () -> {
                    for (int num : data) {
                        tree.insert(num);
                    }
                });
                System.out.printf("Размер после добавления: %d\n", tree.size());

                // Поиск элементов
                long searchTime = measureOperation("Поиск", () -> {
                    for (int i = 0; i < Math.min(20, data.length); i++) {
                        tree.contains(data[i]);
                    }
                });

                // Удаление элементов
                int removeCount = Math.min(10, data.length/2);
                long removeTime = measureOperation("Удаление", () -> {
                    for (int i = 0; i < removeCount; i++) {
                        tree.remove(data[i]);
                    }
                });
                System.out.printf("Размер после удаления %d элементов: %d\n",
                        removeCount, tree.size());

                System.out.println("=".repeat(60));
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    private static long measureOperation(String operationName, Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        long duration = System.nanoTime() - start;
        System.out.printf("%s: %d нс\n", operationName, duration);
        return duration;
    }
}
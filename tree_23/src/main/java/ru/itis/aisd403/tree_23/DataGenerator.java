package ru.itis.aisd403.tree_23;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {
    public static void generateDemoFile(String filename, int minSize, int maxSize, int step) {
        try (FileWriter writer = new FileWriter(filename)) {
            Random random = new Random();
            int datasetCount = 0;

            for (int size = minSize; size <= maxSize; size += step) {
                datasetCount++;
                writer.write(size + ":");

                for (int i = 0; i < size; i++) {
                    writer.write(random.nextInt(1000) + (i < size-1 ? "," : ""));
                }
                writer.write("\n");
            }

        } catch (IOException e) {
            System.err.println("Ошибка генерации данных: " + e.getMessage());
        }
    }
}
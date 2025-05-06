package ru.itis.aisd403.radix_sort;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Writer {
    public static void write() {
        Random random = new Random();
        final int ROWS = 50;
        try (FileWriter writer = new FileWriter("data.txt")) {
            for (int i = 0; i < ROWS; i++) {
                int[] row = new int[random.nextInt(10000-100)+100];
                for (int j = 0; j < row.length; j++) {
                    row[j] = random.nextInt(1000);
                }
                writer.write(rowToString(row));
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String rowToString(int[] row) {
        StringBuilder b = new StringBuilder();
        for (int el : row) {
            b.append(el);
            b.append(" ");
        }
        b.append("\n");
        return b.toString();
    }
}
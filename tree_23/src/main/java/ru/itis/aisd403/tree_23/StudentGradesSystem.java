package ru.itis.aisd403.tree_23;

import java.util.Scanner;

public class StudentGradesSystem {
    public static void main(String[] args) {
        TwoThreeTree gradeTree = new TwoThreeTree();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Система учета оценок студентов");
        System.out.println("------------------------------------------");

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить оценку");
            System.out.println("2. Проверить наличие оценки");
            System.out.println("3. Удалить оценку");
            System.out.println("4. Показать количество оценок");
            System.out.println("5. Вывести все оценки (в порядке возрастания)");
            System.out.println("6. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите оценку для добавления (0-100): ");
                    int gradeToAdd = scanner.nextInt();
                    if (gradeToAdd < 0 || gradeToAdd > 100) {
                        System.out.println("Оценка должна быть в диапазоне 0-100!");
                    } else if (gradeTree.insert(gradeToAdd)) {
                        System.out.println("Оценка " + gradeToAdd + " успешно добавлена.");
                    } else {
                        System.out.println("Оценка " + gradeToAdd + " уже существует в системе.");
                    }
                    break;

                case 2:
                    System.out.print("Введите оценку для поиска: ");
                    int gradeToFind = scanner.nextInt();
                    System.out.println("Оценка " + gradeToFind + " " + (gradeTree.contains(gradeToFind) ? "найдена." : "не найдена."));
                    break;

                case 3:
                    System.out.print("Введите оценку для удаления: ");
                    int gradeToRemove = scanner.nextInt();
                    if (gradeTree.remove(gradeToRemove)) {
                        System.out.println("Оценка " + gradeToRemove + " успешно удалена.");
                    } else {
                        System.out.println("Оценка " + gradeToRemove + " не найдена в системе.");
                    }
                    break;

                case 4:
                    System.out.println("Общее количество оценок: " + gradeTree.size());
                    break;

                case 5:
                    System.out.println("Все оценки в системе:");
                    printInOrder(gradeTree.getRoot());
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Выход из системы...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // Вспомогательный метод для обхода дерева в порядке возрастания
    private static void printInOrder(TreeNode node) {
        if (node != null) {
            if (node.isTwoNode) {
                printInOrder(node.left);
                System.out.print(node.key1 + " ");
                printInOrder(node.right);
            } else {
                printInOrder(node.left);
                System.out.print(node.key1 + " ");
                printInOrder(node.middle);
                System.out.print(node.key2 + " ");
                printInOrder(node.right);
            }
        }
    }
}


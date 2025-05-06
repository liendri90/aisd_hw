package task3;

import java.util.Stack;

public class RemoveDublicates {
    public static int[] removeDuplicates(int[] sortedArray) {
        if (sortedArray.length == 0) {
            return new int[0];
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(sortedArray[0]);

        for (int i = 1; i < sortedArray.length; i++) {
            if (sortedArray[i] != stack.peek()) {
                stack.push(sortedArray[i]);
            }
        }

        int[] result = new int[stack.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        // Пример использования
        int[] testArray = {1, 1, 2, 2, 2, 3, 4, 4, 5};
        int[] uniqueArray = removeDuplicates(testArray);

        // Вывод результата
        System.out.print("Массив без дубликатов: ");
        for (int num : uniqueArray) {
            System.out.print(num + " ");
        }
    }
}

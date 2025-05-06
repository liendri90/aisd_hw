package task4;

import java.util.LinkedList;
import java.util.Queue;

public class MergeSortedQueues {
    public class MergeQueues {
        public static void main(String[] args) {
            // Создаем и заполняем первую очередь
            Queue<Integer> q1 = new LinkedList<>();
            q1.add(1);
            q1.add(3);
            q1.add(5);

            // Создаем и заполняем вторую очередь
            Queue<Integer> q2 = new LinkedList<>();
            q2.add(2);
            q2.add(4);
            q2.add(6);

            // Объединяем очереди
            Queue<Integer> merged = mergeQueues(q1, q2);

            // Выводим результат
            System.out.println("Объединенная очередь: " + merged);
        }

        public static Queue<Integer> mergeQueues(Queue<Integer> q1, Queue<Integer> q2) {
            Queue<Integer> merged = new LinkedList<>();

            // Пока обе очереди не пусты
            while (!q1.isEmpty() && !q2.isEmpty()) {
                // Сравниваем первые элементы и добавляем меньший
                if (q1.peek() <= q2.peek()) {
                    merged.add(q1.poll());
                } else {
                    merged.add(q2.poll());
                }
            }

            // Добавляем оставшиеся элементы из q1
            while (!q1.isEmpty()) {
                merged.add(q1.poll());
            }

            // Добавляем оставшиеся элементы из q2
            while (!q2.isEmpty()) {
                merged.add(q2.poll());
            }

            return merged;
        }
    }
}
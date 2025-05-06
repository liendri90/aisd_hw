package task5;
import java.util.Stack;

public class Main {
    public static int[] z5(int[] arr) {
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        int[] result = new int[arr.length];

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            } else {
                left[i] = 0;
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                right[i] = stack.peek();
            } else {
                right[i] = 0;
            }
            stack.push(i);
        }

        for (int i = 0; i < arr.length; i++) {
            if (left[i] == 0 && right[i] == 0) {
                result[i] = 0;
            } else if (left[i] == 0) {
                result[i] = right[i];
            } else if (right[i] == 0) {
                result[i] = left[i];
            } else {
                int leftRes = i - left[i];
                int rightRes = right[i] - (i);
                result[i] = (leftRes <= rightRes) ? left[i] : right[i];
            }
        }

        return result;
    }

}

package task2;

import java.util.Stack;

public class Calculator {
    public static int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        String[] tokens = s.trim().split("\\s+");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                stack.push(Integer.parseInt(token));
            } else if ("+-*".contains(token)) {
                int b = stack.pop();
                int a = stack.pop();
                int res = 0;
                switch (token) {
                    case "+": res = a + b; break;
                    case "-": res = a - b; break;
                    case "*": res = a * b; break;
                }
                stack.push(res);
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        String expression1 = "3 4 + 2 *"; // (3 + 4) * 2 = 14
        System.out.println(calculate(expression1));

        String expression2 = "5 1 2 + 4 * + 3 -"; // 5 + (1 + 2) * 4 - 3 = 14
        System.out.println(calculate(expression2));
    }
}





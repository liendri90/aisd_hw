package ru.itis.aisd403.hw_stack;

import java.util.Stack;

public class ValidBrackets {
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char lastOpen = stack.pop();
                if ((c == ')' && lastOpen != '(') ||
                        (c == '}' && lastOpen != '{') ||
                        (c == ']' && lastOpen != '[')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("([{}])"));
        System.out.println(isValid("(((((())))))"));
        System.out.println(isValid("[]{}"));
        System.out.println(isValid("(("));
        System.out.println(isValid("([{]})"));
        System.out.println(isValid("((((((())"));
    }
}
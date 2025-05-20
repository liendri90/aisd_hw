package task2;

public class ReverseWords {
    public static String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        char[] chars = s.toCharArray();
        int n = chars.length;

        reverse(chars, 0, n - 1);

        reverseWords(chars, n);

        return cleanSpaces(chars, n);
    }

    private static void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left++] = chars[right];
            chars[right--] = temp;
        }
    }

    private static void reverseWords(char[] chars, int n) {
        int start = 0;
        while (start < n) {
            while (start < n && chars[start] == ' ') {
                start++;
            }
            if (start >= n) break;

            int end = start;
            while (end < n && chars[end] != ' ') {
                end++;
            }

            reverse(chars, start, end - 1);

            start = end;
        }
    }

    private static String cleanSpaces(char[] chars, int n) {
        int i = 0, j = 0;
        while (j < n) {
            while (j < n && chars[j] == ' ') j++;
            if (j >= n) break;

            if (i != 0) {
                chars[i++] = ' ';
            }

            while (j < n && chars[j] != ' ') {
                chars[i++] = chars[j++];
            }
        }
        return new String(chars, 0, i);
    }

    public static void main(String[] args) {
        String s1 = "the sky is blue";
        System.out.println(reverseWords(s1));

        String s2 = "this is ib";
        System.out.println(reverseWords(s2));
    }
}

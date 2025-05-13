package task5;

public class GCDCalulator {
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        int num1 = 56;
        int num2 = 98;

        int result = gcd(num1, num2);
        System.out.println(result);
    }
}


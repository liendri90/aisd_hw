package task1;

public class LogFactorial {
    public static void main(String[] args) {
        int N = 3;
        double result = logFactorial(N);
        System.out.println(result);
    }

    public static double logFactorial(int N) {
        if (N == 1) {
            return 0.0;
        } else {
            return Math.log(N) + logFactorial(N - 1);
        }
    }
}
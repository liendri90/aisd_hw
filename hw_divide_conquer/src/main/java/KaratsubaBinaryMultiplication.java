
//X=A⋅2^(n/2)+B, Y=C⋅2^(n/2)+D  A,B,C,D — числа длины n/2
// P1=A⋅C, P2=B⋅D, P3=(A+B)⋅(C+D)
// X⋅Y=P1⋅2^n+(P3−P1−P2)⋅2^(n/2)+P2

public class KaratsubaBinaryMultiplication {
    public static void main(String[] args) {
        String x = "1101"; // 13 в двоичной
        String y = "1010"; // 10 в двоичной
        String result = karatsubaMultiply(x, y);
        System.out.println(result); // Вывод: "1000010" (130 в двоичной)
    }

    public static String karatsubaMultiply(String x, String y) {
        int n = Math.max(x.length(), y.length());

        int m = 1;
        while (m < n) {
            m <<= 1;
        }
        x = padZeros(x, m);
        y = padZeros(y, m);


        if (m == 1) {
            return multiplySingleBit(x, y);
        }

        int half = m / 2;


        String A = x.substring(0, half);
        String B = x.substring(half);
        String C = y.substring(0, half);
        String D = y.substring(half);


        String P1 = karatsubaMultiply(A, C);
        String P2 = karatsubaMultiply(B, D);
        String A_plus_B = binaryAddition(A, B);
        String C_plus_D = binaryAddition(C, D);
        String P3 = karatsubaMultiply(A_plus_B, C_plus_D);


        String temp = binarySubtraction(P3, P1);
        temp = binarySubtraction(temp, P2);


        String term1 = P1 + zeros(m);
        String term2 = temp + zeros(half);
        String sum = binaryAddition(term1, term2);
        return binaryAddition(sum, P2);
    }


    private static String padZeros(String s, int length) {
        while (s.length() < length) {
            s = "0" + s;
        }
        return s;
    }


    private static String zeros(int n) {
        return new String(new char[n]).replace("\0", "0");
    }


    private static String multiplySingleBit(String a, String b) {
        return (a.charAt(0) == '1' && b.charAt(0) == '1') ? "1" : "0";
    }


    public static String binaryAddition(String a, String b) {
        int maxLen = Math.max(a.length(), b.length());
        a = padZeros(a, maxLen);
        b = padZeros(b, maxLen);

        StringBuilder result = new StringBuilder();
        int carry = 0;

        for (int i = maxLen - 1; i >= 0; i--) {
            int sum = (a.charAt(i) - '0') + (b.charAt(i) - '0') + carry;
            result.append(sum % 2);
            carry = sum / 2;
        }

        if (carry != 0) {
            result.append(carry);
        }

        return result.reverse().toString();
    }


    public static String binarySubtraction(String a, String b) {
        int maxLen = Math.max(a.length(), b.length());
        a = padZeros(a, maxLen);
        b = padZeros(b, maxLen);

        StringBuilder result = new StringBuilder();
        int borrow = 0;

        for (int i = maxLen - 1; i >= 0; i--) {
            int sub = (a.charAt(i) - '0') - (b.charAt(i) - '0') - borrow;
            if (sub < 0) {
                sub += 2;
                borrow = 1;
            } else {
                borrow = 0;
            }
            result.append(sub);
        }

        String res = result.reverse().toString().replaceFirst("^0+(?!$)", "");
        return res.isEmpty() ? "0" : res;
    }

}
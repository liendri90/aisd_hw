package task3;

import java.util.Arrays;
import java.util.Comparator;

public class LargestNumber {
    public static String largestNumber(int[] nums) {
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStrings, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String order1 = b + a;
                String order2 = a + b;
                return order1.compareTo(order2);
            }
        });

        if (numStrings[0].equals("0")) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        for (String numStr : numStrings) {
            result.append(numStr);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 30, 34, 5, 9};
        System.out.println(largestNumber(nums1));

        int[] nums2 = {0, 0};
        System.out.println(largestNumber(nums2));
    }
}

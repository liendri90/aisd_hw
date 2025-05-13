package task2;
import java.util.HashMap;
import java.util.Map;

public class ArrayDublicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> numToIndex = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (numToIndex.containsKey(num) && i - numToIndex.get(num) <= k) {
                return true;
            }
            numToIndex.put(num, i);
        }
        return false;
    }

    public static void main(String[] args) {
        ArrayDublicate arrayDublicate = new ArrayDublicate();
        int[] nums = {1, 2, 3, 1, 5};
        int k = 3;
        System.out.println(arrayDublicate.containsNearbyDuplicate(nums, k));
    }
}

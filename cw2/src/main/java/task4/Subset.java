package task4;

public class Subset {
    private static boolean helper(int[] nums,int target, int s, int index) {
        if (s == target) return true;
        if (index >= nums.length || s > target) return false;

        return helper(nums,target,s - nums[index], index + 1) ||
                helper(nums,target, s, index + 1);
    }
    public static void main(String[] args) {
        int[] nums = {3, 34, 4, 12, 5, 2};
        int s = 9;


    }
}

package task1;

public class FindTransitionPoint {
    public static int findTransitionPoint(int[] nums) {
        if (nums == null || nums.length < 2) {
            return -1;
        }

        int l = 0;
        int r = nums.length-1;

        while (l <= r) {
            int m = l+(r-l)/2;
            if (m > 0 && nums[m] < nums[m-1] && nums[m] < nums[m+1]) {
                return m;
            }

            if (nums[m] < nums[m-1]) {
                l = m+1;
            } else {
                r = m-1;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {10, 8, 5, 3, 1, 2, 4, 6, 7};
        int j = findTransitionPoint(nums);
        System.out.println(j);
    }
}

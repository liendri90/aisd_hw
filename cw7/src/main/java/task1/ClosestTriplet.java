package task1;

import java.util.Arrays;

public class ClosestTriplet {
    public static int[] findClosestTriplet(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        int[] result = new int[]{nums[0], nums[1], nums[2]};

        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                int desired = target - nums[i] - nums[j];
                int k = binarySearchClosest(nums, j + 1, n - 1, desired);
                int currentSum = nums[i] + nums[j] + nums[k];

                if (Math.abs(currentSum - target) < Math.abs(closestSum - target)) {
                    closestSum = currentSum;
                    result = new int[]{nums[i], nums[j], nums[k]};
                }
            }
        }
        return result;
    }

    private static int binarySearchClosest(int[] nums, int left, int right, int target) {
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (left > 0 && Math.abs(nums[left-1] - target) < Math.abs(nums[left] - target)) {
            return left - 1;
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 1;
        int[] result = findClosestTriplet(nums, target);
        System.out.println("Ближайшая тройка: " + Arrays.toString(result));
    }
}
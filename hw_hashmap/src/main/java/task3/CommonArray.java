package task3;

import java.util.*;

public class CommonArray {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> freq1 = createFrequencyMap(nums1);
        Map<Integer, Integer> freq2 = createFrequencyMap(nums2);

        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : freq1.entrySet()) {
            int num = entry.getKey();
            int count1 = entry.getValue();

            if (freq2.containsKey(num)) {
                int count2 = freq2.get(num);
                int minCount = Math.min(count1, count2);
                for (int i = 0; i < minCount; i++) {
                    result.add(num);
                }
            }
        }

        int[] arr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }
        return arr;
    }

    private Map<Integer, Integer> createFrequencyMap(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap;
    }

    public static void main(String[] args) {
        CommonArray commonArray = new CommonArray();
        int[] nums1 = {2, 4, 4, 1};
        int[] nums2 = {2, 2, 4, 5};
        int[] result = commonArray.intersect(nums1, nums2);
        System.out.println(Arrays.toString(result));
    }
}
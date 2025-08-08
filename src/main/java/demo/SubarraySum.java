package demo;

import java.util.HashMap;
import java.util.Map;

public class SubarraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        int k = 3;
        System.out.println(subarraySum(nums, k));
    }

    private static int subarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cnt = 0;
        for (int num : nums) {
            sum += num;
            cnt += map.getOrDefault(sum - k, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}

package demo;

public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }

    private static int maxSubArray(int[] nums) {
        int curMax = 0;
        int gloMax = 0;
        for (int i = 0; i < nums.length; i++) {
            curMax = Math.max(nums[i], curMax + nums[i]);
            gloMax = Math.max(curMax, gloMax);
        }
        return gloMax;
    }
}

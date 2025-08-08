package demo.dp;

public class CanPartition {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 5};
        System.out.println(canPartition(nums));
    }

    private static boolean canPartition(int[] nums) {
        int len = nums.length;
        if (len < 2) {
            return false;
        }
        int max = 0;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(nums[i], max);
            sum += nums[i];
        }
        if (sum % 2 != 0) {
            return false;
        }
        int target = sum / 2;
        if (max > target) {
            return false;
        }
        boolean[] dp = new boolean[target + 1];
        // 如果不选取任何正整数，则被选取的正整数之和等于 0，所以为true
        dp[0] = true;
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            // 判断和为num到target区间是否选择num
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
}

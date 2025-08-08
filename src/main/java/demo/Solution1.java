package demo;

import java.util.Scanner;

public class Solution1 {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = null;
        int a = 0;
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            a = in.nextInt();
            nums = new int[a];
            break;
        }
        for (int i = 0; i < a; i++) {
            while (in.hasNextInt()) {
                int b = in.nextInt();
                nums[i] = b;
                break;
            }
        }
        System.out.println(total(nums));
    }

    private static int total(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 2 < nums.length) {
                for (int j = i + 2; j < nums.length; j++) {
                    max = Math.max(nums[i], nums[i] + nums[j]);
                }
            }
        }
        return max;
    }

}

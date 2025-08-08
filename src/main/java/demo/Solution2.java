package demo;

import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] nums = null;
        int a = 0;
        int b = 0;
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            a = in.nextInt();
            b = in.nextInt();
            nums = new int[a][b];
            break;
        }
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < b; j++) {
                int c = in.nextInt();
                nums[i][j] = c;
            }
        }
        System.out.println(total(nums));
    }


    private static int total(int[][] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                max = Math.max(nums[i][j], max);
            }
        }
        return max;
    }
}

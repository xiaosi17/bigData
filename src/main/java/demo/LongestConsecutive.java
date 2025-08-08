package demo;

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutive {
    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[]{100, 4, 200, 1, 3, 2}));
    }

    private static int longestConsecutive(int[] ints) {
        Set<Integer> set = new HashSet<>();
        for (int i : ints) {
            set.add(i);
        }
        int maxLen = 0;
        for (int i : ints) {
            int currentNum = i;
            int currentNumLen = 1;
            // 检查是否是序列起点
            if (!set.contains((i - 1))) {
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentNumLen++;
                }
                maxLen = Math.max(maxLen, currentNumLen);
            }
        }
        return maxLen;


    }
}

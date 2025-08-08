package demo;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class MaxSlidingWindow {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(maxSlidingWindow(nums, k));
    }

    private static List<Integer> maxSlidingWindow(int[] num, int k) {
        List<Integer> res = new ArrayList<>();
        if (num.length < k) {
            return res;
        }
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            while (!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }
            while (!q.isEmpty() && num[q.peekLast()] < num[i]) {
                q.pollLast();
            }
            q.offerLast(i);
            if (i >= k - 1) {
                res.add(num[q.peekFirst()]);
            }
        }
        return res;
    }
}

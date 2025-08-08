package demo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Merge {
    public static void main(String[] args) {
        int[][] intervals = new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        LinkedList<int[]> merge = merge(intervals);
        for (int i = 0; i < merge.size(); i++) {
            System.out.println(Arrays.stream(merge.get(i)).boxed().collect(Collectors.toList()));
        }
    }

    private static LinkedList<int[]> merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merge = new LinkedList<>();
        for (int[] interval : intervals) {
            if (merge.isEmpty() || merge.getLast()[1] < interval[0]) {
                merge.add(interval);
            } else {
                merge.getLast()[1] = Math.max(merge.getLast()[1], interval[1]);
            }
        }
        return merge;
    }


}

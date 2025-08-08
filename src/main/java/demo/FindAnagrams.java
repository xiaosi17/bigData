package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAnagrams {
    public static void main(String[] args) {
        String a = "cbaebabacd";
        String b = "abc";
        System.out.println(findAnagrams(a, b));
    }

    private static List<Integer> findAnagrams(String a, String b) {
        List<Integer> res = new ArrayList<>();
        if (a.length() < b.length()) {
            return res;
        }
        for (int i = 0; i < a.length() - b.length() + 1; i++) {
            int blen = b.length();
            String str = a.substring(i, i + blen);
            char[] achars = str.toCharArray();
            Arrays.sort(achars);
            char[] bchars = b.toCharArray();
            Arrays.sort(bchars);
            if (String.valueOf(achars).equals(String.valueOf(bchars))) {
                res.add(i);
            }
        }
        return res;
    }
}

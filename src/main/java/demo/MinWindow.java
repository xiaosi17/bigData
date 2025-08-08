package demo;

import java.util.HashMap;
import java.util.Map;

public class MinWindow {
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }

    private static String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        ;
        Map<Character, Integer> tmap = new HashMap<>();
        char[] t1 = t.toCharArray();
        char[] s1 = s.toCharArray();
        for (char c : t1) {
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        }
        int tlen = t.length();
        int slen = s.length();

        int left = 0;
        int right = 0;
        int distance = 0;
        int minLen = Integer.MAX_VALUE;
        String res = "";
        Map<Character, Integer> smap = new HashMap<>();
        while (right < slen) {
            char c = s.charAt(right);
            smap.put(c, smap.getOrDefault(c, 0) + 1);
            if (tmap.containsKey(c) && tmap.get(c).intValue() == smap.get(c).intValue()) {
                distance++;
            }
            while (left <= right && distance == tlen) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    res = s.substring(left, right + 1);
                }
                smap.put(s1[left], smap.getOrDefault(s1[left], 0) - 1);
                if (tmap.containsKey(s1[left]) && tmap.get(s1[left]) > smap.get(s1[left])) {
                    distance--;
                }
                left++;
            }
            right++;
        }
        return res;
    }
}

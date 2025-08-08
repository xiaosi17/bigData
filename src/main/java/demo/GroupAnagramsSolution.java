package demo;

import java.util.*;

public class GroupAnagramsSolution {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    private static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs){
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key=new String(chars);
            map.computeIfAbsent(key,k->new ArrayList<>()).add(str);

        }
        return new ArrayList<>(map.values());
    }
}

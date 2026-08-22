import java.util.*;

// Group Anagrams
// Group an array of strings into sets where every string in a set is an anagram of the others.
//
// Sort each string's characters to build a canonical key, then group
// original strings by that key in a hash map.
//
// Time: O(n * k log k), Space: O(n * k)
class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}

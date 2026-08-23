import java.util.*;

// Group Anagrams
// Group an array of strings into sets where every string in a set is an anagram of the others.
//
// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["eat","tea","ate"],["tan","nat"],["bat"]]
//
// Sort each string's characters to build a canonical key, then group
// original strings by that key in a hash map.
//
// Trace with strs = ["eat","tea","tan","ate","nat","bat"] (sorted-letters key):
//   eat -> "aet"   tea -> "aet"   tan -> "ant"
//   ate -> "aet"   nat -> "ant"   bat -> "abt"
//   groups: "aet"->[eat,tea,ate], "ant"->[tan,nat], "abt"->[bat]
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

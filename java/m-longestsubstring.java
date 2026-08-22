import java.util.HashMap;
import java.util.Map;

// Longest Substring Without Repeating Characters
// Find the length of the longest substring with no repeated characters.
//
// Sliding window with a map of last-seen index per character; shrink the
// window's start whenever a repeat is found inside it.
//
// Time: O(n), Space: O(min(n, charset))
class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> seen = new HashMap<>();
        int start = 0;
        int maxLen = 0;

        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (seen.containsKey(c) && seen.get(c) >= start) start = seen.get(c) + 1;
            seen.put(c, end);
            maxLen = Math.max(maxLen, end - start + 1);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("pwwkew")); // 3
    }
}

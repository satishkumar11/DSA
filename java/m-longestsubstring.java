// hellointerview: https://www.hellointerview.com/learn/code/sliding-window/longest-substring-without-repeating-characters
import java.util.HashMap;
import java.util.Map;

// Longest Substring Without Repeating Characters
// Find the length of the longest substring with no repeated characters.
//
// Input: s = "abcabcbb"
// Output: 3
//
// Sliding window with a map of last-seen index per character; on a repeat,
// pull the window's left edge past that character's earlier position -
// Math.max keeps left from ever moving backward on a stale/older match.
//
// Trace with s = "abcabcbb":
//   a(0): new -> seen={a:0}, window "a", best=1
//   b(1): new -> seen={a:0,b:1}, window "ab", best=2
//   c(2): new -> seen={a:0,b:1,c:2}, window "abc", best=3
//   a(3): seen at 0 -> left=max(0,0+1)=1, window "bca", best stays 3
//   b(4): seen at 1 -> left=max(1,1+1)=2, window "cab", best stays 3
//   c(5): seen at 2 -> left=max(2,2+1)=3, window "abc", best stays 3
//   b(6): seen at 4 -> left=max(3,4+1)=5, window "cb",  best stays 3
//   b(7): seen at 6 -> left=max(5,6+1)=7, window "b",   best stays 3
//   best = 3
//
// Time: O(n), Space: O(min(n, charset))
class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> seen = new HashMap<>();
        int n = s.length();
        int left = 0;
        int best = 0;

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            if (seen.containsKey(ch)) {
                left = Math.max(left, seen.get(ch) + 1);
            }
            seen.put(ch, right);
            best = Math.max(best, right - left + 1);
        }

        return best;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("pwwkew")); // 3
    }
}

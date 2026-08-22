import java.util.HashMap;
import java.util.Map;

// Minimum Window Substring
// Find the smallest substring of s that contains every character of t.
//
// Sliding window with a need/have character count; expand the right edge
// until valid, then shrink the left edge to find the smallest valid window.
//
// Time: O(n + m), Space: O(charset)
class MinWindowSubstring {
    public static String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);

        int required = need.size();
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();
        int l = 0, resLen = Integer.MAX_VALUE, resStart = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            windowCounts.merge(c, 1, Integer::sum);
            if (need.containsKey(c) && windowCounts.get(c).intValue() == need.get(c).intValue()) formed++;

            while (formed == required) {
                if (r - l + 1 < resLen) {
                    resLen = r - l + 1;
                    resStart = l;
                }
                char lc = s.charAt(l);
                windowCounts.put(lc, windowCounts.get(lc) - 1);
                if (need.containsKey(lc) && windowCounts.get(lc) < need.get(lc)) formed--;
                l++;
            }
        }

        return resLen == Integer.MAX_VALUE ? "" : s.substring(resStart, resStart + resLen);
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // "BANC"
    }
}

import java.util.HashMap;
import java.util.Map;

// Minimum Window Substring
// Find the smallest substring of s that contains every character of t.
//
// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
//
// Sliding window with a need/have character count; expand the right edge
// until valid, then shrink the left edge to find the smallest valid window.
//
// Trace with s = "ADOBECODEBANC", t = "ABC" (need: A=1,B=1,C=1):
//   expand r=0..5 ("ADOBEC"): once C is added, formed=3=required -> shrink
//     from the left: only 'A' at l=0 is essential, so shrinking stops right
//     after removing it -> first valid window "ADOBEC" (len 6), best so far
//   expand r=6..9 ("ODEB"): rebuilds toward a valid window but doesn't
//     re-trigger formed=3 until another 'A' arrives
//   expand r=10 ('A'): formed=3 again -> shrink from l=1: removes D, O, B,
//     E (none essential) then hits 'C' -> formed drops to 2, shrink stops;
//     no length improvement found this round (window was already too big)
//   expand r=11..12 ("NC"): formed=3 again once C arrives -> shrink from
//     l=6: removes O, D, E (not essential), then B - window "BANC" (len 4)
//     beats the previous best (6), then removing B itself breaks formed
//   final smallest window: "BANC"
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

    // Simpler version: try every possible start, and for each one, just keep
    // extending right until the window is valid - stop at the first valid
    // window for that start (extending further only makes it bigger). No
    // need to reason about when to shrink.
    //
    // Time: O(n^2), Space: O(charset)
    public static String minWindowSimple(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";

        Map<Character, Integer> need = new HashMap<>();
        for (char c : t.toCharArray()) need.merge(c, 1, Integer::sum);
        int required = need.size();

        String best = "";

        for (int start = 0; start < s.length(); start++) {
            Map<Character, Integer> window = new HashMap<>();
            int formed = 0;

            for (int end = start; end < s.length(); end++) {
                char c = s.charAt(end);
                window.merge(c, 1, Integer::sum);
                if (need.containsKey(c) && window.get(c).intValue() == need.get(c).intValue()) formed++;

                if (formed == required) {
                    String candidate = s.substring(start, end + 1);
                    if (best.isEmpty() || candidate.length() < best.length()) best = candidate;
                    break; // shortest window starting here - no point extending further
                }
            }
        }

        return best;
    }

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC")); // "BANC"
        System.out.println(minWindowSimple("ADOBECODEBANC", "ABC")); // "BANC"
    }
}

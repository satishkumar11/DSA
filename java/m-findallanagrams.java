import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Find All Anagrams in a String
// Given strings s and p, find every starting index in s where a substring of
// length p.length is an anagram of p (same letters, any order).
//
// Input: s = "cbaebabacd", p = "abc"  (p.length = 3, so check every length-3 window of s)
// Output: [0, 6]
//   index 0 -> s[0..2] = "cba" -> an anagram of "abc"
//   index 6 -> s[6..8] = "bac" -> an anagram of "abc"
//
// Sliding window of p's length over s, comparing 26-letter frequency
// counts and recording every position where they match exactly.
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
class FindAllAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] patternCounts = new int[26];
        int[] windowCounts = new int[26];

        for (char c : p.toCharArray()) patternCounts[c - 'a']++;

        // build the first window and check it
        for (int i = 0; i < p.length(); i++) {
            windowCounts[s.charAt(i) - 'a']++;
        }
        if (Arrays.equals(patternCounts, windowCounts)) result.add(0);

        // slide the window one character at a time: drop the char leaving on the
        // left, add the char entering on the right, then check the new window
        for (int start = 1; start <= s.length() - p.length(); start++) {
            int charLeaving = s.charAt(start - 1) - 'a';
            int charEntering = s.charAt(start + p.length() - 1) - 'a';

            windowCounts[charLeaving]--;
            windowCounts[charEntering]++;

            if (Arrays.equals(patternCounts, windowCounts)) result.add(start);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc")); // [0, 6]
    }
}

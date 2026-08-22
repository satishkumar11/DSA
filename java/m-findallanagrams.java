import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Find All Anagrams in a String
// Find all starting indices of p's anagrams in s.
//
// Input: s = "cbaebabacd", p = "abc"
// Output: [0, 6]
//
// Sliding window of p's length over s, comparing 26-letter frequency
// counts and recording every position where they match exactly.
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
class FindAllAnagrams {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : p.toCharArray()) need[c - 'a']++;

        for (int i = 0; i < s.length(); i++) {
            window[s.charAt(i) - 'a']++;
            if (i >= p.length()) window[s.charAt(i - p.length()) - 'a']--;
            if (i >= p.length() - 1 && Arrays.equals(need, window)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(findAnagrams("cbaebabacd", "abc")); // [0, 6]
    }
}

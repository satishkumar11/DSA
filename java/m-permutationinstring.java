import java.util.Arrays;

// Permutation in String
// Determine if one string contains a permutation of another as a substring.
//
// Input: s1 = "ab", s2 = "eidbaooo"
// Output: true
//
// Sliding window of s1's length over s2, comparing 26-letter frequency
// counts of the window against s1's counts at every position.
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
class PermutationInString {
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] need = new int[26];
        int[] window = new int[26];

        for (char c : s1.toCharArray()) need[c - 'a']++;

        for (int i = 0; i < s2.length(); i++) {
            window[s2.charAt(i) - 'a']++;
            if (i >= s1.length()) window[s2.charAt(i - s1.length()) - 'a']--;
            if (i >= s1.length() - 1 && Arrays.equals(need, window)) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo")); // true
        System.out.println(checkInclusion("ab", "eidboaoo")); // false
    }
}

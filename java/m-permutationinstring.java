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
// Trace with s1 = "ab" (need: a=1,b=1), s2 = "eidbaooo":
//   i=0 'e': window={e:1} - window too short to check yet (i < s1.length-1)
//   i=1 'i': window={e:1,i:1} - doesn't match need
//   i=2 'd': 'e' leaves, 'd' enters -> window={i:1,d:1} - doesn't match
//   i=3 'b': 'i' leaves, 'b' enters -> window={d:1,b:1} - doesn't match
//   i=4 'a': 'd' leaves, 'a' enters -> window={b:1,a:1} - matches need! -> true
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

    // Simpler (less efficient) alternative: sort s1 once, then for every window
    // of the same length in s2, sort that window too and compare directly - a
    // permutation is just a rearrangement, so two strings with the same sorted
    // form are permutations of each other. No frequency arrays or sliding-window
    // bookkeeping needed, just a direct check at every position.
    //
    // Time: O(n * m log m), Space: O(m)
    public static boolean checkInclusionSimple(String s1, String s2) {
        char[] s1Chars = s1.toCharArray();
        Arrays.sort(s1Chars);
        String sortedS1 = new String(s1Chars);

        for (int i = 0; i + s1.length() <= s2.length(); i++) {
            char[] windowChars = s2.substring(i, i + s1.length()).toCharArray();
            Arrays.sort(windowChars);
            if (new String(windowChars).equals(sortedS1)) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo")); // true
        System.out.println(checkInclusion("ab", "eidboaoo")); // false
        System.out.println(checkInclusionSimple("ab", "eidbaooo")); // true
        System.out.println(checkInclusionSimple("ab", "eidboaoo")); // false
    }
}

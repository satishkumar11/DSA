// Valid Anagram
// Determine if string t is an anagram of string s (same characters, same frequency).
//
// Input: s = "anagram", t = "nagaram"
// Output: true
//
// Count character frequencies of both strings into a single 26-slot array,
// incrementing for s and decrementing for t; all zeros means an anagram.
//
// Trace with s = "anagram", t = "nagaram" (+1 per s char, -1 per t char):
//   a/n -> counts[a]=1, counts[n]=-1
//   n/a -> counts[n]=0,  counts[a]=0
//   a/g -> counts[a]=1, counts[g]=-1
//   g/a -> counts[g]=0,  counts[a]=0
//   r/r, a/a, m/m -> each pair cancels itself out
//   every slot ends at 0 -> true
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
            counts[t.charAt(i) - 'a']--;
        }

        for (int c : counts) {
            if (c != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram")); // true
        System.out.println(isAnagram("rat", "car")); // false
    }
}

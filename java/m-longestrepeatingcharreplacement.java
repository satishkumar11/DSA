// hellointerview: https://www.hellointerview.com/learn/code/sliding-window/longest-repeating-character-replacement
// Longest Repeating Character Replacement
// Find the longest substring achievable by replacing at most k characters with the same character.
//
// Input: s = "ABAB", k = 2
// Output: 4
//
// Sliding window tracking the count of the most frequent character
// inside it; shrink the window whenever replacements needed exceed k.
//
// Trace with s = "ABAB", k = 2:
//   right=0 'A': counts={A:1}, maxCount=1, window size 1, 1-1=0 not > k -> result=1
//   right=1 'B': counts={A:1,B:1}, maxCount=1, window size 2, 2-1=1 not > k -> result=2
//   right=2 'A': counts={A:2,B:1}, maxCount=2, window size 3, 3-2=1 not > k -> result=3
//   right=3 'B': counts={A:2,B:2}, maxCount=2, window size 4, 4-2=2 not > k -> result=4
//   result = 4 (replace both B's -> "AAAA", using exactly k=2 replacements)
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
class LongestRepeatingCharReplacement {
    public static int characterReplacement(String s, int k) {
        int[] counts = new int[26];
        int maxCount = 0, left = 0, result = 0;

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'A';
            counts[idx]++;
            maxCount = Math.max(maxCount, counts[idx]);

            while (right - left + 1 - maxCount > k) {
                counts[s.charAt(left) - 'A']--;
                left++;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("ABAB", 2)); // 4
        System.out.println(characterReplacement("AABABBA", 1)); // 4
    }
}

// Longest Palindromic Substring
// Find the longest substring that reads the same forwards and backwards.
// Expand around every possible center (both single and double character)
// and track the widest palindrome found.
// Time: O(n^2), Space: O(1)
class LongestPalindrome {
    private static String s;

    public static String longestPalindrome(String input) {
        s = input;
        if (s.isEmpty()) return "";
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expand(i, i);
            int len2 = expand(i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start + 1) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }

        return s.substring(start, end + 1);
    }

    private static int expand(int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return r - l - 1;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // "bab" or "aba"
    }
}

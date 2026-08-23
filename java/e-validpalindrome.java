// Valid Palindrome
// Determine if a string is a palindrome, ignoring non-alphanumeric characters and case.
//
// Input: s = "A man, a plan, a canal: Panama"
// Output: true
//
// Two pointers from both ends, skipping non-alphanumeric characters and
// comparing letters case-insensitively until they meet in the middle.
//
// Trace with s = "A man, a plan, a canal: Panama" (spaces/punctuation skipped):
//   l='A', r='a' -> equal case-insensitively -> l++, r--
//   l='m', r='m' -> equal -> continue
//   ... pointers keep meeting equal letters (a/a, n/n, ...) all the way in
//   pointers cross in the middle having never found a mismatch -> true
//
// Time: O(n), Space: O(1)
class ValidPalindrome {
    public static boolean isPalindrome(String s) {
        int l = 0, r = s.length() - 1;

        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r--;
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))) return false;
            l++;
            r--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama")); // true
        System.out.println(isPalindrome("race a car")); // false
    }
}

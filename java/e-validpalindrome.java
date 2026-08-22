// Valid Palindrome
// Determine if a string is a palindrome, ignoring non-alphanumeric characters and case.
// Two pointers from both ends, skipping non-alphanumeric characters and
// comparing letters case-insensitively until they meet in the middle.
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

# Valid Palindrome
# Determine if a string is a palindrome, ignoring non-alphanumeric characters and case.
#
# Input: s = "A man, a plan, a canal: Panama"
# Output: true
#
# Two pointers from both ends, skipping non-alphanumeric characters and
# comparing letters case-insensitively until they meet in the middle.
#
# Trace with s = "A man, a plan, a canal: Panama" (spaces/punctuation skipped):
#   l='A', r='a' -> equal case-insensitively -> l++, r--
#   l='m', r='m' -> equal -> continue
#   ... pointers keep meeting equal letters (a/a, n/n, ...) all the way in
#   pointers cross in the middle having never found a mismatch -> true
#
# Time: O(n), Space: O(1)
def is_palindrome(s):
    l = 0
    r = len(s) - 1

    def is_alnum(c):
        return c.isalnum() and c.isascii()

    while l < r:
        while l < r and not is_alnum(s[l]):
            l += 1
        while l < r and not is_alnum(s[r]):
            r -= 1
        if s[l].lower() != s[r].lower():
            return False
        l += 1
        r -= 1

    return True


print(is_palindrome('A man, a plan, a canal: Panama'))  # True
print(is_palindrome('race a car'))  # False

# Find All Anagrams in a String
# Given strings s and p, find every starting index in s where a substring of
# length p.length is an anagram of p (same letters, any order).
#
# Input: s = "cbaebabacd", p = "abc"  (p.length = 3, so check every length-3 window of s)
# Output: [0, 6]
#   index 0 -> s[0..2] = "cba" -> an anagram of "abc"
#   index 6 -> s[6..8] = "bac" -> an anagram of "abc"
#
# Sliding window of p's length over s, comparing 26-letter frequency
# counts and recording every position where they match exactly.
#
# Time: O(n), Space: O(1) (fixed 26-letter alphabet)
def find_anagrams(s, p):
    result = []
    if len(s) < len(p):
        return result

    pattern_counts = [0] * 26
    window_counts = [0] * 26
    char_code_a = ord('a')

    for c in p:
        pattern_counts[ord(c) - char_code_a] += 1

    # build the first window and check it
    for i in range(len(p)):
        window_counts[ord(s[i]) - char_code_a] += 1
    if counts_match(pattern_counts, window_counts):
        result.append(0)

    # slide the window one character at a time: drop the char leaving on the
    # left, add the char entering on the right, then check the new window
    for start in range(1, len(s) - len(p) + 1):
        char_leaving = ord(s[start - 1]) - char_code_a
        char_entering = ord(s[start + len(p) - 1]) - char_code_a

        window_counts[char_leaving] -= 1
        window_counts[char_entering] += 1

        if counts_match(pattern_counts, window_counts):
            result.append(start)

    return result


def counts_match(pattern_counts, window_counts):
    for i in range(len(pattern_counts)):
        if pattern_counts[i] != window_counts[i]:
            return False
    return True


print(find_anagrams('cbaebabacd', 'abc'))  # [0, 6]

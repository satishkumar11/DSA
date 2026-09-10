# hellointerview: https://www.hellointerview.com/learn/code/sliding-window/longest-repeating-character-replacement
# Longest Repeating Character Replacement
# Find the longest substring achievable by replacing at most k characters with the same character.
#
# Input: s = "ABAB", k = 2
# Output: 4
#
# Sliding window tracking the count of the most frequent character
# inside it; shrink the window whenever replacements needed exceed k.
#
# Trace with s = "ABAB", k = 2:
#   right=0 'A': counts={A:1}, maxCount=1, window size 1, 1-1=0 not > k -> result=1
#   right=1 'B': counts={A:1,B:1}, maxCount=1, window size 2, 2-1=1 not > k -> result=2
#   right=2 'A': counts={A:2,B:1}, maxCount=2, window size 3, 3-2=1 not > k -> result=3
#   right=3 'B': counts={A:2,B:2}, maxCount=2, window size 4, 4-2=2 not > k -> result=4
#   result = 4 (replace both B's -> "AAAA", using exactly k=2 replacements)
#
# Time: O(n), Space: O(1) (fixed 26-letter alphabet)
def character_replacement(s, k):
    counts = [0] * 26
    base = ord('A')
    max_count = 0
    left = 0
    result = 0

    for right in range(len(s)):
        idx = ord(s[right]) - base
        counts[idx] += 1
        max_count = max(max_count, counts[idx])

        while right - left + 1 - max_count > k:
            counts[ord(s[left]) - base] -= 1
            left += 1

        result = max(result, right - left + 1)

    return result


print(character_replacement('ABAB', 2))  # 4
print(character_replacement('AABABBA', 1))  # 4

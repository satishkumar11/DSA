# Valid Anagram
# Determine if string t is an anagram of string s (same characters, same frequency).
#
# Input: s = "anagram", t = "nagaram"
# Output: true
#
# Count character frequencies of both strings into a single 26-slot list,
# incrementing for s and decrementing for t; all zeros means an anagram.
#
# Trace with s = "anagram", t = "nagaram" (+1 per s char, -1 per t char):
#   a/n -> counts[a]=1, counts[n]=-1
#   n/a -> counts[n]=0,  counts[a]=0
#   a/g -> counts[a]=1, counts[g]=-1
#   g/a -> counts[g]=0,  counts[a]=0
#   r/r, a/a, m/m -> each pair cancels itself out
#   every slot ends at 0 -> true
#
# Time: O(n), Space: O(1) (fixed 26-letter alphabet)
def is_anagram(s, t):
    if len(s) != len(t):
        return False

    counts = [0] * 26
    base = ord('a')

    for i in range(len(s)):
        counts[ord(s[i]) - base] += 1
        counts[ord(t[i]) - base] -= 1

    for c in counts:
        if c != 0:
            return False
    return True


print(is_anagram('anagram', 'nagaram'))  # True
print(is_anagram('rat', 'car'))  # False

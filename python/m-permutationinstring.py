# Permutation in String
# Determine if one string contains a permutation of another as a substring.
#
# Input: s1 = "ab", s2 = "eidbaooo"
# Output: true
#
# Sliding window of s1's length over s2, comparing 26-letter frequency
# counts of the window against s1's counts at every position.
#
# Trace with s1 = "ab" (need: a=1,b=1), s2 = "eidbaooo":
#   i=0 'e': window={e:1} - window too short to check yet (i < len(s1)-1)
#   i=1 'i': window={e:1,i:1} - doesn't match need
#   i=2 'd': 'e' leaves, 'd' enters -> window={i:1,d:1} - doesn't match
#   i=3 'b': 'i' leaves, 'b' enters -> window={d:1,b:1} - doesn't match
#   i=4 'a': 'd' leaves, 'a' enters -> window={b:1,a:1} - matches need! -> true
#
# Time: O(n), Space: O(1) (fixed 26-letter alphabet)
def check_inclusion(s1, s2):
    if len(s1) > len(s2):
        return False

    need = [0] * 26
    window = [0] * 26
    base = ord('a')

    for c in s1:
        need[ord(c) - base] += 1

    for i in range(len(s2)):
        window[ord(s2[i]) - base] += 1
        if i >= len(s1):
            window[ord(s2[i - len(s1)]) - base] -= 1
        if i >= len(s1) - 1 and counts_match(need, window):
            return True

    return False


def counts_match(need, window):
    for i in range(len(need)):
        if need[i] != window[i]:
            return False
    return True


# Simpler (less efficient) alternative: sort s1 once, then for every window
# of the same length in s2, sort that window too and compare directly - a
# permutation is just a rearrangement, so two strings with the same sorted
# form are permutations of each other. No frequency arrays or sliding-window
# bookkeeping needed, just a direct check at every position.
#
# Time: O(n * m log m), Space: O(m)
def check_inclusion_simple(s1, s2):
    sorted_s1 = ''.join(sorted(s1))

    for i in range(len(s2) - len(s1) + 1):
        window = s2[i:i + len(s1)]
        sorted_window = ''.join(sorted(window))
        if sorted_window == sorted_s1:
            return True

    return False


print(check_inclusion('ab', 'eidbaooo'))  # true
print(check_inclusion('ab', 'eidboaoo'))  # false
print(check_inclusion_simple('ab', 'eidbaooo'))  # true
print(check_inclusion_simple('ab', 'eidboaoo'))  # false

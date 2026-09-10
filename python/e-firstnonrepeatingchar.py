# First Non-Repeating Character
# Given a string, return the index of the first character that does not repeat.
# Return -1 if every character repeats.
#
# Input: s = "leetcode"
# Output: 0 ('l' never repeats and appears first)
#
# Count the frequency of every character in one pass using a hash map, then
# scan the string a second time and return the index of the first character
# whose count is exactly 1.
#
# Trace with s = "loveleetcode":
#   counts after pass 1 -> l:2, o:2, v:1, e:4, t:1, c:1, d:1
#   pass 2: l(2) skip, o(2) skip, v(1) -> return index 2
#
# Time: O(n), Space: O(k) where k is the number of distinct characters
def first_uniq_char(s):
    counts = {}

    for ch in s:
        counts[ch] = counts.get(ch, 0) + 1

    for i in range(len(s)):
        if counts.get(s[i]) == 1:
            return i

    return -1


# Alternate approach using index/rindex instead of a hash map: a
# character is non-repeating exactly when its first occurrence and its last
# occurrence are the same index. Scan left to right and return the first
# index where that holds.
#
# Trace with s = "loveleetcode":
#   i=0 'l': index=0, rindex=4 -> repeats, skip
#   i=1 'o': index=1, rindex=7 -> repeats, skip
#   i=2 'v': index=2, rindex=2 -> match -> return 2
#
# Time: O(n^2) (index/rindex each scan the string), Space: O(1)
def first_uniq_char_index_of(s):
    for i in range(len(s)):
        if s.index(s[i]) == s.rindex(s[i]):
            return i
    return -1


print(first_uniq_char('leetcode'))  # 0
print(first_uniq_char('loveleetcode'))  # 2
print(first_uniq_char('aabb'))  # -1

print(first_uniq_char_index_of('leetcode'))  # 0
print(first_uniq_char_index_of('loveleetcode'))  # 2
print(first_uniq_char_index_of('aabb'))  # -1

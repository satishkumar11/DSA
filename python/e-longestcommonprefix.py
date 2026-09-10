# Longest Common Prefix
# Find the longest prefix shared by all strings in an array.
#
# Input: strs = ["flower", "flow", "flight"]
# Output: "fl"
#
# Start with the first string as the candidate prefix, then repeatedly
# trim it until every other string starts with it.
#
# Time: O(n * m), Space: O(1)
#
# Dry run with strs = ["flower", "flow", "flight"]:
#   prefix starts as "flower"
#   i=1 ("flow"):   "flower" doesn't fit -> trim to "flowe" -> "flow" (fits)   prefix = "flow"
#   i=2 ("flight"): "flow" doesn't fit -> trim to "flo" -> "fl" (fits)         prefix = "fl"
#   loop ends -> return "fl"
def longest_common_prefix(strs):
    if not strs:
        return ''
    prefix = strs[0]

    for i in range(1, len(strs)):
        while strs[i].find(prefix) != 0:
            prefix = prefix[0:len(prefix) - 1]
            if not prefix:
                return ''

    return prefix


print(longest_common_prefix(['flower', 'flow', 'flight']))  # "fl"
print(longest_common_prefix(['dog', 'racecar', 'car']))  # "" (no common prefix)

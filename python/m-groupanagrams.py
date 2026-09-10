# Group Anagrams
# Group an array of strings into sets where every string in a set is an anagram of the others.
#
# Input: strs = ["eat","tea","tan","ate","nat","bat"]
# Output: [["eat","tea","ate"],["tan","nat"],["bat"]]
#
# Sort each string's characters to build a canonical key, then group
# original strings by that key in a hash map.
#
# Trace with strs = ["eat","tea","tan","ate","nat","bat"] (sorted-letters key):
#   eat -> "aet"   tea -> "aet"   tan -> "ant"
#   ate -> "aet"   nat -> "ant"   bat -> "abt"
#   groups: "aet"->[eat,tea,ate], "ant"->[tan,nat], "abt"->[bat]
#
# Time: O(n * k log k), Space: O(n * k)
def group_anagrams(strs):
    groups = {}
    for s in strs:
        key_chars = list(s)
        key_chars.sort()
        key = ''.join(key_chars)

        if key not in groups:
            groups[key] = []
        groups[key].append(s)

    result = []
    for key in groups:
        result.append(groups[key])
    return result


print(group_anagrams(['eat', 'tea', 'tan', 'ate', 'nat', 'bat']))

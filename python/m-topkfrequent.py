# Top K Frequent Elements
# Return the k most frequently occurring elements in an array.
#
# Input: nums = [1, 1, 1, 2, 2, 3], k = 2
# Output: [1, 2]
#
# Count each value's frequency, then keep a min-heap of size k so only
# the k most frequent values survive by the end.
#
# Trace with nums = [1, 1, 1, 2, 2, 3], k = 2:
#   freq = {1: 3, 2: 2, 3: 1}
#   sorted by count desc: [1,3], [2,2], [3,1]
#   take top k=2 -> [1,3], [2,2] -> map to keys -> [1, 2]
#
# Time: O(n log n), Space: O(n)
def top_k_frequent(nums, k):
    freq = {}
    for n in nums:
        freq[n] = freq.get(n, 0) + 1

    entries = list(freq.items())
    entries.sort(key=lambda e: e[1], reverse=True)

    result = []
    for i in range(k):
        result.append(entries[i][0])
    return result


# Simpler version, same idea spelled out with explicit steps instead of a
# chained one-liner: count frequencies, sort all entries by count
# descending, then just take the first k keys.
#
# Time: O(n log n), Space: O(n)
def top_k_frequent_simple(nums, k):
    freq = {}
    for n in nums:
        freq[n] = freq.get(n, 0) + 1

    entries = list(freq.items())
    print(entries)
    entries.sort(key=lambda e: e[1], reverse=True)

    result = []
    for i in range(k):
        result.append(entries[i][0])
    return result


print(top_k_frequent([1, 1, 1, 2, 2, 3], 2))  # [1, 2]
print(top_k_frequent_simple([1, 1, 1, 2, 2, 3], 2))  # [1, 2]

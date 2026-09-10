# Reverse Pairs
# Count pairs (i, j) where i < j and nums[i] > 2 * nums[j].
#
# Input: nums = [1, 3, 2, 3, 1]
# Output: 2
#
# Modified merge sort: while merging two already-sorted halves, count cross
# pairs before combining them. Both halves are sorted ascending, so as i
# walks the left half the matching pointer j in the right half only ever
# moves forward - no need to restart it for each i.
#
# Trace with nums = [1, 3, 2, 3, 1] (lo/mid/hi refer to the current split):
#   split [1,3] | [2,3,1] -> recurse
#     [1,3] splits to [1] | [3]: no cross pairs, merges to [1,3]
#     [2,3,1] splits to [2] | [3,1]
#       [3,1] splits to [3] | [1]: i=3, j=1 -> 3>2*1=2, j++; count=1
#                                  merges to [1,3]
#       merge [2] | [1,3]: i=2, j=1 -> 2>2*1=2? no; count stays 1
#                           merges to [1,2,3]
#   merge [1,3] | [1,2,3]: i=1 -> 1>2*1=2? no
#                           i=3 -> 3>2*1=2? yes, j->2; 3>2*2=4? no; count=2
#                           merges to [1,1,2,3,3]
#   count = 2  (pairs (1,4): 3>2*1, and (3,4): 3>2*1)
#
# Time: O(n log n), Space: O(n)
def reverse_pairs(nums):
    arr = nums[:]
    count = 0

    def merge_sort(lo, hi):
        nonlocal count
        if hi - lo <= 1:
            return
        mid = (lo + hi) // 2
        merge_sort(lo, mid)
        merge_sort(mid, hi)

        j = mid
        for i in range(lo, mid):
            while j < hi and arr[i] > 2 * arr[j]:
                j += 1
            count += j - mid

        merged = []
        l = lo
        r = mid
        while l < mid and r < hi:
            if arr[l] <= arr[r]:
                merged.append(arr[l])
                l += 1
            else:
                merged.append(arr[r])
                r += 1
        while l < mid:
            merged.append(arr[l])
            l += 1
        while r < hi:
            merged.append(arr[r])
            r += 1
        for k in range(len(merged)):
            arr[lo + k] = merged[k]

    merge_sort(0, len(arr))
    return count


# Without merge sort - brute force, check every pair directly.
#
# Time: O(n^2), Space: O(1)
def reverse_pairs_brute_force(nums):
    count = 0

    for i in range(len(nums)):
        for j in range(i + 1, len(nums)):
            if nums[i] > 2 * nums[j]:
                count += 1

    return count


print(reverse_pairs([1, 3, 2, 3, 1]))  # 2
print(reverse_pairs_brute_force([1, 3, 2, 3, 1]))  # 2

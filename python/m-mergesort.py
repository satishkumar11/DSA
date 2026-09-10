# Sort an Array
# Sort an integer array in ascending order using merge sort.
#
# Input: nums = [5, 2, 3, 1]
# Output: [1, 2, 3, 5]
#
# Split the array in half recursively down to single elements (already
# sorted), then merge each pair of sorted halves back together in order.
#
# Trace with nums = [5, 2, 3, 1]:
#   split [5,2] | [3,1]
#     split [5] | [2]  -> merge -> [2,5]
#     split [3] | [1]  -> merge -> [1,3]
#   merge [2,5] | [1,3]:
#     compare 2,1 -> take 1  -> [1]
#     compare 2,3 -> take 2  -> [1,2]
#     compare 5,3 -> take 3  -> [1,2,3]
#     left exhausted -> append remaining 5 -> [1,2,3,5]
#
# Time: O(n log n), Space: O(n)
def merge_sort(nums):
    if len(nums) <= 1:
        return nums

    mid = len(nums) // 2
    left = merge_sort(nums[:mid])
    right = merge_sort(nums[mid:])

    return merge(left, right)


def merge(left, right):
    merged = []
    i = 0
    j = 0

    while i < len(left) and j < len(right):
        if left[i] <= right[j]:
            merged.append(left[i])
            i += 1
        else:
            merged.append(right[j])
            j += 1
    while i < len(left):
        merged.append(left[i])
        i += 1
    while j < len(right):
        merged.append(right[j])
        j += 1

    return merged


print(merge_sort([5, 2, 3, 1]))  # [1, 2, 3, 5]

# Median of Two Sorted Arrays
# Find the median of two sorted arrays in logarithmic time.
#
# Input: nums1 = [1, 3], nums2 = [2]
# Output: 2.0 (with [1,2],[3,4] -> 2.5)
#
# Binary search a partition point in the smaller array so that the
# combined left half and right half of both arrays are balanced and ordered.
#
# Trace with nums1 = [1, 3], nums2 = [2] (nums1 is longer, so they swap:
# nums1 becomes [2], nums2 becomes [1, 3]; m=1, n=2):
#   lo=0, hi=1: i=0, j=2 -> left1=-inf, right1=2, left2=3, right2=inf
#     left2(3) <= right1(2)? no -> partition is off -> lo=1
#   lo=1, hi=1: i=1, j=1 -> left1=2, right1=inf, left2=1, right2=3
#     left1(2)<=right2(3) and left2(1)<=right1(inf) -> valid partition!
#     total length 3 is odd -> answer = max(left1, left2) = max(2, 1) = 2
#
# Trace with nums1 = [1, 2], nums2 = [3, 4] (equal lengths, no swap; m=n=2):
#   lo=0, hi=2: i=1, j=1 -> left1=1, right1=2, left2=3, right2=4
#     left2(3)<=right1(2)? no -> lo=2
#   lo=2, hi=2: i=2, j=0 -> left1=2, right1=inf, left2=-inf, right2=3
#     left1(2)<=right2(3) and left2(-inf)<=right1(inf) -> valid partition!
#     total length 4 is even -> answer = (max(2,-inf) + min(inf,3)) / 2 = (2+3)/2 = 2.5
#
# Time: O(log(min(m, n))), Space: O(1)
def find_median_sorted_arrays(nums1, nums2):
    if len(nums1) > len(nums2):
        nums1, nums2 = nums2, nums1

    m = len(nums1)
    n = len(nums2)
    lo = 0
    hi = m

    while lo <= hi:
        i = (lo + hi) // 2
        j = (m + n + 1) // 2 - i

        if i == 0:
            left1 = float('-inf')
        else:
            left1 = nums1[i - 1]

        if i == m:
            right1 = float('inf')
        else:
            right1 = nums1[i]

        if j == 0:
            left2 = float('-inf')
        else:
            left2 = nums2[j - 1]

        if j == n:
            right2 = float('inf')
        else:
            right2 = nums2[j]

        if left1 <= right2 and left2 <= right1:
            if (m + n) % 2 == 0:
                return (max(left1, left2) + min(right1, right2)) / 2
            return max(left1, left2)
        elif left1 > right2:
            hi = i - 1
        else:
            lo = i + 1

    return 0


# Simpler version: merge both sorted arrays into one (like the merge step
# of merge sort), then just index straight into the middle. No partition
# search, no -inf/inf edge cases to reason about.
#
# Time: O(m + n), Space: O(m + n)
def find_median_sorted_arrays_simple(nums1, nums2):
    merged = []
    i = 0
    j = 0

    while i < len(nums1) and j < len(nums2):
        if nums1[i] <= nums2[j]:
            merged.append(nums1[i])
            i += 1
        else:
            merged.append(nums2[j])
            j += 1
    while i < len(nums1):
        merged.append(nums1[i])
        i += 1
    while j < len(nums2):
        merged.append(nums2[j])
        j += 1

    mid = len(merged) // 2
    if len(merged) % 2 == 0:
        return (merged[mid - 1] + merged[mid]) / 2
    return merged[mid]


print(find_median_sorted_arrays([1, 3], [2]))  # 2
print(find_median_sorted_arrays([1, 2], [3, 4]))  # 2.5
print(find_median_sorted_arrays_simple([1, 3], [2]))  # 2
print(find_median_sorted_arrays_simple([1, 2], [3, 4]))  # 2.5

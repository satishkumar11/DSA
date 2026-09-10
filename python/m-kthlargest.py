# Java solution is easier

# hellointerview: https://www.hellointerview.com/learn/code/heap/kth-largest-element-in-an-array
# Kth Largest Element in an Array
# Find the kth largest element in an unsorted array.
#
# Input: nums = [3, 2, 1, 5, 6, 4], k = 2
# Output: 5
#
# Maintain a min-heap of size k while scanning the array; the smallest
# element in that heap is always the kth largest overall.
#
# Trace with nums = [3, 2, 1, 5, 6, 4], k = 2 (target index = 6-2 = 4):
#   partition(0,5), pivot=nums[5]=4: everything < 4 (3,2,1) shifts left,
#     pivot lands at index 3 -> array becomes [3,2,1,4,6,5], p=3
#   p(3) < target(4) -> search right -> lo=4
#   partition(4,5), pivot=nums[5]=5: nums[4]=6 is not < 5, pivot stays put
#     -> array becomes [3,2,1,4,5,6], p=4
#   p(4) == target(4) -> return nums[4] = 5
#
# Time: O(n) average, Space: O(1)
def find_kth_largest(nums, k):
    target = len(nums) - k

    def partition(lo, hi):
        pivot = nums[hi]
        i = lo
        for j in range(lo, hi):
            if nums[j] < pivot:
                swap(nums, i, j)
                i += 1
        swap(nums, i, hi)
        return i

    lo = 0
    hi = len(nums) - 1
    while True:
        p = partition(lo, hi)
        if p == target:
            return nums[p]
        if p < target:
            lo = p + 1
        else:
            hi = p - 1


def swap(nums, i, j):
    temp = nums[i]
    nums[i] = nums[j]
    nums[j] = temp


print(find_kth_largest([3, 2, 1, 5, 6, 4], 2))  # 5

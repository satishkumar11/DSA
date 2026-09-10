# Two Sum
# Given an array of integers and a target, return the indices of the two numbers that add up to the target.
#
# Input: nums = [2, 7, 11, 15], target = 9
# Output: [0, 1]
#
# Use a hash map to store each number's index as you scan the array.
# For every element, check whether its complement (target - num) has already been seen.
#
# Trace with nums = [2, 7, 11, 15], target = 9:
#   i=0: complement=9-2=7, not seen yet -> seen={2:0}
#   i=1: complement=9-7=2, 2 IS in seen (at index 0) -> return [0, 1]
#
# Time: O(n), Space: O(n)
def two_sum(nums, target):
    seen = {}  # value -> index

    for i in range(len(nums)):
        complement = target - nums[i]
        if complement in seen:
            return [seen[complement], i]
        seen[nums[i]] = i

    return []


print(two_sum([2, 7, 11, 15], 9))  # [0, 1]
print(two_sum([3, 2, 4], 6))  # [1, 2]
print(two_sum([3, 3], 6))  # [0, 1]

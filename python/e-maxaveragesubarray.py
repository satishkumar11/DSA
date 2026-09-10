# Maximum Average Subarray I
# Find the contiguous subarray of length k with the maximum average value.
#
# Input: nums = [1, 12, -5, -6, 50, 3], k = 4
# Output: 12.75
#
# Fixed-size sliding window: compute the first window's sum, then slide
# by adding the entering element and subtracting the one leaving.
#
# Trace with nums = [1, 12, -5, -6, 50, 3], k = 4:
#   first window [1,12,-5,-6]: sum=2, maxSum=2
#   i=4 (50 enters, nums[0]=1 leaves): sum = 2 + (50-1) = 51, maxSum=51
#   i=5 (3 enters, nums[1]=12 leaves): sum = 51 + (3-12) = 42, maxSum stays 51
#   maxSum/k = 51/4 = 12.75
#
# Time: O(n), Space: O(1)
def find_max_average(nums, k):
    total = 0
    for i in range(k):
        total += nums[i]
    max_sum = total

    for i in range(k, len(nums)):
        total += nums[i] - nums[i - k]
        max_sum = max(max_sum, total)

    return max_sum / k


print(find_max_average([1, 12, -5, -6, 50, 3], 4))  # 12.75

# Maximum Subarray
# Find the contiguous subarray with the largest sum (Kadane's algorithm).
#
# Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
# Output: 6
#
# Kadane's algorithm: at each index, either extend the previous subarray
# or start fresh, keeping whichever running sum is larger.
#
# Trace with nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]:
#   cur=-2, maxSum=-2
#   1:  cur=max(1, -2+1)=1,   maxSum=1
#   -3: cur=max(-3, 1-3)=-2,  maxSum stays 1
#   4:  cur=max(4, -2+4)=4,   maxSum=4      <- restart beats extending
#   -1: cur=max(-1, 4-1)=3,   maxSum stays 4
#   2:  cur=max(2, 3+2)=5,    maxSum=5
#   1:  cur=max(1, 5+1)=6,    maxSum=6      <- best subarray [4,-1,2,1]
#   -5: cur=max(-5, 6-5)=1,   maxSum stays 6
#   4:  cur=max(4, 1+4)=5,    maxSum stays 6
#   maxSum = 6
#
# Time: O(n), Space: O(1)
def max_sub_array(nums):
    max_sum = nums[0]
    cur = nums[0]

    for i in range(1, len(nums)):
        cur = max(nums[i], cur + nums[i])
        max_sum = max(max_sum, cur)

    return max_sum


# Without Kadane's - brute force, check every subarray's sum directly.
# For each start, extend the end one step at a time, adding the newly
# included element to a running sum instead of resumming from scratch
# (that would make it O(n^3) instead of O(n^2)).
#
# Time: O(n^2), Space: O(1)
def max_sub_array_brute_force(nums):
    max_sum = float('-inf')

    for start in range(len(nums)):
        total = 0
        for end in range(start, len(nums)):
            total += nums[end]
            max_sum = max(max_sum, total)

    return max_sum


print(max_sub_array([-2, 1, -3, 4, -1, 2, 1, -5, 4]))  # 6
print(max_sub_array_brute_force([-2, 1, -3, 4, -1, 2, 1, -5, 4]))  # 6

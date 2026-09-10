# Missing Number
# Find the missing number in an array containing n distinct numbers from 0 to n.
#
# Input: nums = [3, 0, 1]
# Output: 2
#
# XOR every index and every value together; all present pairs cancel out,
# leaving only the missing number.
#
# Why XOR works: x ^ x = 0, so any number appearing twice cancels out.
# Starting result at n (len(nums)) and XORing in every index 0..n-1 and
# every value in nums combines the full set {0..n} (via indices+n) with the
# array's actual values (all of {0..n} except the missing one) - every
# number that's genuinely present cancels against its matching index,
# leaving only the missing number standing.
#
# Trace with nums = [3, 0, 1] (n = 3):
#   result = 3
#   i=0: result ^= 0 ^ nums[0]=3 -> 3^0^3 = 0
#   i=1: result ^= 1 ^ nums[1]=0 -> 0^1^0 = 1
#   i=2: result ^= 2 ^ nums[2]=1 -> 1^2^1 = 2
#   result = 2
#
# Time: O(n), Space: O(1)
def missing_number(nums):
    result = len(nums)

    for i in range(len(nums)):
        result ^= i ^ nums[i]

    return result


print(missing_number([3, 0, 1]))  # 2

# hellointerview: https://www.hellointerview.com/learn/code/two-pointers/3-sum
# 3Sum
# Find all unique triplets in an array that sum to zero.
#
# Input: nums = [-1, 0, 1, 2, -1, -4]
# Output: [[-1, -1, 2], [-1, 0, 1]]
#
# Sort the array, fix one number, then use two pointers moving inward
# from both ends to find pairs that complete the triplet to zero.
#
# Trace with nums = [-1, 0, 1, 2, -1, -4], sorted -> [-4, -1, -1, 0, 1, 2]:
#   i=0 (-4): every l/r pair sums < 0 (biggest possible is -4-1+2=-3) -> no triplet
#   i=1 (-1): l=2,r=5 -> -1+(-1)+2=0 -> found [-1,-1,2]; l++,r-- -> l=3,r=4
#             -1+0+1=0 -> found [-1,0,1]; l++,r-- -> l=4,r=3, loop ends
#   i=2 (-1): same value as i=1 -> skip (avoids a duplicate triplet)
#   i=3 (0):  l=4,r=5 -> 0+1+2=3 > 0 -> r--, loop ends with nothing found
#   result: [[-1,-1,2], [-1,0,1]]
#
# Time: O(n^2), Space: O(1) excluding output
def three_sum(nums):
    nums = sorted(nums)
    res = []

    for i in range(len(nums) - 2):
        if i > 0 and nums[i] == nums[i - 1]:
            continue
        l = i + 1
        r = len(nums) - 1

        while l < r:
            total = nums[i] + nums[l] + nums[r]
            if total == 0:
                res.append([nums[i], nums[l], nums[r]])
                while l < r and nums[l] == nums[l + 1]:
                    l += 1
                while l < r and nums[r] == nums[r - 1]:
                    r -= 1
                l += 1
                r -= 1
            elif total < 0:
                l += 1
            else:
                r -= 1

    return res


print(three_sum([-1, 0, 1, 2, -1, -4]))  # [[-1,-1,2],[-1,0,1]]

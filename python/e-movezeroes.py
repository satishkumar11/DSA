# hellointerview: https://www.hellointerview.com/learn/code/two-pointers/move-zeroes
# Move Zeroes
# Move all zeroes to the end of an array while keeping the relative order of non-zero elements.
#
# Input: nums = [0, 1, 0, 3, 12]
# Output: [1, 3, 12, 0, 0]
#
# Two pointers i and j: advance i past values already confirmed non-zero,
# advance j past zeros, then swap when nums[i] is a zero blocking a non-zero at j.
#
# Time: O(n), Space: O(1)
def move_zeroes(nums):
    i = 0
    j = 1

    while j < len(nums):
        if nums[i] != 0:
            i += 1
            if i >= j:
                j += 1
        elif nums[j] == 0:
            j += 1
        else:
            temp = nums[j]
            nums[j] = nums[i]
            nums[i] = temp
            i += 1
            j += 1

    return nums


print(move_zeroes([0, 1, 0, 3, 12]))  # [1,3,12,0,0]

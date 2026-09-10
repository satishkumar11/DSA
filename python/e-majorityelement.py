# Majority Element
# Find the element that appears more than n/2 times in an array.
#
# Input: nums = [2, 2, 1, 1, 1, 2, 2]
# Output: 2
#
# Boyer-Moore voting: keep a running candidate and a count, incrementing
# on a match and decrementing otherwise; the true majority always survives.
#
# Time: O(n), Space: O(1)
def majority_element(nums):
    major = nums[0]
    count = 1

    for i in range(1, len(nums)):
        if count == 0:
            count += 1
            major = nums[i]
        elif major == nums[i]:
            count += 1
        else:
            count -= 1

    return major


print(majority_element([2, 2, 1, 1, 1, 2, 2]))  # 2

# Contains Duplicate
# Determine whether any value appears more than once in an array.
#
# Input: nums = [1, 2, 3, 1]
# Output: true
#
# Add each number to a hash set while scanning; if a number is already
# in the set, a duplicate has been found.
#
# Trace with nums = [1, 2, 3, 1]: add 1, 2, 3 to the set with no matches,
# then hit 1 again - it's already in the set -> return true immediately
# (no need to scan the rest of the array).
#
# Time: O(n), Space: O(n)
def contains_duplicate(nums):
    seen = set()
    for n in nums:
        if n in seen:
            return True
        seen.add(n)
    return False


print(contains_duplicate([1, 2, 3, 1]))  # True
print(contains_duplicate([1, 2, 3, 4]))  # False

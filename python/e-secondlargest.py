# Second Largest Element in an Array
# Find the second largest distinct value in an array.
#
# Input: nums = [12, 35, 1, 10, 34, 1]
# Output: 34
#
# Track the largest and second-largest values seen so far in one pass,
# updating both whenever a new maximum is found.
#
# Trace with nums = [12, 35, 1, 10, 34, 1]:
#   12: 12 > first(-inf) -> second=-inf, first=12
#   35: 35 > first(12)   -> second=12,   first=35
#   1:  not > first, not (> second and < first) -> no change
#   10: not > first, not (> second and < first) -> no change
#   34: not > first, but 34 > second(12) and 34 < first(35) -> second=34
#   1:  no change
#   second = 34
#
# Time: O(n), Space: O(1)
def second_largest(nums):
    first = float('-inf')
    second = float('-inf')

    for n in nums:
        if n > first:
            second = first
            first = n
        elif n > second and n < first:
            second = n

    if second == float('-inf'):
        return -1
    return second


print(second_largest([12, 35, 1, 10, 34, 1]))  # 34

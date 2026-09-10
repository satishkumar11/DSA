# hellointerview: https://www.hellointerview.com/learn/code/two-pointers/container-with-most-water
# Container With Most Water
# Find two lines that, with the x-axis, form a container holding the most water.
#
# Input: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
# Output: 49
#
# Two pointers starting at both ends; always move the pointer at the
# shorter line inward, since it's the only side that could improve the area.
#
# height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
#            |                    |
#            8 <---container---> 7   (indices 1 and 8, width 7)
#
# Max area = min(8, 7) * (8 - 1) = 49
#
# Time: O(n), Space: O(1)
def max_area(height):
    l = 0
    r = len(height) - 1
    max_a = 0

    while l < r:
        area = min(height[l], height[r]) * (r - l)
        max_a = max(max_a, area)
        if height[l] < height[r]:
            l += 1
        else:
            r -= 1

    return max_a


print(max_area([1, 8, 6, 2, 5, 4, 8, 3, 7]))  # 49

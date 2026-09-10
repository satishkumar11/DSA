# hellointerview: https://www.hellointerview.com/learn/code/two-pointers/trapping-rain-water
# Trapping Rain Water
# Compute how much rainwater is trapped between bars of varying height.
#
# Input: height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
# Output: 6
#
# Two pointers tracking the max height seen from each side; water at any
# position is bounded by the smaller of the two running maxes.
#
# height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
#
# .......#....
# ...#~~~##~#.
# .#~##~######
#
# '#' = bar, '~' = trapped water (total trapped = 6)
#
# Two-pointer trace (only the water-adding steps shown):
#   l=2 (height 0), leftMax so far=1 -> traps 1-0=1 water   (water=1)
#   r=9 (height 1), rightMax so far=2 -> traps 2-1=1 water  (water=2)
#   l=4 (height 1), leftMax so far=2 -> traps 2-1=1 water   (water=3)
#   l=5 (height 0), leftMax so far=2 -> traps 2-0=2 water   (water=5)
#   l=6 (height 1), leftMax so far=2 -> traps 2-1=1 water   (water=6)
#   pointers meet -> total water = 6
#
# Time: O(n), Space: O(1)
def trap(height):
    l = 0
    r = len(height) - 1
    left_max = 0
    right_max = 0
    water = 0

    while l < r:
        if height[l] < height[r]:
            if height[l] >= left_max:
                left_max = height[l]
            else:
                water += left_max - height[l]
            l += 1
        else:
            if height[r] >= right_max:
                right_max = height[r]
            else:
                water += right_max - height[r]
            r -= 1

    return water


# Simpler version: precompute the max height to the left and right of every
# index into their own lists, then combine them directly - no pointer
# juggling, just "what's the smaller of the two walls around me".
#
# Time: O(n), Space: O(n)
def trap_simple(height):
    n = len(height)
    if n == 0:
        return 0

    left_max = [0] * n
    left_max[0] = height[0]
    for i in range(1, n):
        left_max[i] = max(left_max[i - 1], height[i])

    right_max = [0] * n
    right_max[n - 1] = height[n - 1]
    for i in range(n - 2, -1, -1):
        right_max[i] = max(right_max[i + 1], height[i])

    water = 0
    for i in range(n):
        water += min(left_max[i], right_max[i]) - height[i]

    return water


print(trap([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]))  # 6
print(trap_simple([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]))  # 6

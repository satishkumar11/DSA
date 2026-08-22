// hellointerview: https://www.hellointerview.com/learn/code/two-pointers/trapping-rain-water
// Trapping Rain Water
// Compute how much rainwater is trapped between bars of varying height.
//
// Input: height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
// Output: 6
//
// Two pointers tracking the max height seen from each side; water at any
// position is bounded by the smaller of the two running maxes.
//
// height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
//
// .......#....
// ...#~~~##~#.
// .#~##~######
//
// '#' = bar, '~' = trapped water (total trapped = 6)
//
// Time: O(n), Space: O(1)
class TrappingRainWater {
    public static int trap(int[] height) {
        int l = 0, r = height.length - 1, leftMax = 0, rightMax = 0, water = 0;

        while (l < r) {
            if (height[l] < height[r]) {
                if (height[l] >= leftMax) leftMax = height[l];
                else water += leftMax - height[l];
                l++;
            } else {
                if (height[r] >= rightMax) rightMax = height[r];
                else water += rightMax - height[r];
                r--;
            }
        }

        return water;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
    }
}

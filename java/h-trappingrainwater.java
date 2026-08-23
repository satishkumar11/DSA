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
// Two-pointer trace (only the water-adding steps shown):
//   l=2 (height 0), leftMax so far=1 -> traps 1-0=1 water   (water=1)
//   r=9 (height 1), rightMax so far=2 -> traps 2-1=1 water  (water=2)
//   l=4 (height 1), leftMax so far=2 -> traps 2-1=1 water   (water=3)
//   l=5 (height 0), leftMax so far=2 -> traps 2-0=2 water   (water=5)
//   l=6 (height 1), leftMax so far=2 -> traps 2-1=1 water   (water=6)
//   pointers meet -> total water = 6
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

    // Simpler version: precompute the max height to the left and right of
    // every index into their own arrays, then combine them directly - no
    // pointer juggling, just "what's the smaller of the two walls around me".
    //
    // Time: O(n), Space: O(n)
    public static int trapSimple(int[] height) {
        int n = height.length;
        if (n == 0) return 0;

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return water;
    }

    public static void main(String[] args) {
        System.out.println(trap(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
        System.out.println(trapSimple(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 6
    }
}

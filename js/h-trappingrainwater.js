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
function trap(height) {
  let l = 0;
  let r = height.length - 1;
  let leftMax = 0;
  let rightMax = 0;
  let water = 0;

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

// Simpler version: precompute the max height to the left and right of every
// index into their own arrays, then combine them directly - no pointer
// juggling, just "what's the smaller of the two walls around me".
//
// Time: O(n), Space: O(n)
function trapSimple(height) {
  const n = height.length;
  if (n === 0) return 0;

  const leftMax = new Array(n);
  leftMax[0] = height[0];
  for (let i = 1; i < n; i++) {
    leftMax[i] = Math.max(leftMax[i - 1], height[i]);
  }

  const rightMax = new Array(n);
  rightMax[n - 1] = height[n - 1];
  for (let i = n - 2; i >= 0; i--) {
    rightMax[i] = Math.max(rightMax[i + 1], height[i]);
  }

  let water = 0;
  for (let i = 0; i < n; i++) {
    water += Math.min(leftMax[i], rightMax[i]) - height[i];
  }

  return water;
}

console.log(trap([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1])); // 6
console.log(trapSimple([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1])); // 6

module.exports = trap;

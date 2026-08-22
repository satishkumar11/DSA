// Trapping Rain Water
// Compute how much rainwater is trapped between bars of varying height.
//
// Two pointers tracking the max height seen from each side; water at any
// position is bounded by the smaller of the two running maxes.
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

console.log(trap([0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1])); // 6

module.exports = trap;

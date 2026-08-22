// Container With Most Water
// Find two lines that, with the x-axis, form a container holding the most water.
// Time: O(n), Space: O(1)
function maxArea(height) {
  let l = 0;
  let r = height.length - 1;
  let max = 0;

  while (l < r) {
    const area = Math.min(height[l], height[r]) * (r - l);
    max = Math.max(max, area);
    if (height[l] < height[r]) l++;
    else r--;
  }

  return max;
}

console.log(maxArea([1, 8, 6, 2, 5, 4, 8, 3, 7])); // 49

module.exports = maxArea;

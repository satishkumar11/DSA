// Maximum Subarray
// Find the contiguous subarray with the largest sum (Kadane's algorithm).
// Time: O(n), Space: O(1)
function maxSubArray(nums) {
  let maxSum = nums[0];
  let cur = nums[0];

  for (let i = 1; i < nums.length; i++) {
    cur = Math.max(nums[i], cur + nums[i]);
    maxSum = Math.max(maxSum, cur);
  }

  return maxSum;
}

console.log(maxSubArray([-2, 1, -3, 4, -1, 2, 1, -5, 4])); // 6

module.exports = maxSubArray;

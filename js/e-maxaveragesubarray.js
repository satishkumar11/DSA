// Maximum Average Subarray I
// Find the contiguous subarray of length k with the maximum average value.
// Fixed-size sliding window: compute the first window's sum, then slide
// by adding the entering element and subtracting the one leaving.
// Time: O(n), Space: O(1)
function findMaxAverage(nums, k) {
  let sum = 0;
  for (let i = 0; i < k; i++) sum += nums[i];
  let maxSum = sum;

  for (let i = k; i < nums.length; i++) {
    sum += nums[i] - nums[i - k];
    maxSum = Math.max(maxSum, sum);
  }

  return maxSum / k;
}

console.log(findMaxAverage([1, 12, -5, -6, 50, 3], 4)); // 12.75

module.exports = findMaxAverage;

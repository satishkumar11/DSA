// Sliding Window Maximum
// Return the maximum value in every sliding window of size k.
//
// Input: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
// Output: [3, 3, 5, 5, 6, 7]
//
// Maintain a monotonic decreasing deque of indices; the front always
// holds the max of the current window, evicting stale or smaller values.
//
// nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
//
// [1  3 -1] -3  5  3  6  7   -> max 3
//  1 [3 -1 -3]  5  3  6  7   -> max 3
//  1  3 [-1 -3  5] 3  6  7   -> max 5
//  1  3 -1 [-3  5  3] 6  7   -> max 5
//  1  3 -1 -3 [5  3  6] 7    -> max 6
//  1  3 -1 -3  5 [3  6  7]   -> max 7
//
// Time: O(n), Space: O(k)
function maxSlidingWindow(nums, k) {
  const deque = []; // indices, values decreasing
  const result = [];

  for (let i = 0; i < nums.length; i++) {
    while (deque.length && deque[0] <= i - k) deque.shift();
    while (deque.length && nums[deque[deque.length - 1]] < nums[i]) deque.pop();
    deque.push(i);
    if (i >= k - 1) result.push(nums[deque[0]]);
  }

  return result;
}

console.log(maxSlidingWindow([1, 3, -1, -3, 5, 3, 6, 7], 3)); // [3,3,5,5,6,7]

module.exports = maxSlidingWindow;

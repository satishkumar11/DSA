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
// Deque trace (storing indices, values shown in parens; front = current max):
//   i=0 (1):  deque=[0(1)]
//   i=1 (3):  1 beats the 1 at the back -> pop it, push 3 -> deque=[1(3)]
//   i=2 (-1): -1 doesn't beat 3 -> just push -> deque=[1(3), 2(-1)]        -> max 3
//   i=3 (-3): doesn't beat -1 -> push -> deque=[1(3), 2(-1), 3(-3)]        -> max 3
//   i=4 (5):  index 1 has aged out of the window -> evict from front;
//             5 beats everything behind it -> pop -3, -1, 3 -> deque=[4(5)] -> max 5
//   i=5 (3):  doesn't beat 5 -> push -> deque=[4(5), 5(3)]                 -> max 5
//   i=6 (6):  6 beats 3 and 5 -> pop both -> deque=[6(6)]                  -> max 6
//   i=7 (7):  7 beats 6 -> pop it -> deque=[7(7)]                         -> max 7
//   result: [3, 3, 5, 5, 6, 7]
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

// Simpler version: for every window position, just scan its k elements
// directly to find the max. No deque, no "is this index stale" bookkeeping.
//
// Time: O(n * k), Space: O(1) excluding output
function maxSlidingWindowSimple(nums, k) {
  const result = [];

  for (let i = 0; i + k <= nums.length; i++) {
    let max = nums[i];
    for (let j = i + 1; j < i + k; j++) {
      max = Math.max(max, nums[j]);
    }
    result.push(max);
  }

  return result;
}

console.log(maxSlidingWindow([1, 3, -1, -3, 5, 3, 6, 7], 3)); // [3,3,5,5,6,7]
console.log(maxSlidingWindowSimple([1, 3, -1, -3, 5, 3, 6, 7], 3)); // [3,3,5,5,6,7]

module.exports = maxSlidingWindow;

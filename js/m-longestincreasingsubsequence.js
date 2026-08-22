// hellointerview: https://www.hellointerview.com/learn/code/dynamic-programming/longest-increasing-subsequence
// Longest Increasing Subsequence
// Find the length of the longest strictly increasing subsequence in an array.
//
// Input: nums = [10, 9, 2, 5, 3, 7, 101, 18]
// Output: 4
//
// Patience sorting: maintain the smallest possible tail value for every
// subsequence length, using binary search to place each new number.
//
// Time: O(n log n), Space: O(n)
function lengthOfLIS(nums) {
  const tails = [];

  for (const n of nums) {
    let lo = 0;
    let hi = tails.length;
    while (lo < hi) {
      const mid = (lo + hi) >> 1;
      if (tails[mid] < n) lo = mid + 1;
      else hi = mid;
    }
    tails[lo] = n;
  }

  return tails.length;
}

console.log(lengthOfLIS([10, 9, 2, 5, 3, 7, 101, 18])); // 4

module.exports = lengthOfLIS;

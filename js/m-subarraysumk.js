// Subarray Sum Equals K
// Count the number of contiguous subarrays whose sum equals k.
//
// Input: nums = [1, 1, 1], k = 2
// Output: 2
//
// Track running prefix sums in a hash map; a subarray sums to k whenever
// (prefixSum - k) was seen before at some earlier index.
//
// Time: O(n), Space: O(n)
function subarraySum(nums, k) {
  const map = new Map();
  map.set(0, 1);
  let sum = 0;
  let count = 0;

  for (const n of nums) {
    sum += n;
    count += map.get(sum - k) || 0;
    map.set(sum, (map.get(sum) || 0) + 1);
  }

  return count;
}

console.log(subarraySum([1, 1, 1], 2)); // 2
console.log(subarraySum([1, 2, 3], 3)); // 2

module.exports = subarraySum;

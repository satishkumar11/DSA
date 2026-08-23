// hellointerview: https://www.hellointerview.com/learn/code/prefix-sum/subarray-sum-equals-k
// Subarray Sum Equals K
// Count the number of contiguous subarrays whose sum equals k.
//
// Input: nums = [1, 1, 1], k = 2
// Output: 2
//
// Track running prefix sums in a hash map; a subarray sums to k whenever
// (prefixSum - k) was seen before at some earlier index.
//
// Trace with nums = [1, 1, 1], k = 2 (map starts {0: 1}):
//   n=1: sum=1, map.get(1-2=-1)=0 -> count=0, map={0:1, 1:1}
//   n=1: sum=2, map.get(2-2=0)=1  -> count=1, map={0:1, 1:1, 2:1}
//   n=1: sum=3, map.get(3-2=1)=1  -> count=2, map={0:1, 1:1, 2:1, 3:1}
//   count = 2  (subarrays [1,1] at indices 0-1 and 1-2)
//
// Trace with nums = [3, 4, 7, 2, -3, 1, 4, 2], k = 7 (map starts {0: 1}):
//   n=3:  sum=3,  map.get(3-7=-4)=0  -> count=0, map={0:1, 3:1}
//   n=4:  sum=7,  map.get(7-7=0)=1   -> count=1, map={0:1, 3:1, 7:1}          [3,4]
//   n=7:  sum=14, map.get(14-7=7)=1  -> count=2, map={...,14:1}               [7]
//   n=2:  sum=16, map.get(16-7=9)=0  -> count=2, map={...,16:1}
//   n=-3: sum=13, map.get(13-7=6)=0  -> count=2, map={...,13:1}
//   n=1:  sum=14, map.get(14-7=7)=1  -> count=3, map={14:2,...}               [7,2,-3,1]
//   n=4:  sum=18, map.get(18-7=11)=0 -> count=3, map={...,18:1}
//   n=2:  sum=20, map.get(20-7=13)=1 -> count=4, map={...,20:1}               [1,4,2]
//   count = 4  (subarrays [3,4], [7], [7,2,-3,1], [1,4,2])
//
// Time: O(n), Space: O(n)
function subarraySum(nums, k) {
  const map = new Map();
  // seed the "empty prefix" (sum 0, before the array starts) so subarrays
  // that begin right at index 0 can still be counted below
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
console.log(subarraySum([3, 4, 7, 2, -3, 1, 4, 2], 7)); // 4

module.exports = subarraySum;

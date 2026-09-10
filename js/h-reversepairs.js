// Reverse Pairs
// Count pairs (i, j) where i < j and nums[i] > 2 * nums[j].
//
// Input: nums = [1, 3, 2, 3, 1]
// Output: 2
//
// Modified merge sort: while merging two already-sorted halves, count cross
// pairs before combining them. Both halves are sorted ascending, so as i
// walks the left half the matching pointer j in the right half only ever
// moves forward - no need to restart it for each i.
//
// Trace with nums = [1, 3, 2, 3, 1] (lo/mid/hi refer to the current split):
//   split [1,3] | [2,3,1] -> recurse
//     [1,3] splits to [1] | [3]: no cross pairs, merges to [1,3]
//     [2,3,1] splits to [2] | [3,1]
//       [3,1] splits to [3] | [1]: i=3, j=1 -> 3>2*1=2, j++; count=1
//                                  merges to [1,3]
//       merge [2] | [1,3]: i=2, j=1 -> 2>2*1=2? no; count stays 1
//                           merges to [1,2,3]
//   merge [1,3] | [1,2,3]: i=1 -> 1>2*1=2? no
//                           i=3 -> 3>2*1=2? yes, j->2; 3>2*2=4? no; count=2
//                           merges to [1,1,2,3,3]
//   count = 2  (pairs (1,4): 3>2*1, and (3,4): 3>2*1)
//
// Time: O(n log n), Space: O(n)
function reversePairs(nums) {
  const arr = nums.slice();
  let count = 0;

  function mergeSort(lo, hi) {
    if (hi - lo <= 1) return;
    const mid = Math.floor((lo + hi) / 2);
    mergeSort(lo, mid);
    mergeSort(mid, hi);

    let j = mid;
    for (let i = lo; i < mid; i++) {
      while (j < hi && arr[i] > 2 * arr[j]) j++;
      count += j - mid;
    }

    const merged = [];
    let l = lo;
    let r = mid;
    while (l < mid && r < hi) {
      if (arr[l] <= arr[r]) merged.push(arr[l++]);
      else merged.push(arr[r++]);
    }
    while (l < mid) merged.push(arr[l++]);
    while (r < hi) merged.push(arr[r++]);
    for (let k = 0; k < merged.length; k++) arr[lo + k] = merged[k];
  }

  mergeSort(0, arr.length);
  return count;
}

// Without merge sort - brute force, check every pair directly.
//
// Time: O(n^2), Space: O(1)
function reversePairsBruteForce(nums) {
  let count = 0;

  for (let i = 0; i < nums.length; i++) {
    for (let j = i + 1; j < nums.length; j++) {
      if (nums[i] > 2 * nums[j]) count++;
    }
  }

  return count;
}

console.log(reversePairs([1, 3, 2, 3, 1])); // 2
console.log(reversePairsBruteForce([1, 3, 2, 3, 1])); // 2

module.exports = reversePairs;

// Top K Frequent Elements
// Return the k most frequently occurring elements in an array.
//
// Input: nums = [1, 1, 1, 2, 2, 3], k = 2
// Output: [1, 2]
//
// Count each value's frequency, then keep a min-heap of size k so only
// the k most frequent values survive by the end.
//
// Trace with nums = [1, 1, 1, 2, 2, 3], k = 2:
//   freq = {1: 3, 2: 2, 3: 1}
//   sorted by count desc: [1,3], [2,2], [3,1]
//   take top k=2 -> [1,3], [2,2] -> map to keys -> [1, 2]
//
// Time: O(n log n), Space: O(n)
function topKFrequent(nums, k) {
  const freq = new Map();
  for (const n of nums) freq.set(n, (freq.get(n) || 0) + 1);
  return [...freq.entries()]
    .sort((a, b) => b[1] - a[1])
    .slice(0, k)
    .map((e) => e[0]);
}

// Simpler version, same idea spelled out with explicit steps instead of a
// chained one-liner: count frequencies, sort all entries by count
// descending, then just take the first k keys.
//
// Time: O(n log n), Space: O(n)
function topKFrequentSimple(nums, k) {
  const freq = new Map();
  for (const n of nums) {
    freq.set(n, (freq.get(n) || 0) + 1);
  }

  const entries = [...freq.entries()];
  console.log(entries)
  entries.sort((a, b) => b[1] - a[1]);

  const result = [];
  for (let i = 0; i < k; i++) {
    result.push(entries[i][0]);
  }
  return result;
}

console.log(topKFrequent([1, 1, 1, 2, 2, 3], 2)); // [1, 2]
console.log(topKFrequentSimple([1, 1, 1, 2, 2, 3], 2)); // [1, 2]

module.exports = topKFrequent;

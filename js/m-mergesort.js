// Sort an Array
// Sort an integer array in ascending order using merge sort.
//
// Input: nums = [5, 2, 3, 1]
// Output: [1, 2, 3, 5]
//
// Split the array in half recursively down to single elements (already
// sorted), then merge each pair of sorted halves back together in order.
//
// Trace with nums = [5, 2, 3, 1]:
//   split [5,2] | [3,1]
//     split [5] | [2]  -> merge -> [2,5]
//     split [3] | [1]  -> merge -> [1,3]
//   merge [2,5] | [1,3]:
//     compare 2,1 -> take 1  -> [1]
//     compare 2,3 -> take 2  -> [1,2]
//     compare 5,3 -> take 3  -> [1,2,3]
//     left exhausted -> append remaining 5 -> [1,2,3,5]
//
// Time: O(n log n), Space: O(n)
function mergeSort(nums) {
  if (nums.length <= 1) return nums;

  const mid = Math.floor(nums.length / 2);
  const left = mergeSort(nums.slice(0, mid));
  const right = mergeSort(nums.slice(mid));

  return merge(left, right);
}

function merge(left, right) {
  const merged = [];
  let i = 0;
  let j = 0;

  while (i < left.length && j < right.length) {
    if (left[i] <= right[j]) merged.push(left[i++]);
    else merged.push(right[j++]);
  }
  while (i < left.length) merged.push(left[i++]);
  while (j < right.length) merged.push(right[j++]);

  return merged;
}

console.log(mergeSort([5, 2, 3, 1])); // [1, 2, 3, 5]

module.exports = mergeSort;

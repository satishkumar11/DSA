// hellointerview: https://www.hellointerview.com/learn/code/heap/kth-largest-element-in-an-array
// Kth Largest Element in an Array
// Find the kth largest element in an unsorted array.
//
// Input: nums = [3, 2, 1, 5, 6, 4], k = 2
// Output: 5
//
// Maintain a min-heap of size k while scanning the array; the smallest
// element in that heap is always the kth largest overall.
//
// Time: O(n) average, Space: O(1)
function findKthLargest(nums, k) {
  const target = nums.length - k;

  function partition(lo, hi) {
    const pivot = nums[hi];
    let i = lo;
    for (let j = lo; j < hi; j++) {
      if (nums[j] < pivot) {
        swap(nums, i, j);
        i++;
      }
    }
    swap(nums, i, hi);
    return i;
  }

  let lo = 0;
  let hi = nums.length - 1;
  while (true) {
    const p = partition(lo, hi);
    if (p === target) return nums[p];
    if (p < target) lo = p + 1;
    else hi = p - 1;
  }
}

function swap(nums, i, j) {
  const temp = nums[i];
  nums[i] = nums[j];
  nums[j] = temp;
}

console.log(findKthLargest([3, 2, 1, 5, 6, 4], 2)); // 5

module.exports = findKthLargest;

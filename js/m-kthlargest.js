// Kth Largest Element in an Array
// Find the kth largest element in an unsorted array.
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
        [nums[i], nums[j]] = [nums[j], nums[i]];
        i++;
      }
    }
    [nums[i], nums[hi]] = [nums[hi], nums[i]];
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

console.log(findKthLargest([3, 2, 1, 5, 6, 4], 2)); // 5

module.exports = findKthLargest;

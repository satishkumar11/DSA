// Java solution is easier

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
// Trace with nums = [3, 2, 1, 5, 6, 4], k = 2 (target index = 6-2 = 4):
//   partition(0,5), pivot=nums[5]=4: everything < 4 (3,2,1) shifts left,
//     pivot lands at index 3 -> array becomes [3,2,1,4,6,5], p=3
//   p(3) < target(4) -> search right -> lo=4
//   partition(4,5), pivot=nums[5]=5: nums[4]=6 is not < 5, pivot stays put
//     -> array becomes [3,2,1,4,5,6], p=4
//   p(4) == target(4) -> return nums[4] = 5
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

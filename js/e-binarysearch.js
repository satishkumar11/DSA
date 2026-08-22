// Binary Search
// Find the index of a target value in a sorted array.
//
// Input: nums = [-1, 0, 3, 5, 9, 12], target = 9
// Output: 4
//
// Repeatedly halve the search range, comparing the middle element to the
// target and discarding the half that can't contain it.
//
// Time: O(log n), Space: O(1)
function binarySearch(nums, target) {
  let lo = 0;
  let hi = nums.length - 1;

  while (lo <= hi) {
    const mid = lo + Math.floor((hi - lo) / 2);
    if (nums[mid] === target) return mid;
    if (nums[mid] < target) lo = mid + 1;
    else hi = mid - 1;
  }

  return -1;
}

console.log(binarySearch([-1, 0, 3, 5, 9, 12], 9)); // 4

module.exports = binarySearch;

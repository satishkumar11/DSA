// Search in Rotated Sorted Array
// Find a target's index in a sorted array that has been rotated at an unknown pivot.
//
// Modified binary search: at each step, determine which half of the
// array is sorted, then check if the target falls in that sorted half.
//
// Time: O(log n), Space: O(1)
function search(nums, target) {
  let lo = 0;
  let hi = nums.length - 1;

  while (lo <= hi) {
    const mid = Math.floor((lo + hi) / 2);
    if (nums[mid] === target) return mid;

    if (nums[lo] <= nums[mid]) {
      if (nums[lo] <= target && target < nums[mid]) hi = mid - 1;
      else lo = mid + 1;
    } else {
      if (nums[mid] < target && target <= nums[hi]) lo = mid + 1;
      else hi = mid - 1;
    }
  }

  return -1;
}

console.log(search([4, 5, 6, 7, 0, 1, 2], 0)); // 4

module.exports = search;

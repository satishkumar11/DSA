// Find Minimum in Rotated Sorted Array
// Find the minimum element in a sorted array that has been rotated.
//
// Input: nums = [3, 4, 5, 1, 2]
// Output: 1
//
// Binary search comparing the middle element to the rightmost element to
// decide which half contains the rotation point (and the minimum).
//
// Time: O(log n), Space: O(1)
function findMin(nums) {
  let lo = 0;
  let hi = nums.length - 1;

  while (lo < hi) {
    const mid = lo + Math.floor((hi - lo) / 2);
    // const mid = (lo + hi) >> 1;
    if (nums[mid] > nums[hi]) lo = mid + 1;
    else hi = mid;
  }

  return nums[lo];
}

console.log(findMin([3, 4, 5, 1, 2])); // 1

module.exports = findMin;

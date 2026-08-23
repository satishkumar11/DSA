// Find Peak Element
// Find an index whose value is strictly greater than both its neighbors.
// Treat nums[-1] and nums[n] as -infinity, so an edge element only needs to
// beat its one real neighbor. An array can have several valid peaks -
// returning the index of any one of them is an acceptable answer.
//
// Input: nums = [1, 2, 3, 1]
// Output: 2  (nums[2]=3 beats both neighbors 2 and 1 - the only peak here)
//
// Binary search that always moves toward the side with a larger
// neighbor, since that side is guaranteed to contain a peak.
//
// Time: O(log n), Space: O(1)
function findPeakElement(nums) {
  let lo = 0;
  let hi = nums.length - 1;

  while (lo < hi) {
    const mid = Math.floor((lo + hi) / 2);
    if (nums[mid] > nums[mid + 1]) hi = mid;
    else lo = mid + 1;
  }

  return lo;
}

console.log(findPeakElement([1, 2, 3, 1])); // 2
// index 1 (value 2, beats 1 and 1) and index 5 (value 6, beats 5 and 4) are
// both valid peaks in this array - either is a correct answer
console.log(findPeakElement([1, 2, 1, 3, 5, 6, 4])); // 1 or 5

module.exports = findPeakElement;

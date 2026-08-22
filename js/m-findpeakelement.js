// Find Peak Element
// Find an index whose value is greater than both its neighbors.
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
console.log(findPeakElement([1, 2, 1, 3, 5, 6, 4])); // 1 or 5

module.exports = findPeakElement;

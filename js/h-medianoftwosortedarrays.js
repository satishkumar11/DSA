// Median of Two Sorted Arrays
// Find the median of two sorted arrays in logarithmic time.
//
// Input: nums1 = [1, 3], nums2 = [2]
// Output: 2.0 (with [1,2],[3,4] -> 2.5)
//
// Binary search a partition point in the smaller array so that the
// combined left half and right half of both arrays are balanced and ordered.
//
// Time: O(log(min(m, n))), Space: O(1)
function findMedianSortedArrays(nums1, nums2) {
  if (nums1.length > nums2.length) [nums1, nums2] = [nums2, nums1];
  const m = nums1.length;
  const n = nums2.length;
  let lo = 0;
  let hi = m;

  while (lo <= hi) {
    const i = Math.floor((lo + hi) / 2);
    const j = Math.floor((m + n + 1) / 2) - i;

    const left1 = i === 0 ? -Infinity : nums1[i - 1];
    const right1 = i === m ? Infinity : nums1[i];
    const left2 = j === 0 ? -Infinity : nums2[j - 1];
    const right2 = j === n ? Infinity : nums2[j];

    if (left1 <= right2 && left2 <= right1) {
      if ((m + n) % 2 === 0) return (Math.max(left1, left2) + Math.min(right1, right2)) / 2;
      return Math.max(left1, left2);
    } else if (left1 > right2) {
      hi = i - 1;
    } else {
      lo = i + 1;
    }
  }

  return 0;
}

console.log(findMedianSortedArrays([1, 3], [2])); // 2
console.log(findMedianSortedArrays([1, 2], [3, 4])); // 2.5

module.exports = findMedianSortedArrays;

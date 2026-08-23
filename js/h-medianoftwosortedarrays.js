// Median of Two Sorted Arrays
// Find the median of two sorted arrays in logarithmic time.
//
// Input: nums1 = [1, 3], nums2 = [2]
// Output: 2.0 (with [1,2],[3,4] -> 2.5)
//
// Binary search a partition point in the smaller array so that the
// combined left half and right half of both arrays are balanced and ordered.
//
// Trace with nums1 = [1, 3], nums2 = [2] (nums1 is longer, so they swap:
// nums1 becomes [2], nums2 becomes [1, 3]; m=1, n=2):
//   lo=0, hi=1: i=0, j=2 -> left1=-inf, right1=2, left2=3, right2=inf
//     left2(3) <= right1(2)? no -> partition is off -> lo=1
//   lo=1, hi=1: i=1, j=1 -> left1=2, right1=inf, left2=1, right2=3
//     left1(2)<=right2(3) and left2(1)<=right1(inf) -> valid partition!
//     total length 3 is odd -> answer = max(left1, left2) = max(2, 1) = 2
//
// Trace with nums1 = [1, 2], nums2 = [3, 4] (equal lengths, no swap; m=n=2):
//   lo=0, hi=2: i=1, j=1 -> left1=1, right1=2, left2=3, right2=4
//     left2(3)<=right1(2)? no -> lo=2
//   lo=2, hi=2: i=2, j=0 -> left1=2, right1=inf, left2=-inf, right2=3
//     left1(2)<=right2(3) and left2(-inf)<=right1(inf) -> valid partition!
//     total length 4 is even -> answer = (max(2,-inf) + min(inf,3)) / 2 = (2+3)/2 = 2.5
//
// Time: O(log(min(m, n))), Space: O(1)
function findMedianSortedArrays(nums1, nums2) {
  if (nums1.length > nums2.length) {
    const temp = nums1;
    nums1 = nums2;
    nums2 = temp;
  }
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

// Simpler version: merge both sorted arrays into one (like the merge step
// of merge sort), then just index straight into the middle. No partition
// search, no -inf/inf edge cases to reason about.
//
// Time: O(m + n), Space: O(m + n)
function findMedianSortedArraysSimple(nums1, nums2) {
  const merged = [];
  let i = 0;
  let j = 0;

  while (i < nums1.length && j < nums2.length) {
    if (nums1[i] <= nums2[j]) merged.push(nums1[i++]);
    else merged.push(nums2[j++]);
  }
  while (i < nums1.length) merged.push(nums1[i++]);
  while (j < nums2.length) merged.push(nums2[j++]);

  const mid = Math.floor(merged.length / 2);
  if (merged.length % 2 === 0) {
    return (merged[mid - 1] + merged[mid]) / 2;
  }
  return merged[mid];
}

console.log(findMedianSortedArrays([1, 3], [2])); // 2
console.log(findMedianSortedArrays([1, 2], [3, 4])); // 2.5
console.log(findMedianSortedArraysSimple([1, 3], [2])); // 2
console.log(findMedianSortedArraysSimple([1, 2], [3, 4])); // 2.5

module.exports = findMedianSortedArrays;

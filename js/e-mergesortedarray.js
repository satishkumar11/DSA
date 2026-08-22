// Merge Sorted Array
// Merge a second sorted array into the first, which has extra trailing space.
// Merge from the back: compare the largest remaining elements of both
// arrays and place the bigger one at the end of nums1's true length.
// Time: O(m + n), Space: O(1)
function merge(nums1, m, nums2, n) {
  let i = m - 1;
  let j = n - 1;
  let k = m + n - 1;

  while (j >= 0) {
    if (i >= 0 && nums1[i] > nums2[j]) {
      nums1[k--] = nums1[i--];
    } else {
      nums1[k--] = nums2[j--];
    }
  }

  return nums1;
}

console.log(merge([1, 2, 3, 0, 0, 0], 3, [2, 5, 6], 3)); // [1,2,2,3,5,6]

module.exports = merge;

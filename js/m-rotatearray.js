// Rotate Array
// Rotate an array to the right by k steps, in place.
//
// Input: nums = [1, 2, 3, 4, 5, 6, 7], k = 3
// Output: [5, 6, 7, 1, 2, 3, 4]
//
// Reverse the whole array, then reverse the first k elements and the
// remaining n-k elements separately. Reversing twice in the right pieces
// puts everything back in order, just shifted.
//
// Trace with nums = [1,2,3,4,5,6,7], k = 3:
//   reverse all:          [7,6,5,4,3,2,1]
//   reverse first k=3:    [5,6,7,4,3,2,1]
//   reverse remaining 4:  [5,6,7,1,2,3,4]
//
// Time: O(n), Space: O(1)
function rotate(nums, k) {
  const n = nums.length;
  k %= n;

  reverse(nums, 0, n - 1);
  reverse(nums, 0, k - 1);
  reverse(nums, k, n - 1);

  return nums;
}

function reverse(nums, i, j) {
  while (i < j) {
    const temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
    i++;
    j--;
  }
}

console.log(rotate([1, 2, 3, 4, 5, 6, 7], 3)); // [5,6,7,1,2,3,4]

module.exports = rotate;

// Move Zeroes
// Move all zeroes to the end of an array while keeping the relative order of non-zero elements.
//
// Input: nums = [0, 1, 0, 3, 12]
// Output: [1, 3, 12, 0, 0]
//
// Two pointers i and j: advance i past values already confirmed non-zero,
// advance j past zeros, then swap when nums[i] is a zero blocking a non-zero at j.
//
// Time: O(n), Space: O(1)
function moveZeroes(nums) {
  let i = 0;
  let j = 1;

  while (j < nums.length) {
    if (nums[i] !== 0) {
      i++;
      if (i >= j) j++;
    } else if (nums[j] === 0) {
      j++;
    } else {
      const temp = nums[j];
      nums[j] = nums[i];
      nums[i] = temp;
      i++;
      j++;
    }
  }

  return nums;
}

console.log(moveZeroes([0, 1, 0, 3, 12])); // [1,3,12,0,0]

module.exports = moveZeroes;

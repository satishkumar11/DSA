// Move Zeroes
// Move all zeroes to the end of an array while keeping the relative order of non-zero elements.
//
// Input: nums = [0, 1, 0, 3, 12]
// Output: [1, 3, 12, 0, 0]
//
// Two pointers: an insert pointer tracks where the next non-zero value
// goes; after copying all non-zeros forward, fill the remaining tail with zeros.
//
// Time: O(n), Space: O(1)
function moveZeroes(nums) {
  let insertPos = 0;

  for (let i = 0; i < nums.length; i++) {
    if (nums[i] !== 0) {
      nums[insertPos] = nums[i];
      insertPos++;
    }
  }

  for (let i = insertPos; i < nums.length; i++) nums[i] = 0;

  return nums;
}

console.log(moveZeroes([0, 1, 0, 3, 12])); // [1,3,12,0,0]

module.exports = moveZeroes;

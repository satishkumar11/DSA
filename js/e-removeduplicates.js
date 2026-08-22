// Remove Duplicates from Sorted Array
// Remove duplicates in place from a sorted array and return the new length.
//
// Two pointers: a slow pointer marks the last unique value written, a fast
// pointer scans ahead and copies in any new distinct value.
//
// Time: O(n), Space: O(1)
function removeDuplicates(nums) {
  if (nums.length === 0) return 0;
  let i = 0;

  for (let j = 1; j < nums.length; j++) {
    if (nums[j] !== nums[i]) {
      i++;
      nums[i] = nums[j];
    }
  }

  return i + 1;
}

const arr = [1, 1, 2, 2, 3];
console.log(removeDuplicates(arr), arr); // 3 [1, 2, 3, 2, 3]

module.exports = removeDuplicates;

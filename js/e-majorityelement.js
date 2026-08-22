// Majority Element
// Find the element that appears more than n/2 times in an array.
//
// Input: nums = [2, 2, 1, 1, 1, 2, 2]
// Output: 2
//
// Boyer-Moore voting: keep a running candidate and a count, incrementing
// on a match and decrementing otherwise; the true majority always survives.
//
// Time: O(n), Space: O(1)
function majorityElement(nums) {
  let major = nums[0];
  let count = 1;

  for (let i = 1; i < nums.length; i++) {
    if (count === 0) {
      count++;
      major = nums[i];
    } else if (major === nums[i]) {
      count++;
    } else {
      count--;
    }
  }

  return major;
}

console.log(majorityElement([2, 2, 1, 1, 1, 2, 2])); // 2

module.exports = majorityElement;

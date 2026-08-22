// Missing Number
// Find the missing number in an array containing n distinct numbers from 0 to n.
// Time: O(n), Space: O(1)
function missingNumber(nums) {
  let result = nums.length;

  for (let i = 0; i < nums.length; i++) {
    result ^= i ^ nums[i];
  }

  return result;
}

console.log(missingNumber([3, 0, 1])); // 2

module.exports = missingNumber;

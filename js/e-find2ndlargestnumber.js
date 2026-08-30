function find2ndLargestNumber(nums) {
  let first = nums[0];
  let second = nums[0];

  for (let i = 0; i < nums.length; i++) {
    if (nums[i] > first) {
      second = first;
      first = nums[i];
    } else if (nums[i] > second && nums[i] !== first) {
      second = nums[i];
    }
  }

  return second;
}

console.log(find2ndLargestNumber([10, 5, 25, 8, 30, 15])); // 25

module.exports = find2ndLargestNumber;

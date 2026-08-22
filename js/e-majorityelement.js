// Majority Element
// Find the element that appears more than n/2 times in an array.
// Time: O(n), Space: O(1)
function majorityElement(nums) {
  let count = 0;
  let candidate = null;

  for (const n of nums) {
    if (count === 0) candidate = n;
    count += n === candidate ? 1 : -1;
  }

  return candidate;
}

console.log(majorityElement([2, 2, 1, 1, 1, 2, 2])); // 2

module.exports = majorityElement;

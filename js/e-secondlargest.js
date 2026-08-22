// Second Largest Element in an Array
// Find the second largest distinct value in an array.
//
// Input: nums = [12, 35, 1, 10, 34, 1]
// Output: 34
//
// Track the largest and second-largest values seen so far in one pass,
// updating both whenever a new maximum is found.
//
// Time: O(n), Space: O(1)
function secondLargest(nums) {
  let first = -Infinity;
  let second = -Infinity;

  for (const n of nums) {
    if (n > first) {
      second = first;
      first = n;
    } else if (n > second && n < first) {
      second = n;
    }
  }

  return second === -Infinity ? -1 : second;
}

console.log(secondLargest([12, 35, 1, 10, 34, 1])); // 34

module.exports = secondLargest;

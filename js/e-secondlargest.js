// Second Largest Element in an Array
// Find the second largest distinct value in an array.
//
// Input: nums = [12, 35, 1, 10, 34, 1]
// Output: 34
//
// Track the largest and second-largest values seen so far in one pass,
// updating both whenever a new maximum is found.
//
// Trace with nums = [12, 35, 1, 10, 34, 1]:
//   12: 12 > first(-inf) -> second=-inf, first=12
//   35: 35 > first(12)   -> second=12,   first=35
//   1:  not > first, not (> second and < first) -> no change
//   10: not > first, not (> second and < first) -> no change
//   34: not > first, but 34 > second(12) and 34 < first(35) -> second=34
//   1:  no change
//   second = 34
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

// Contains Duplicate
// Determine whether any value appears more than once in an array.
//
// Add each number to a hash set while scanning; if a number is already
// in the set, a duplicate has been found.
//
// Time: O(n), Space: O(n)
function containsDuplicate(nums) {
  const seen = new Set();
  for (const n of nums) {
    if (seen.has(n)) return true;
    seen.add(n);
  }
  return false;
}

console.log(containsDuplicate([1, 2, 3, 1])); // true
console.log(containsDuplicate([1, 2, 3, 4])); // false

module.exports = containsDuplicate;

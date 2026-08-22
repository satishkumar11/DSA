// Longest Consecutive Sequence
// Find the length of the longest run of consecutive integers in an unsorted array.
//
// Input: nums = [100, 4, 200, 1, 3, 2]
// Output: 4
//
// Put all numbers in a set, then only start counting a streak from
// numbers whose predecessor (n-1) is not in the set.
//
// Time: O(n), Space: O(n)
function longestConsecutive(nums) {
  const set = new Set(nums);
  let longest = 0;

  for (const n of set) {
    if (!set.has(n - 1)) {
      let length = 1;
      while (set.has(n + length)) length++;
      longest = Math.max(longest, length);
    }
  }

  return longest;
}

console.log(longestConsecutive([100, 4, 200, 1, 3, 2])); // 4

module.exports = longestConsecutive;

// Longest Consecutive Sequence
// Find the length of the longest run of consecutive integers in an unsorted array.
//
// Input: nums = [100, 4, 200, 1, 3, 2]
// Output: 4
//
// Put all numbers in a set, then only start counting a streak from
// numbers whose predecessor (n-1) is not in the set - if n-1 exists, some
// earlier number will already discover this same streak from its start.
//
// Still O(n) overall despite the while loop nested in the for loop: the
// while only ever runs for genuine sequence starts, and every number gets
// consumed by at most one streak count across the whole run.
//
// Trace with nums = [100, 4, 200, 1, 3, 2]:
//   100: no 99 in set -> start, no 101 -> length 1
//   4:   3 is in set -> skip (not a start)
//   200: no 199 in set -> start, no 201 -> length 1
//   1:   no 0 in set -> start, 2,3,4 all in set, no 5 -> length 4
//   3,2: both have a predecessor in the set -> skipped
//   longest = 4 (the run 1,2,3,4)
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

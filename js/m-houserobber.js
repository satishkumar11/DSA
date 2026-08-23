// House Robber
// nums[i] is the money stashed in house i, houses lined up in a row. Robbing
// two adjacent houses trips the alarm, so you can never pick two neighboring
// indices. Find the maximum total you can rob.
//
// Input: nums = [2, 7, 9, 3, 1]
// Output: 12  (rob houses 0, 2, and 4 -> 2 + 9 + 1 = 12; every other
//              combination of non-adjacent houses sums to less, e.g. 1 and 3 -> 7 + 3 = 10)
//
// At each house, decide the best of two options: skip it (keep previous
// best) or rob it (previous-previous best plus this house's value).
//
// Time: O(n), Space: O(1)
function rob(nums) {
  let prev = 0;
  let cur = 0;

  for (const n of nums) {
    const best = Math.max(cur, prev + n);
    prev = cur;
    cur = best;
  }

  return cur;
}

console.log(rob([2, 7, 9, 3, 1])); // 12

module.exports = rob;

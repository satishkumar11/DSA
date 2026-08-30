// hellointerview: https://www.hellointerview.com/learn/code/two-pointers/two-sum
// Two Sum II - Input Array Is Sorted
// Given a sorted array, return the 1-indexed positions of the two numbers
// that add up to target. Unlike the classic Two Sum, being sorted means a
// two-pointer sweep works instead of a hash map.
//
// Input: numbers = [2, 7, 11, 15], target = 9
// Output: [1, 2]
//
// Two pointers from both ends: if the pair sums too high, the right value
// is too big, so move the right pointer in; if too low, move the left
// pointer up. Because the array is sorted, this always converges correctly.
//
// Trace with numbers = [2, 7, 11, 15], target = 9:
//   l=0, r=3: 2+15=17 > 9 -> too high -> r--
//   l=0, r=2: 2+11=13 > 9 -> too high -> r--
//   l=0, r=1: 2+7=9 == 9  -> found -> return [1, 2] (1-indexed)
//
// Time: O(n), Space: O(1)
function twoSumSorted(numbers, target) {
  let l = 0;
  let r = numbers.length - 1;

  while (l < r) {
    const sum = numbers[l] + numbers[r];
    if (sum === target) return [l + 1, r + 1];
    if (sum < target) l++;
    else r--;
  }

  return [];
}

console.log(twoSumSorted([2, 7, 11, 15], 9)); // [1, 2]

module.exports = twoSumSorted;

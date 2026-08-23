// Fruit Into Baskets
// Trees are planted in a row; fruits[i] is the type of fruit on tree i. You
// have exactly two baskets, each can hold unlimited fruit but only one type.
// Starting at any tree, pick fruit moving right and stop as soon as a tree
// holds a third type you have no basket for. Find the most fruit you can
// collect - equivalently, the length of the longest contiguous subarray
// containing at most two distinct values.
//
// Input: fruits = [1, 2, 1]
// Output: 3  (only two types total, so the whole array fits in two baskets)
//
// Input: fruits = [0, 1, 2, 2]
// Output: 3  (three types overall, but [1, 2, 2] - types 1 and 2 - is the
//             longest run that fits two baskets; starting from 0 caps out at length 2)
//
// Sliding window with a count map of fruit types inside it; shrink from
// the left whenever more than two distinct types are present.
//
// Time: O(n), Space: O(1)
function totalFruit(fruits) {
  const count = new Map();
  let left = 0;
  let maxLen = 0;

  for (let right = 0; right < fruits.length; right++) {
    count.set(fruits[right], (count.get(fruits[right]) || 0) + 1);

    while (count.size > 2) {
      const leftType = fruits[left];
      count.set(leftType, count.get(leftType) - 1);
      if (count.get(leftType) === 0) count.delete(leftType);
      left++;
    }

    maxLen = Math.max(maxLen, right - left + 1);
  }

  return maxLen;
}

console.log(totalFruit([1, 2, 1])); // 3
console.log(totalFruit([0, 1, 2, 2])); // 3

module.exports = totalFruit;

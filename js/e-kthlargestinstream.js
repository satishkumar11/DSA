// Kth Largest Element in a Stream
// Design a class that tracks the kth largest element as numbers are added one at a time.
//
// Input: k = 3, nums = [4, 5, 8, 2]; add(3); add(5); add(10)
// Output: 4, 5, 5
//
// Maintain a min-heap capped at size k as values stream in; the heap's
// smallest element is always the current kth largest.
//
// add: O(k) with array approach (use a real heap for O(log k) in production)
class KthLargest {
  constructor(k, nums) {
    this.k = k;
    this.heap = [];
    for (const n of nums) this.add(n);
  }

  add(val) {
    this.heap.push(val);
    this.heap.sort((a, b) => a - b);
    if (this.heap.length > this.k) this.heap.shift();
    return this.heap[0];
  }
}

const kth = new KthLargest(3, [4, 5, 8, 2]);
console.log(kth.add(3)); // 4
console.log(kth.add(5)); // 5
console.log(kth.add(10)); // 5

module.exports = KthLargest;

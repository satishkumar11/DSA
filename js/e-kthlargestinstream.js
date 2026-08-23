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
//
// Trace: constructor adds 4, 5, 8, 2 one at a time (k=3) - after all four,
// the smallest (2) has been evicted, leaving heap=[4, 5, 8].
//   add(3):  heap=[4,5,8,3] -> evict smallest -> [4,5,8]  -> returns 4
//   add(5):  heap=[4,5,8,5] -> evict smallest -> [5,5,8]  -> returns 5
//   add(10): heap=[5,5,8,10] -> evict smallest -> [5,8,10] -> returns 5
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

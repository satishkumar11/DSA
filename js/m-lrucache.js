// LRU Cache
// Design a fixed-capacity cache that evicts the least recently used item.
//
// Input: capacity = 2; put(1,1); put(2,2); get(1); put(3,3); get(2)
// Output: 1, -1
//
// Backed by an insertion-ordered map; every get/put re-inserts the key so
// it becomes most recent, and overflow evicts the oldest entry.
//
// get/put: O(1) average
//
// Trace with capacity=2:
//   put(1,1): map={1:1}
//   put(2,2): map={1:1, 2:2}
//   get(1): found -> delete+re-insert 1 so it's most recent -> map={2:2, 1:1}, returns 1
//   put(3,3): size(2) >= capacity(2) -> evict oldest (first key, which is now
//             2, since 1 was just refreshed) -> map={1:1} -> insert 3 -> map={1:1, 3:3}
//   get(2): 2 was evicted -> returns -1
class LRUCache {
  constructor(capacity) {
    this.capacity = capacity;
    this.map = new Map();
  }

  get(key) {
    if (!this.map.has(key)) return -1;
    const val = this.map.get(key);
    this.map.delete(key);
    this.map.set(key, val);
    return val;
  }

  put(key, value) {
    if (this.map.has(key)) this.map.delete(key);
    else if (this.map.size >= this.capacity) {
      // Map iteration order = insertion order, so the first key yielded by
      // .keys() is whichever key has sat untouched the longest - the LRU one.
      this.map.delete(this.map.keys().next().value);
      // Simpler to read (same result): destructure the first value straight
      // off the iterator instead of chaining .next().value manually.
      //   const [oldestKey] = this.map.keys();
      //   this.map.delete(oldestKey);
    }
    this.map.set(key, value);
  }
}

const cache = new LRUCache(2);
cache.put(1, 1);
cache.put(2, 2);
console.log(cache.get(1)); // 1
cache.put(3, 3); // evicts key 2
console.log(cache.get(2)); // -1

module.exports = LRUCache;

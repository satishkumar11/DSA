// LRU Cache
// Design a fixed-capacity cache that evicts the least recently used item.
// Backed by an insertion-ordered map; every get/put re-inserts the key so
// it becomes most recent, and overflow evicts the oldest entry.
// get/put: O(1) average
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
      this.map.delete(this.map.keys().next().value);
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

import java.util.LinkedHashMap;
import java.util.Map;

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
//   get(1): found -> re-inserting makes it most recent -> map={2:2, 1:1}, returns 1
//   put(3,3): size(2) >= capacity(2) -> evict oldest (key 2, since 1 was just
//             refreshed) -> map={1:1} -> insert 3 -> map={1:1, 3:3}
//   get(2): 2 was evicted -> returns -1
class LRUCache extends LinkedHashMap<Integer, Integer> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        super.put(key, value);
    }

    // LinkedHashMap's built-in equivalent of JS's `map.keys().next().value`:
    // constructed with accessOrder=true (see super() above), so the eldest
    // entry here is always the least recently used one. Returning true tells
    // the map to evict it automatically right after this put() completes.
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1
        cache.put(3, 3); // evicts key 2
        System.out.println(cache.get(2)); // -1
    }
}

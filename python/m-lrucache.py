# LRU Cache
# Design a fixed-capacity cache that evicts the least recently used item.
#
# Input: capacity = 2; put(1,1); put(2,2); get(1); put(3,3); get(2)
# Output: 1, -1
#
# Backed by an insertion-ordered dict; every get/put re-inserts the key so
# it becomes most recent, and overflow evicts the oldest entry.
#
# get/put: O(1) average
#
# Trace with capacity=2:
#   put(1,1): dict={1:1}
#   put(2,2): dict={1:1, 2:2}
#   get(1): found -> move 1 to the end so it's most recent -> dict={2:2, 1:1}, returns 1
#   put(3,3): size(2) >= capacity(2) -> evict oldest (first key, which is now
#             2, since 1 was just refreshed) -> dict={1:1} -> insert 3 -> dict={1:1, 3:3}
#   get(2): 2 was evicted -> returns -1
from collections import OrderedDict


class LRUCache:
    def __init__(self, capacity):
        self.capacity = capacity
        self.map = OrderedDict()

    def get(self, key):
        if key not in self.map:
            return -1
        val = self.map[key]
        self.map.move_to_end(key)
        return val

    def put(self, key, value):
        if key in self.map:
            self.map.move_to_end(key)
        elif len(self.map) >= self.capacity:
            # OrderedDict iteration order = insertion order (refreshed on
            # move_to_end), so popitem(last=False) evicts whichever key has
            # sat untouched the longest - the LRU one.
            self.map.popitem(last=False)
        self.map[key] = value


cache = LRUCache(2)
cache.put(1, 1)
cache.put(2, 2)
print(cache.get(1))  # 1
cache.put(3, 3)  # evicts key 2
print(cache.get(2))  # -1

import java.util.*;

// Top K Frequent Elements
// Return the k most frequently occurring elements in an array.
//
// Input: nums = [1, 1, 1, 2, 2, 3], k = 2
// Output: [1, 2]
//
// Count each value's frequency, then keep a min-heap of size k so only
// the k most frequent values survive by the end.
//
// Trace with nums = [1, 1, 1, 2, 2, 3], k = 2 (freq = {1:3, 2:2, 3:1}):
//   heap keeps at most k=2 keys, always evicting the one with the smallest
//   frequency once it grows past size k. Regardless of HashMap iteration
//   order, key 3 (frequency 1, the lowest) always ends up evicted, leaving
//   the heap holding keys 1 (freq 3) and 2 (freq 2) -> result = [1, 2]
//
// Time: O(n log k), Space: O(n)
class TopKFrequent {
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> freq.get(a) - freq.get(b));
        for (int key : freq.keySet()) {
            heap.offer(key);
            if (heap.size() > k) heap.poll();
        }

        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) result[i] = heap.poll();
        return result;
    }

    // Simpler version, same idea spelled out with explicit steps instead of a
    // heap: count frequencies, sort all entries by count descending, then
    // just take the first k keys. No PriorityQueue or eviction bookkeeping.
    //
    // Time: O(n log n), Space: O(n)
    public static int[] topKFrequentSimple(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int n : nums) freq.merge(n, 1, Integer::sum);

        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>(freq.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = entries.get(i).getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
        System.out.println(Arrays.toString(topKFrequentSimple(new int[] {1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
    }
}

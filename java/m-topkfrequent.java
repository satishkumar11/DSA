import java.util.*;

// Top K Frequent Elements
// Return the k most frequently occurring elements in an array.
// Count each value's frequency, then keep a min-heap of size k so only
// the k most frequent values survive by the end.
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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[] {1, 1, 1, 2, 2, 3}, 2))); // [1, 2]
    }
}

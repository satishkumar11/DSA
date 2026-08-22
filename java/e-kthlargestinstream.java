import java.util.PriorityQueue;

// Kth Largest Element in a Stream
// Design a class that tracks the kth largest element as numbers are added one at a time.
//
// Maintain a min-heap capped at size k as values stream in; the heap's
// smallest element is always the current kth largest.
//
// add: O(log k)
class KthLargestInStream {
    private final PriorityQueue<Integer> heap;
    private final int k;

    public KthLargestInStream(int k, int[] nums) {
        this.k = k;
        this.heap = new PriorityQueue<>();
        for (int n : nums) add(n);
    }

    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) heap.poll();
        return heap.peek();
    }

    public static void main(String[] args) {
        KthLargestInStream kth = new KthLargestInStream(3, new int[] {4, 5, 8, 2});
        System.out.println(kth.add(3)); // 4
        System.out.println(kth.add(5)); // 5
        System.out.println(kth.add(10)); // 5
    }
}

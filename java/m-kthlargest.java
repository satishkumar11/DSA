// hellointerview: https://www.hellointerview.com/learn/code/heap/kth-largest-element-in-an-array
import java.util.PriorityQueue;

// Kth Largest Element in an Array
// Find the kth largest element in an unsorted array.
//
// Input: nums = [3, 2, 1, 5, 6, 4], k = 2
// Output: 5
//
// Maintain a min-heap of size k while scanning the array; the smallest
// element in that heap is always the kth largest overall.
//
// Time: O(n log k), Space: O(k)
class KthLargest {
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int n : nums) {
            heap.offer(n);
            if (heap.size() > k) heap.poll();
        }

        return heap.peek();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[] {3, 2, 1, 5, 6, 4}, 2)); // 5
    }
}

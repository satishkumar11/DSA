import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

// Sliding Window Maximum
// Return the maximum value in every sliding window of size k.
// Maintain a monotonic decreasing deque of indices; the front always
// holds the max of the current window, evicting stale or smaller values.
// Time: O(n), Space: O(k)
class SlidingWindowMax {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() <= i - k) deque.pollFirst();
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) deque.pollLast();
            deque.offerLast(i);
            if (i >= k - 1) result[i - k + 1] = nums[deque.peekFirst()];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[] {1, 3, -1, -3, 5, 3, 6, 7}, 3))); // [3,3,5,5,6,7]
    }
}

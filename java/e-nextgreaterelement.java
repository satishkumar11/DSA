import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

// Next Greater Element I
// For each element in one array, find its next greater element in another array.
//
// Input: nums1 = [4, 1, 2], nums2 = [1, 3, 4, 2]
// Output: [-1, 3, -1]
//
// Monotonic decreasing stack over nums2 to precompute each value's next
// greater element in a map, then look up answers for nums1 from that map.
//
// Time: O(n + m), Space: O(n)
class NextGreaterElement {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int n : nums2) {
            while (!stack.isEmpty() && stack.peek() < n) {
                map.put(stack.pop(), n);
            }
            stack.push(n);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2}))); // [-1, 3, -1]
    }
}

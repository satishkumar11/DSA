import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// Next Greater Element I
// nums1 is a subset of nums2. For each value in nums1, find the first greater
// number that appears to its right in nums2 - if none exists, the answer is -1.
//
// Input: nums1 = [4, 1, 2], nums2 = [1, 3, 4, 2]
// Output: [-1, 3, -1]
//
// Monotonic decreasing stack over nums2 to precompute each value's next
// greater element in a map, then look up answers for nums1 from that map.
//
// Time: O(n + m), Space: O(n)
//
// Dry run building the map from nums2 = [1, 3, 4, 2]:
//   n=1: stack=[]     -> push 1                              stack=[1]
//   n=3: top 1 < 3     -> pop 1, map[1]=3; push 3             stack=[3]     map={1:3}
//   n=4: top 3 < 4     -> pop 3, map[3]=4; push 4             stack=[4]     map={1:3,3:4}
//   n=2: top 4 < 2? no -> just push 2                         stack=[4,2]   map={1:3,3:4}
//   4 and 2 are never popped -> no map entry -> answer -1
//
// Lookup for nums1 = [4, 1, 2]: 4 -> -1, 1 -> 3, 2 -> -1 => [-1, 3, -1]
class NextGreaterElement {
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int n : nums2) {
            while (!stack.isEmpty() && stack.peek() < n) {
                int value = stack.pop();
                map.put(value, n);
            }
            stack.add(n);
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

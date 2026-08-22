import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

// Two Sum
// Given an array of integers and a target, return the indices of the two numbers that add up to the target.
// Use a hash map to store each number's index as you scan the array.
// For every element, check whether its complement (target - num) has already been seen.
// Time: O(n), Space: O(n)
class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // value -> index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (seen.containsKey(complement)) {
                return new int[] { seen.get(complement), i };
            }
            seen.put(nums[i], i);
        }

        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[] {2, 7, 11, 15}, 9))); // [0, 1]
        System.out.println(Arrays.toString(twoSum(new int[] {3, 2, 4}, 6))); // [1, 2]
        System.out.println(Arrays.toString(twoSum(new int[] {3, 3}, 6))); // [0, 1]
    }
}

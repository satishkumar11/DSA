// hellointerview: https://www.hellointerview.com/learn/code/prefix-sum/subarray-sum-equals-k
import java.util.HashMap;
import java.util.Map;

// Subarray Sum Equals K
// Count the number of contiguous subarrays whose sum equals k.
//
// Input: nums = [1, 1, 1], k = 2
// Output: 2
//
// Track running prefix sums in a hash map; a subarray sums to k whenever
// (prefixSum - k) was seen before at some earlier index.
//
// Time: O(n), Space: O(n)
class SubarraySumK {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0;
        int count = 0;

        for (int n : nums) {
            sum += n;
            count += map.getOrDefault(sum - k, 0);
            map.merge(sum, 1, Integer::sum);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[] {1, 1, 1}, 2)); // 2
        System.out.println(subarraySum(new int[] {1, 2, 3}, 3)); // 2
    }
}

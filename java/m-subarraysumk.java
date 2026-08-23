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
// Trace with nums = [1, 1, 1], k = 2 (map starts {0: 1}):
//   n=1: sum=1, map.get(1-2=-1)=0 -> count=0, map={0:1, 1:1}
//   n=1: sum=2, map.get(2-2=0)=1  -> count=1, map={0:1, 1:1, 2:1}
//   n=1: sum=3, map.get(3-2=1)=1  -> count=2, map={0:1, 1:1, 2:1, 3:1}
//   count = 2  (subarrays [1,1] at indices 0-1 and 1-2)
//
// Trace with nums = [3, 4, 7, 2, -3, 1, 4, 2], k = 7 (map starts {0: 1}):
//   n=3:  sum=3,  map.get(3-7=-4)=0  -> count=0, map={0:1, 3:1}
//   n=4:  sum=7,  map.get(7-7=0)=1   -> count=1, map={0:1, 3:1, 7:1}          [3,4]
//   n=7:  sum=14, map.get(14-7=7)=1  -> count=2, map={...,14:1}               [7]
//   n=2:  sum=16, map.get(16-7=9)=0  -> count=2, map={...,16:1}
//   n=-3: sum=13, map.get(13-7=6)=0  -> count=2, map={...,13:1}
//   n=1:  sum=14, map.get(14-7=7)=1  -> count=3, map={14:2,...}               [7,2,-3,1]
//   n=4:  sum=18, map.get(18-7=11)=0 -> count=3, map={...,18:1}
//   n=2:  sum=20, map.get(20-7=13)=1 -> count=4, map={...,20:1}               [1,4,2]
//   count = 4  (subarrays [3,4], [7], [7,2,-3,1], [1,4,2])
//
// Time: O(n), Space: O(n)
class SubarraySumK {
    public static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        // seed the "empty prefix" (sum 0, before the array starts) so subarrays
        // that begin right at index 0 can still be counted below
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
        System.out.println(subarraySum(new int[] {3, 4, 7, 2, -3, 1, 4, 2}, 7)); // 4
    }
}

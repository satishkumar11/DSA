// hellointerview: https://www.hellointerview.com/learn/code/two-pointers/3-sum
import java.util.*;

// 3Sum
// Find all unique triplets in an array that sum to zero.
//
// Input: nums = [-1, 0, 1, 2, -1, -4]
// Output: [[-1, -1, 2], [-1, 0, 1]]
//
// Sort the array, fix one number, then use two pointers moving inward
// from both ends to find pairs that complete the triplet to zero.
//
// Trace with nums = [-1, 0, 1, 2, -1, -4], sorted -> [-4, -1, -1, 0, 1, 2]:
//   i=0 (-4): every l/r pair sums < 0 (biggest possible is -4-1+2=-3) -> no triplet
//   i=1 (-1): l=2,r=5 -> -1+(-1)+2=0 -> found [-1,-1,2]; l++,r-- -> l=3,r=4
//             -1+0+1=0 -> found [-1,0,1]; l++,r-- -> l=4,r=3, loop ends
//   i=2 (-1): same value as i=1 -> skip (avoids a duplicate triplet)
//   i=3 (0):  l=4,r=5 -> 0+1+2=3 > 0 -> r--, loop ends with nothing found
//   result: [[-1,-1,2], [-1,0,1]]
//
// Time: O(n^2), Space: O(1) excluding output
class ThreeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = nums.length - 1;

            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[] {-1, 0, 1, 2, -1, -4})); // [[-1,-1,2],[-1,0,1]]
    }
}

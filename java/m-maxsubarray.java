// Maximum Subarray
// Find the contiguous subarray with the largest sum (Kadane's algorithm).
//
// Input: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
// Output: 6
//
// Kadane's algorithm: at each index, either extend the previous subarray
// or start fresh, keeping whichever running sum is larger.
//
// Time: O(n), Space: O(1)
class MaxSubarray {
    public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int cur = nums[0];

        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            maxSum = Math.max(maxSum, cur);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4})); // 6
    }
}

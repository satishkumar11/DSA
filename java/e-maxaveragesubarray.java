// Maximum Average Subarray I
// Find the contiguous subarray of length k with the maximum average value.
//
// Input: nums = [1, 12, -5, -6, 50, 3], k = 4
// Output: 12.75
//
// Fixed-size sliding window: compute the first window's sum, then slide
// by adding the entering element and subtracting the one leaving.
//
// Trace with nums = [1, 12, -5, -6, 50, 3], k = 4:
//   first window [1,12,-5,-6]: sum=2, maxSum=2
//   i=4 (50 enters, nums[0]=1 leaves): sum = 2 + (50-1) = 51, maxSum=51
//   i=5 (3 enters, nums[1]=12 leaves): sum = 51 + (3-12) = 42, maxSum stays 51
//   maxSum/k = 51/4 = 12.75
//
// Time: O(n), Space: O(1)
class MaxAverageSubarray {
    public static double findMaxAverage(int[] nums, int k) {
        long sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        long maxSum = sum;

        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        return (double) maxSum / k;
    }

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[] {1, 12, -5, -6, 50, 3}, 4)); // 12.75
    }
}

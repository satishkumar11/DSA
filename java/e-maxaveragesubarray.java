// Maximum Average Subarray I
// Find the contiguous subarray of length k with the maximum average value.
// Fixed-size sliding window: compute the first window's sum, then slide
// by adding the entering element and subtracting the one leaving.
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

// Longest Increasing Subsequence
// Find the length of the longest strictly increasing subsequence in an array.
// Patience sorting: maintain the smallest possible tail value for every
// subsequence length, using binary search to place each new number.
// Time: O(n log n), Space: O(n)
class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;

        for (int n : nums) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;
                if (tails[mid] < n) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = n;
            if (lo == size) size++;
        }

        return size;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[] {10, 9, 2, 5, 3, 7, 101, 18})); // 4
    }
}

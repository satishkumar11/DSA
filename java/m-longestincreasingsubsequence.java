// hellointerview: https://www.hellointerview.com/learn/code/dynamic-programming/longest-increasing-subsequence
// Longest Increasing Subsequence
// Find the length of the longest strictly increasing subsequence in an array.
//
// Input: nums = [10, 9, 2, 5, 3, 7, 101, 18]
// Output: 4
//
// Patience sorting: tails[i] holds the smallest possible value that can end
// an increasing subsequence of length i+1, given everything scanned so far.
// For each new number, binary search tails for the leftmost tail >= it:
//   - found  -> overwrite that slot (this number is a smaller, more useful
//                tail for that length, easier to extend later)
//   - not found (bigger than every tail) -> append it, extending the
//                longest streak found so far by one
// The final length of tails is the answer. Note: tails does not necessarily
// hold an actual subsequence from the array (its values get overwritten by
// numbers seen at different points) - it only tracks the best possible tail
// per length, which is enough to get the length but not to reconstruct the
// actual LIS (that would need extra parent-pointer bookkeeping).
//
// Trace with nums = [10, 9, 2, 5, 3, 7, 101, 18]:
//   n=10:  tails=[10]
//   n=9:   tails=[9]              (9 is a smaller length-1 tail than 10)
//   n=2:   tails=[2]              (2 is smaller still)
//   n=5:   tails=[2, 5]           (5 > 2 -> extends to length 2)
//   n=3:   tails=[2, 3]           (3 is a smaller length-2 tail than 5)
//   n=7:   tails=[2, 3, 7]        (7 extends to length 3)
//   n=101: tails=[2, 3, 7, 101]   (101 extends to length 4)
//   n=18:  tails=[2, 3, 7, 18]    (18 is a smaller length-4 tail than 101)
//   length = 4  (a real LIS: 2, 3, 7, 18)
//
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

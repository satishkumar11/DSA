// Reverse Pairs
// Count pairs (i, j) where i < j and nums[i] > 2 * nums[j].
//
// Input: nums = [1, 3, 2, 3, 1]
// Output: 2
//
// Modified merge sort: while merging two already-sorted halves, count cross
// pairs before combining them. Both halves are sorted ascending, so as i
// walks the left half the matching pointer j in the right half only ever
// moves forward - no need to restart it for each i.
//
// Trace with nums = [1, 3, 2, 3, 1] (lo/mid/hi refer to the current split):
//   split [1,3] | [2,3,1] -> recurse
//     [1,3] splits to [1] | [3]: no cross pairs, merges to [1,3]
//     [2,3,1] splits to [2] | [3,1]
//       [3,1] splits to [3] | [1]: i=3, j=1 -> 3>2*1=2, j++; count=1
//                                  merges to [1,3]
//       merge [2] | [1,3]: i=2, j=1 -> 2>2*1=2? no; count stays 1
//                           merges to [1,2,3]
//   merge [1,3] | [1,2,3]: i=1 -> 1>2*1=2? no
//                           i=3 -> 3>2*1=2? yes, j->2; 3>2*2=4? no; count=2
//                           merges to [1,1,2,3,3]
//   count = 2  (pairs (1,4): 3>2*1, and (3,4): 3>2*1)
//
// Time: O(n log n), Space: O(n)
class ReversePairs {
    static int count;
    static int[] arr;

    public static int reversePairs(int[] nums) {
        arr = nums.clone();
        count = 0;
        mergeSort(0, arr.length);
        return count;
    }

    private static void mergeSort(int lo, int hi) {
        if (hi - lo <= 1) return;
        int mid = (lo + hi) / 2;
        mergeSort(lo, mid);
        mergeSort(mid, hi);

        int j = mid;
        for (int i = lo; i < mid; i++) {
            while (j < hi && (long) arr[i] > 2L * arr[j]) j++;
            count += j - mid;
        }

        int[] merged = new int[hi - lo];
        int l = lo, r = mid, k = 0;
        while (l < mid && r < hi) {
            merged[k++] = arr[l] <= arr[r] ? arr[l++] : arr[r++];
        }
        while (l < mid) merged[k++] = arr[l++];
        while (r < hi) merged[k++] = arr[r++];
        System.arraycopy(merged, 0, arr, lo, merged.length);
    }

    // Without merge sort - brute force, check every pair directly.
    //
    // Time: O(n^2), Space: O(1)
    public static int reversePairsBruteForce(int[] nums) {
        int count = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((long) nums[i] > 2L * nums[j]) count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(reversePairs(new int[] {1, 3, 2, 3, 1})); // 2
        System.out.println(reversePairsBruteForce(new int[] {1, 3, 2, 3, 1})); // 2
    }
}

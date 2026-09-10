// Sort an Array
// Sort an integer array in ascending order using merge sort.
//
// Input: nums = [5, 2, 3, 1]
// Output: [1, 2, 3, 5]
//
// Split the array in half recursively down to single elements (already
// sorted), then merge each pair of sorted halves back together in order.
//
// Trace with nums = [5, 2, 3, 1]:
//   split [5,2] | [3,1]
//     split [5] | [2]  -> merge -> [2,5]
//     split [3] | [1]  -> merge -> [1,3]
//   merge [2,5] | [1,3]:
//     compare 2,1 -> take 1  -> [1]
//     compare 2,3 -> take 2  -> [1,2]
//     compare 5,3 -> take 3  -> [1,2,3]
//     left exhausted -> append remaining 5 -> [1,2,3,5]
//
// Time: O(n log n), Space: O(n)
import java.util.Arrays;

class MergeSort {
    public static int[] mergeSort(int[] nums) {
        if (nums.length <= 1) return nums;

        int mid = nums.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(nums, 0, mid));
        int[] right = mergeSort(Arrays.copyOfRange(nums, mid, nums.length));

        return merge(left, right);
    }

    private static int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            merged[k++] = left[i] <= right[j] ? left[i++] : right[j++];
        }
        while (i < left.length) merged[k++] = left[i++];
        while (j < right.length) merged[k++] = right[j++];

        return merged;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeSort(new int[] {5, 2, 3, 1}))); // [1, 2, 3, 5]
    }
}

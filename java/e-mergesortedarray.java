import java.util.Arrays;

// Merge Sorted Array
// Merge a second sorted array into the first, which has extra trailing space.
// Merge from the back: compare the largest remaining elements of both
// arrays and place the bigger one at the end of nums1's true length.
// Time: O(m + n), Space: O(1)
class MergeSortedArray {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        merge(nums1, 3, new int[] {2, 5, 6}, 3);
        System.out.println(Arrays.toString(nums1)); // [1, 2, 2, 3, 5, 6]
    }
}

import java.util.Arrays;

// Merge Sorted Array
// Merge a second sorted array into the first, which has extra trailing space.
//
// Input: nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
// Output: [1, 2, 2, 3, 5, 6]
//
// Merge from the back: compare the largest remaining elements of both
// arrays and place the bigger one at the end of nums1's true length.
//
// Time: O(m + n), Space: O(1)
//
// Dry run with nums1 = [1,2,3,0,0,0], m=3, nums2=[2,5,6], n=3:
//   i=2,j=2,k=5: 3 vs 6 -> 6 bigger        write nums1[5]=6         [1,2,3,0,0,6]
//   i=2,j=1,k=4: 3 vs 5 -> 5 bigger        write nums1[4]=5         [1,2,3,0,5,6]
//   i=2,j=0,k=3: 3 vs 2 -> 3 bigger, i=1   write nums1[3]=3         [1,2,3,3,5,6]
//   i=1,j=0,k=2: 2 vs 2 -> not >, j wins   write nums1[2]=2, j=-1   [1,2,2,3,5,6]
//   j < 0 -> loop ends -> [1, 2, 2, 3, 5, 6]
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

import java.util.Arrays;

// Remove Duplicates from Sorted Array
// Remove duplicates in place from a sorted array and return the new length.
//
// Input: nums = [1, 1, 2, 2, 3]
// Output: 3 (array becomes [1, 2, 3, ...])
//
// Two pointers: a slow pointer marks the last unique value written, a fast
// pointer scans ahead and copies in any new distinct value.
//
// Time: O(n), Space: O(1)
class RemoveDuplicates {
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;

        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(nums) + " " + Arrays.toString(nums)); // 3 [1, 2, 3, 2, 3]
    }
}

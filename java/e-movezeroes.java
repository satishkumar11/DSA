// hellointerview: https://www.hellointerview.com/learn/code/two-pointers/move-zeroes
import java.util.Arrays;

// Move Zeroes
// Move all zeroes to the end of an array while keeping the relative order of non-zero elements.
//
// Input: nums = [0, 1, 0, 3, 12]
// Output: [1, 3, 12, 0, 0]
//
// Two pointers i and j: advance i past values already confirmed non-zero,
// advance j past zeros, then swap when nums[i] is a zero blocking a non-zero at j.
//
// Time: O(n), Space: O(1)
class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int i = 0;
        int j = 1;

        while (j < nums.length) {
            if (nums[i] != 0) {
                i++;
                if (i >= j) {
                    j++;
                }
            } else if (nums[j] == 0) {
                j++;
            } else {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
                j++;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums)); // [1, 3, 12, 0, 0]
    }
}

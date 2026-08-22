import java.util.Arrays;

// Move Zeroes
// Move all zeroes to the end of an array while keeping the relative order of non-zero elements.
//
// Input: nums = [0, 1, 0, 3, 12]
// Output: [1, 3, 12, 0, 0]
//
// Two pointers: an insert pointer tracks where the next non-zero value
// goes; after copying all non-zeros forward, fill the remaining tail with zeros.
//
// Time: O(n), Space: O(1)
class MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int insertPos = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[insertPos] = nums[i];
                insertPos++;
            }
        }

        for (int i = insertPos; i < nums.length; i++) nums[i] = 0;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums)); // [1, 3, 12, 0, 0]
    }
}

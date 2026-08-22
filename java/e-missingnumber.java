// Missing Number
// Find the missing number in an array containing n distinct numbers from 0 to n.
// Time: O(n), Space: O(1)
class MissingNumber {
    public static int missingNumber(int[] nums) {
        int result = nums.length;

        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[] {3, 0, 1})); // 2
    }
}

// Single Number
// Every element in the array appears twice except for one - find that one.
//
// Input: nums = [4, 1, 2, 1, 2]
// Output: 4
//
// XOR every number together. x ^ x = 0, so every pair cancels itself out
// regardless of order, leaving only the number with no pair.
//
// Trace with nums = [4, 1, 2, 1, 2]:
//   result = 0
//   0 ^ 4 = 4
//   4 ^ 1 = 5
//   5 ^ 2 = 7
//   7 ^ 1 = 6   (the first 1 cancels out)
//   6 ^ 2 = 4   (the first 2 cancels out)
//   result = 4
//
// Time: O(n), Space: O(1)
class SingleNumber {
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int n : nums) {
            result ^= n;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(singleNumber(new int[] {4, 1, 2, 1, 2})); // 4
    }
}

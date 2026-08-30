// hellointerview: https://www.hellointerview.com/learn/code/two-pointers/two-sum
import java.util.Arrays;

// Two Sum II - Input Array Is Sorted
// Given a sorted array, return the 1-indexed positions of the two numbers
// that add up to target. Unlike the classic Two Sum, being sorted means a
// two-pointer sweep works instead of a hash map.
//
// Input: numbers = [2, 7, 11, 15], target = 9
// Output: [1, 2]
//
// Two pointers from both ends: if the pair sums too high, the right value
// is too big, so move the right pointer in; if too low, move the left
// pointer up. Because the array is sorted, this always converges correctly.
//
// Trace with numbers = [2, 7, 11, 15], target = 9:
//   l=0, r=3: 2+15=17 > 9 -> too high -> r--
//   l=0, r=2: 2+11=13 > 9 -> too high -> r--
//   l=0, r=1: 2+7=9 == 9  -> found -> return [1, 2] (1-indexed)
//
// Time: O(n), Space: O(1)
class TwoSumSorted {
    public static int[] twoSumSorted(int[] numbers, int target) {
        int l = 0, r = numbers.length - 1;

        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) return new int[] {l + 1, r + 1};
            if (sum < target) l++;
            else r--;
        }

        return new int[0];
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSumSorted(new int[] {2, 7, 11, 15}, 9))); // [1, 2]
    }
}

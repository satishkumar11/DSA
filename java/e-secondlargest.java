// Second Largest Element in an Array
// Find the second largest distinct value in an array.
//
// Input: nums = [12, 35, 1, 10, 34, 1]
// Output: 34
//
// Track the largest and second-largest values seen so far in one pass,
// updating both whenever a new maximum is found.
//
// Trace with nums = [12, 35, 1, 10, 34, 1]:
//   12: 12 > first(-inf) -> second=-inf, first=12
//   35: 35 > first(12)   -> second=12,   first=35
//   1:  not > first, not (> second and < first) -> no change
//   10: not > first, not (> second and < first) -> no change
//   34: not > first, but 34 > second(12) and 34 < first(35) -> second=34
//   1:  no change
//   second = 34
//
// Time: O(n), Space: O(1)
class SecondLargest {
    public static int secondLargest(int[] nums) {
        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int n : nums) {
            if (n > first) {
                second = first;
                first = n;
            } else if (n > second && n < first) {
                second = n;
            }
        }

        return second == Integer.MIN_VALUE ? -1 : second;
    }

    public static void main(String[] args) {
        System.out.println(secondLargest(new int[] {12, 35, 1, 10, 34, 1})); // 34
    }
}

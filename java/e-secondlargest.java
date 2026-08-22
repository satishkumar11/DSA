// Second Largest Element in an Array
// Find the second largest distinct value in an array.
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

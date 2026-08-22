// Majority Element
// Find the element that appears more than n/2 times in an array.
// Time: O(n), Space: O(1)
class MajorityElement {
    public static int majorityElement(int[] nums) {
        int count = 0, candidate = 0;

        for (int n : nums) {
            if (count == 0) candidate = n;
            count += (n == candidate) ? 1 : -1;
        }

        return candidate;
    }

    public static void main(String[] args) {
        System.out.println(majorityElement(new int[] {2, 2, 1, 1, 1, 2, 2})); // 2
    }
}

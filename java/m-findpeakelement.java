// Find Peak Element
// Find an index whose value is greater than both its neighbors.
// Time: O(log n), Space: O(1)
class FindPeakElement {
    public static int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[mid + 1]) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }

    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[] {1, 2, 3, 1})); // 2
        System.out.println(findPeakElement(new int[] {1, 2, 1, 3, 5, 6, 4})); // 1 or 5
    }
}

// Find Minimum in Rotated Sorted Array
// Find the minimum element in a sorted array that has been rotated.
// Time: O(log n), Space: O(1)
class FindMinInRotated {
    public static int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1;
            else hi = mid;
        }

        return nums[lo];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] {3, 4, 5, 1, 2})); // 1
    }
}

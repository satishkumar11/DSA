// Binary Search
// Find the index of a target value in a sorted array.
// Time: O(log n), Space: O(1)
class BinarySearch {
    public static int binarySearch(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(binarySearch(new int[] {-1, 0, 3, 5, 9, 12}, 9)); // 4
    }
}

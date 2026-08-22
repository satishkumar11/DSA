// Search in Rotated Sorted Array
// Find a target's index in a sorted array that has been rotated at an unknown pivot.
// Time: O(log n), Space: O(1)
class SearchRotated {
    public static int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == target) return mid;

            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[] {4, 5, 6, 7, 0, 1, 2}, 0)); // 4
    }
}

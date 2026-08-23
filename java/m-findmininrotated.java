// Find Minimum in Rotated Sorted Array
// Find the minimum element in a sorted array that has been rotated.
//
// Input: nums = [3, 4, 5, 1, 2]
// Output: 1
//
// Binary search comparing the middle element to the rightmost element to
// decide which half contains the rotation point (and the minimum).
//
// Trace with nums = [3, 4, 5, 1, 2]:
//   lo=0, hi=4: mid=2, nums[2]=5 > nums[4]=2 -> minimum is past mid -> lo=3
//   lo=3, hi=4: mid=3, nums[3]=1 > nums[4]=2? no -> minimum is at mid or before -> hi=3
//   lo == hi (3) -> loop ends -> return nums[3] = 1
//
// Time: O(log n), Space: O(1)
class FindMinInRotated {
    public static int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1;
            else hi = mid;
        }

        return nums[lo];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[] {3, 4, 5, 1, 2})); // 1
    }
}

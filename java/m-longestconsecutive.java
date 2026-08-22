import java.util.HashSet;
import java.util.Set;

// Longest Consecutive Sequence
// Find the length of the longest run of consecutive integers in an unsorted array.
// Time: O(n), Space: O(n)
class LongestConsecutive {
    public static int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) set.add(n);

        int longest = 0;
        for (int n : set) {
            if (!set.contains(n - 1)) {
                int length = 1;
                while (set.contains(n + length)) length++;
                longest = Math.max(longest, length);
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive(new int[] {100, 4, 200, 1, 3, 2})); // 4
    }
}

import java.util.HashSet;
import java.util.Set;

// Contains Duplicate
// Determine whether any value appears more than once in an array.
// Add each number to a hash set while scanning; if a number is already
// in the set, a duplicate has been found.
// Time: O(n), Space: O(n)
class ContainsDuplicate {
    public static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int n : nums) {
            if (!seen.add(n)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsDuplicate(new int[] {1, 2, 3, 1})); // true
        System.out.println(containsDuplicate(new int[] {1, 2, 3, 4})); // false
    }
}

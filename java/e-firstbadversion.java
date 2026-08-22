import java.util.function.IntPredicate;

// First Bad Version
// Find the first bad version using the fewest calls to an isBadVersion API.
// Time: O(log n), Space: O(1)
class FirstBadVersion {
    public static int firstBadVersion(int n, IntPredicate isBadVersion) {
        int lo = 1, hi = n;

        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (isBadVersion.test(mid)) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }

    public static void main(String[] args) {
        System.out.println(firstBadVersion(5, v -> v >= 4)); // 4
    }
}

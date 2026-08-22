// Climbing Stairs
// Count the number of distinct ways to climb n stairs taking 1 or 2 steps at a time.
// The number of ways to reach step n is the sum of ways to reach step
// n-1 and step n-2, so track just those two running values (Fibonacci).
// Time: O(n), Space: O(1)
class ClimbingStairs {
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2;

        for (int i = 3; i <= n; i++) {
            int next = a + b;
            a = b;
            b = next;
        }

        return b;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs(5)); // 8
    }
}

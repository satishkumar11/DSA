// Climbing Stairs
// Count the number of distinct ways to climb n stairs taking 1 or 2 steps at a time.
//
// Input: n = 5
// Output: 8
//
// Bottom-up DP array: ways(i) = ways(i-1) + ways(i-2), same recurrence as
// Fibonacci, built iteratively so no recomputation is needed.
//
// Time: O(n), Space: O(n)
class ClimbingStairs {
    public int climbStairs(int n) {
        return getWays(n);
    }

    public int getWays(int n) {
        int[] DP = new int[n + 2];
        DP[0] = 1;
        DP[1] = 1;

        for (int i = 2; i <= n; i++) {
            DP[i] = DP[i - 1] + DP[i - 2];
        }
        return DP[n];
    }

    // Time: O(2^n), Space: O(n) call stack
    // public int getWays(int n){
    //     if(n<=1){
    //         return n;
    //     }
    //     return (getWays(n-1) + getWays(n-2));
    // }

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs(5)); // 8
    }
}

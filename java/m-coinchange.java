import java.util.Arrays;

// Coin Change
// Find the fewest number of coins needed to make up a given amount.
//
// Bottom-up DP: for each amount from 1 to target, try every coin and take
// the minimum coins needed using dp[amount - coin] + 1.
//
// Time: O(amount * coins), Space: O(amount)
class CoinChange {
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (c <= i && dp[i - c] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - c] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[] {1, 2, 5}, 11)); // 3
    }
}

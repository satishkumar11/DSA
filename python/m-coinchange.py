# Coin Change
# Find the fewest number of coins needed to make up a given amount.
#
# Input: coins = [1, 2, 5], amount = 11
# Output: 3
#
# dp[i] = minimum coins needed to make amount i (infinity if impossible).
# dp[0] = 0 - zero coins make zero amount. For every amount i, try each coin
# c as the "last coin used": if it fits (c <= i), using it costs whatever it
# took to make the remaining amount (dp[i - c]) plus this one coin. Take
# whichever coin choice gives the smallest total at each amount.
#
# dp table for coins = [1, 2, 5], amount = 11:
#   i:   0  1  2  3  4  5  6  7  8  9  10  11
#   dp:  0  1  1  2  2  1  2  2  3  3  2   3
#   (dp[11] = 3, e.g. 5 + 5 + 1 - no combination does it in fewer coins)
#
# How dp[11] gets built - one arrow per coin, each pointing from an
# already-solved smaller amount into the cell it feeds:
#
#   dp[10]=2 --(coin 1)--\
#   dp[9] =3 --(coin 2)---+-->  dp[11] = 1 + min(2, 3, 2) = 3
#   dp[6] =2 --(coin 5)--/
#
# This is exactly why the DP version beats the recursive one: dp[6], dp[9],
# and dp[10] are each computed once and then simply looked up here, instead
# of being solved from scratch every time a coin path needs them.
#
# Time: O(amount * coins), Space: O(amount)
def coin_change(coins, amount):
    dp = [float('inf')] * (amount + 1)
    dp[0] = 0

    for i in range(1, amount + 1):
        for c in coins:
            if c <= i:
                dp[i] = min(dp[i], dp[i - c] + 1)

    if dp[amount] == float('inf'):
        return -1
    return dp[amount]


# Without DP - plain recursion, trying every coin at every remaining amount.
# Massively overlapping subproblems (e.g. remaining=6 is recomputed many times
# via different coin paths), so this is exponential instead of O(amount * coins).
#
# Recursion tree for helper(11), coins = [1, 2, 5] (first two levels only):
#
#                              helper(11)
#                  /               |               \
#           coin 1                coin 2            coin 5
#              /                     |                  \
#        helper(10)              helper(9)            helper(6)
#        /   |   \               /   |   \            /   |   \
#    h(9) h(8) h(5)          h(8) h(7) h(4)        h(5) h(4) h(1)
#
# Look at helper(9): it's a direct child of helper(11) (via coin 2), but it
# also shows up again as a grandchild (via helper(10) -> coin 1). Same story
# for helper(8), helper(5), helper(4) - every one of them gets fully
# re-explored from scratch each time it's reached by a different coin path.
# That duplication compounds at every level, which is exactly what dp[]
# eliminates by solving each amount exactly once.
#
# Time: O(coins ^ amount), Space: O(amount) recursion depth
def coin_change_brute_force(coins, amount):
    def helper(remaining):
        if remaining == 0:
            return 0
        if remaining < 0:
            return float('inf')

        min_coins = float('inf')
        for c in coins:
            result = helper(remaining - c)
            if result != float('inf'):
                min_coins = min(min_coins, result + 1)
        return min_coins

    result = helper(amount)
    if result == float('inf'):
        return -1
    return result


print(coin_change([1, 2, 5], 11))  # 3
print(coin_change_brute_force([1, 2, 5], 11))  # 3

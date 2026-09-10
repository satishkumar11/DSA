# Climbing Stairs
# Count the number of distinct ways to climb n stairs taking 1 or 2 steps at a time.
#
# Input: n = 5
# Output: 8
#
# Bottom-up DP array: ways(i) = ways(i-1) + ways(i-2), same recurrence as
# Fibonacci, built iteratively so no recomputation is needed.
#
# Time: O(n), Space: O(n)
def climb_stairs(n):
    return get_ways(n)


def get_ways(n):
    dp = [0] * (n + 2)
    dp[0] = 1
    dp[1] = 1

    for i in range(2, n + 1):
        dp[i] = dp[i - 1] + dp[i - 2]
    return dp[n]


# Time: O(2^n), Space: O(n) call stack
# def get_ways(n):
#     if n <= 1:
#         return n
#     return get_ways(n - 1) + get_ways(n - 2)

print(climb_stairs(5))  # 8

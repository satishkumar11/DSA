# Edit Distance
# Find the minimum number of insert/delete/replace operations to convert one word into another.
#
# Input: word1 = "horse", word2 = "ros"
# Output: 3
#
# 2D DP where dp[i][j] is the edit distance between the first i and j
# characters; match chars for free, otherwise take the best of insert/delete/replace.
#
# Trace with word1 = "horse", word2 = "ros" (dp[i][j] = edit distance
# between the first i chars of word1 and first j chars of word2):
#        ""  r  o  s
#    ""   0  1  2  3
#    h    1  1  2  3
#    o    2  2  1  2
#    r    3  2  2  2
#    s    4  3  3  2
#    e    5  4  4  3
#   Notice dp["o"]["o"] = 1 (chars match, free - just carries dp[0][0]=0... plus 1 row shift)
#   and dp["s"]["s"] = 2 (match, carries the diagonal value). The final
#   answer dp[5][3] = 3 matches "horse" -> "rorse" -> "rose" -> "ros"
#   (replace h->r, delete r, delete e).
#
# Time: O(m * n), Space: O(m * n)
def min_distance(word1, word2):
    m = len(word1)
    n = len(word2)
    dp = []
    for i in range(m + 1):
        dp.append([0] * (n + 1))

    for i in range(m + 1):
        dp[i][0] = i
    for j in range(n + 1):
        dp[0][j] = j

    for i in range(1, m + 1):
        for j in range(1, n + 1):
            if word1[i - 1] == word2[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            else:
                dp[i][j] = 1 + min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1])

    return dp[m][n]


# Simpler version: plain recursion, no dp table. At each pair of positions,
# either the characters match for free, or try all three edits and take
# the cheapest. Exponential without memoization since the same (i, j) pair
# gets re-explored on many different recursive paths.
#
# Time: O(3^(m+n)) worst case, Space: O(m + n) recursion depth
def min_distance_simple(word1, word2):
    def helper(i, j):
        if i == len(word1):
            return len(word2) - j
        if j == len(word2):
            return len(word1) - i

        if word1[i] == word2[j]:
            return helper(i + 1, j + 1)

        return 1 + min(
            helper(i + 1, j),      # delete from word1
            helper(i, j + 1),      # insert into word1
            helper(i + 1, j + 1),  # replace
        )

    return helper(0, 0)


print(min_distance('horse', 'ros'))  # 3
print(min_distance_simple('horse', 'ros'))  # 3

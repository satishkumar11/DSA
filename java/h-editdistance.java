// Edit Distance
// Find the minimum number of insert/delete/replace operations to convert one word into another.
//
// Input: word1 = "horse", word2 = "ros"
// Output: 3
//
// 2D DP where dp[i][j] is the edit distance between the first i and j
// characters; match chars for free, otherwise take the best of insert/delete/replace.
//
// Trace with word1 = "horse", word2 = "ros" (dp[i][j] = edit distance
// between the first i chars of word1 and first j chars of word2):
//        ""  r  o  s
//    ""   0  1  2  3
//    h    1  1  2  3
//    o    2  2  1  2
//    r    3  2  2  2
//    s    4  3  3  2
//    e    5  4  4  3
//   Notice dp["o"]["o"] = 1 and dp["s"]["s"] = 2 - both matches, so they
//   just carry the diagonal value forward with no extra cost. The final
//   answer dp[5][3] = 3 matches "horse" -> "rorse" -> "rose" -> "ros"
//   (replace h->r, delete r, delete e).
//
// Time: O(m * n), Space: O(m * n)
class EditDistance {
    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        return dp[m][n];
    }

    // Simpler version: plain recursion, no dp table. At each pair of
    // positions, either the characters match for free, or try all three
    // edits and take the cheapest. Exponential without memoization since the
    // same (i, j) pair gets re-explored on many different recursive paths.
    //
    // Time: O(3^(m+n)) worst case, Space: O(m + n) recursion depth
    public static int minDistanceSimple(String word1, String word2) {
        return helper(word1, word2, 0, 0);
    }

    private static int helper(String word1, String word2, int i, int j) {
        if (i == word1.length()) return word2.length() - j;
        if (j == word2.length()) return word1.length() - i;

        if (word1.charAt(i) == word2.charAt(j)) {
            return helper(word1, word2, i + 1, j + 1);
        }

        int delete = helper(word1, word2, i + 1, j);
        int insert = helper(word1, word2, i, j + 1);
        int replace = helper(word1, word2, i + 1, j + 1);
        return 1 + Math.min(delete, Math.min(insert, replace));
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros")); // 3
        System.out.println(minDistanceSimple("horse", "ros")); // 3
    }
}

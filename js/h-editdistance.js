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
//   Notice dp["o"]["o"] = 1 (chars match, free - just carries dp[0][0]=0... plus 1 row shift)
//   and dp["s"]["s"] = 2 (match, carries the diagonal value). The final
//   answer dp[5][3] = 3 matches "horse" -> "rorse" -> "rose" -> "ros"
//   (replace h->r, delete r, delete e).
//
// Time: O(m * n), Space: O(m * n)
function minDistance(word1, word2) {
  const m = word1.length;
  const n = word2.length;
  const dp = Array.from({ length: m + 1 }, () => new Array(n + 1).fill(0));

  for (let i = 0; i <= m; i++) dp[i][0] = i;
  for (let j = 0; j <= n; j++) dp[0][j] = j;

  for (let i = 1; i <= m; i++) {
    for (let j = 1; j <= n; j++) {
      if (word1[i - 1] === word2[j - 1]) {
        dp[i][j] = dp[i - 1][j - 1];
      } else {
        dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]);
      }
    }
  }

  return dp[m][n];
}

// Simpler version: plain recursion, no dp table. At each pair of positions,
// either the characters match for free, or try all three edits and take
// the cheapest. Exponential without memoization since the same (i, j) pair
// gets re-explored on many different recursive paths.
//
// Time: O(3^(m+n)) worst case, Space: O(m + n) recursion depth
function minDistanceSimple(word1, word2) {
  function helper(i, j) {
    if (i === word1.length) return word2.length - j;
    if (j === word2.length) return word1.length - i;

    if (word1[i] === word2[j]) {
      return helper(i + 1, j + 1);
    }

    return 1 + Math.min(
      helper(i + 1, j), // delete from word1
      helper(i, j + 1), // insert into word1
      helper(i + 1, j + 1) // replace
    );
  }

  return helper(0, 0);
}

console.log(minDistance('horse', 'ros')); // 3
console.log(minDistanceSimple('horse', 'ros')); // 3

module.exports = minDistance;

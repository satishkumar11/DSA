// Word Break
// Determine if a string can be segmented into a space-separated sequence of dictionary words.
// DP where dp[i] means the prefix of length i is breakable; dp[i] is true
// if some earlier breakable point j has s[j:i] as a dictionary word.
// Time: O(n^2), Space: O(n)
function wordBreak(s, wordDict) {
  const wordSet = new Set(wordDict);
  const dp = new Array(s.length + 1).fill(false);
  dp[0] = true;

  for (let i = 1; i <= s.length; i++) {
    for (let j = 0; j < i; j++) {
      if (dp[j] && wordSet.has(s.substring(j, i))) {
        dp[i] = true;
        break;
      }
    }
  }

  return dp[s.length];
}

console.log(wordBreak('leetcode', ['leet', 'code'])); // true

module.exports = wordBreak;

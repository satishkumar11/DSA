// hellointerview: https://www.hellointerview.com/learn/code/dynamic-programming/word-break
// Word Break
// Determine if a string can be segmented into a space-separated sequence of dictionary words.
//
// Input: s = "leetcode", wordDict = ["leet","code"]
// Output: true
//
// DP where dp[i] means the prefix of length i is breakable; dp[i] is true
// if some earlier breakable point j has s[j:i] as a dictionary word.
//
// Trace with s = "leetcode", wordDict = ["leet","code"]:
//   dp[0]=true (empty prefix is trivially breakable)
//   dp[1..3]: no j gives a dictionary word ("l","le",..,"lee") -> all false
//   dp[4]: j=0, dp[0]=true and s[0:4]="leet" is in the dict -> dp[4]=true
//   dp[5..7]: dp[4]=true, but s[4:5]="c", s[4:6]="co", s[4:7]="cod" -> none
//             in the dict, and no other breakable j works either -> all false
//   dp[8]: j=4, dp[4]=true and s[4:8]="code" is in the dict -> dp[8]=true
//   dp[8] (= dp[s.length]) = true
//
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

// Simpler version, no dp array or indices to track: try every prefix of
// what's left, and if it's a dictionary word, recurse on the rest of the
// string. Easier to follow, but exponential without memoization since the
// same "remaining" substring can get re-explored many times.
//
// Time: O(2^n) worst case, Space: O(n) recursion depth
function wordBreakSimple(s, wordDict) {
  const wordSet = new Set(wordDict);

  function canBreak(remaining) {
    console.log("remaining = ", remaining);
    if (remaining === '') return true;

    for (let end = 1; end <= remaining.length; end++) {
      const prefix = remaining.slice(0, end);
      if (wordSet.has(prefix) && canBreak(remaining.slice(end))) {
        return true;
      }
    }

    return false;
  }

  return canBreak(s);
}

console.log(wordBreak('leetcode', ['leet', 'code'])); // true
console.log(wordBreakSimple('leetcode', ['leet', 'code'])); // true

module.exports = wordBreak;

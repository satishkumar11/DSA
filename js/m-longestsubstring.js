// hellointerview: https://www.hellointerview.com/learn/code/sliding-window/longest-substring-without-repeating-characters
// Longest Substring Without Repeating Characters
// Find the length of the longest substring with no repeated characters.
//
// Input: s = "abcabcbb"
// Output: 3
//
// Sliding window with a map of last-seen index per character; on a repeat,
// pull the window's left edge past that character's earlier position -
// Math.max keeps left from ever moving backward on a stale/older match.
//
// Trace with s = "abcabcbb":
//   a(0): new -> seen={a:0}, window "a", best=1
//   b(1): new -> seen={a:0,b:1}, window "ab", best=2
//   c(2): new -> seen={a:0,b:1,c:2}, window "abc", best=3
//   a(3): seen at 0 -> left=max(0,0+1)=1, window "bca", best stays 3
//   b(4): seen at 1 -> left=max(1,1+1)=2, window "cab", best stays 3
//   c(5): seen at 2 -> left=max(2,2+1)=3, window "abc", best stays 3
//   b(6): seen at 4 -> left=max(3,4+1)=5, window "cb",  best stays 3
//   b(7): seen at 6 -> left=max(5,6+1)=7, window "b",   best stays 3
//   best = 3
//
// Time: O(n), Space: O(min(n, charset))
function lengthOfLongestSubstring(s) {
  if (!s || s.length === 0) return 0;

  const seen = new Map();
  const n = s.length;
  let left = 0;
  let best = 0;

  for (let right = 0; right < n; right++) {
    const ch = s[right];
    if (seen.has(ch)) {
      left = Math.max(left, seen.get(ch) + 1);
    }
    seen.set(ch, right);
    best = Math.max(best, right - left + 1);
  }

  return best;
}

console.log(lengthOfLongestSubstring('abcabcbb')); // 3
console.log(lengthOfLongestSubstring('pwwkew')); // 3

module.exports = lengthOfLongestSubstring;

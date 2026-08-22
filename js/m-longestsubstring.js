// Longest Substring Without Repeating Characters
// Find the length of the longest substring with no repeated characters.
// Time: O(n), Space: O(min(n, charset))
function lengthOfLongestSubstring(s) {
  const seen = new Map();
  let start = 0;
  let maxLen = 0;

  for (let end = 0; end < s.length; end++) {
    const c = s[end];
    if (seen.has(c) && seen.get(c) >= start) start = seen.get(c) + 1;
    seen.set(c, end);
    maxLen = Math.max(maxLen, end - start + 1);
  }

  return maxLen;
}

console.log(lengthOfLongestSubstring('abcabcbb')); // 3
console.log(lengthOfLongestSubstring('pwwkew')); // 3

module.exports = lengthOfLongestSubstring;

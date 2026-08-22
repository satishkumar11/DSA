// Longest Palindromic Substring
// Find the longest substring that reads the same forwards and backwards.
// Time: O(n^2), Space: O(1)
function longestPalindrome(s) {
  if (!s) return '';
  let start = 0;
  let end = 0;

  const expand = (l, r) => {
    while (l >= 0 && r < s.length && s[l] === s[r]) {
      l--;
      r++;
    }
    return r - l - 1;
  };

  for (let i = 0; i < s.length; i++) {
    const len1 = expand(i, i);
    const len2 = expand(i, i + 1);
    const len = Math.max(len1, len2);
    if (len > end - start + 1) {
      start = i - Math.floor((len - 1) / 2);
      end = i + Math.floor(len / 2);
    }
  }

  return s.substring(start, end + 1);
}

console.log(longestPalindrome('babad')); // "bab" or "aba"

module.exports = longestPalindrome;

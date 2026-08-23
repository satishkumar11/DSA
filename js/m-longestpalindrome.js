// Longest Palindromic Substring
// Find the longest substring that reads the same forwards and backwards.
//
// Input: s = "babad"
// Output: "bab" (or "aba")
//
// Expand around every possible center (both single and double character)
// and track the widest palindrome found.
//
// Trace with s = "babad":
//   i=0 'b': odd-center "b" len 1, even-center "ba" len 0 -> best stays "" (len 1 not > current 1)
//   i=1 'a': odd-center expands "b|a|b" -> "bab" len 3, even-center "ab" len 0
//            3 > current best(1) -> best = "bab" (start=0, end=2)
//   i=2 'b': odd-center expands "a|b|a" -> "aba" len 3, but 3 is not > current best(3) -> no change
//   i=3,4:   nothing beats length 3
//   result: "bab" (the algorithm keeps the first length-3 palindrome it finds)
//
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

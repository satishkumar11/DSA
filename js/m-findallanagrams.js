// Find All Anagrams in a String
// Find all starting indices of p's anagrams in s.
//
// Sliding window of p's length over s, comparing 26-letter frequency
// counts and recording every position where they match exactly.
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
function findAnagrams(s, p) {
  const result = [];
  if (s.length < p.length) return result;

  const need = new Array(26).fill(0);
  const window = new Array(26).fill(0);
  const base = 'a'.charCodeAt(0);

  for (const c of p) need[c.charCodeAt(0) - base]++;

  for (let i = 0; i < s.length; i++) {
    window[s.charCodeAt(i) - base]++;
    if (i >= p.length) window[s.charCodeAt(i - p.length) - base]--;
    if (i >= p.length - 1 && need.every((v, idx) => v === window[idx])) {
      result.push(i - p.length + 1);
    }
  }

  return result;
}

console.log(findAnagrams('cbaebabacd', 'abc')); // [0, 6]

module.exports = findAnagrams;

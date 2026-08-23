// Find All Anagrams in a String
// Given strings s and p, find every starting index in s where a substring of
// length p.length is an anagram of p (same letters, any order).
//
// Input: s = "cbaebabacd", p = "abc"  (p.length = 3, so check every length-3 window of s)
// Output: [0, 6]
//   index 0 -> s[0..2] = "cba" -> an anagram of "abc"
//   index 6 -> s[6..8] = "bac" -> an anagram of "abc"
//
// Sliding window of p's length over s, comparing 26-letter frequency
// counts and recording every position where they match exactly.
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
function findAnagrams(s, p) {
  const result = [];
  if (s.length < p.length) return result;

  const patternCounts = new Array(26).fill(0);
  const windowCounts = new Array(26).fill(0);
  const charCodeA = 'a'.charCodeAt(0);

  for (const c of p) patternCounts[c.charCodeAt(0) - charCodeA]++;

  // build the first window and check it
  for (let i = 0; i < p.length; i++) {
    windowCounts[s.charCodeAt(i) - charCodeA]++;
  }
  if (countsMatch(patternCounts, windowCounts)) result.push(0);

  // slide the window one character at a time: drop the char leaving on the
  // left, add the char entering on the right, then check the new window
  for (let start = 1; start <= s.length - p.length; start++) {
    const charLeaving = s.charCodeAt(start - 1) - charCodeA;
    const charEntering = s.charCodeAt(start + p.length - 1) - charCodeA;

    windowCounts[charLeaving]--;
    windowCounts[charEntering]++;

    if (countsMatch(patternCounts, windowCounts)) result.push(start);
  }

  return result;
}

function countsMatch(patternCounts, windowCounts) {
  for (let i = 0; i < patternCounts.length; i++) {
    if (patternCounts[i] !== windowCounts[i]) return false;
  }
  return true;
}

console.log(findAnagrams('cbaebabacd', 'abc')); // [0, 6]

module.exports = findAnagrams;

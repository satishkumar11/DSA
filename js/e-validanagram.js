// Valid Anagram
// Determine if string t is an anagram of string s (same characters, same frequency).
//
// Input: s = "anagram", t = "nagaram"
// Output: true
//
// Count character frequencies of both strings into a single 26-slot array,
// incrementing for s and decrementing for t; all zeros means an anagram.
//
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
function isAnagram(s, t) {
  if (s.length !== t.length) return false;

  const counts = new Array(26).fill(0);
  const base = 'a'.charCodeAt(0);

  for (let i = 0; i < s.length; i++) {
    counts[s.charCodeAt(i) - base]++;
    counts[t.charCodeAt(i) - base]--;
  }

  return counts.every((c) => c === 0);
}

console.log(isAnagram('anagram', 'nagaram')); // true
console.log(isAnagram('rat', 'car')); // false

module.exports = isAnagram;

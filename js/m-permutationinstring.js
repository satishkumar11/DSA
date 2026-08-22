// Permutation in String
// Determine if one string contains a permutation of another as a substring.
// Sliding window of s1's length over s2, comparing 26-letter frequency
// counts of the window against s1's counts at every position.
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
function checkInclusion(s1, s2) {
  if (s1.length > s2.length) return false;

  const need = new Array(26).fill(0);
  const window = new Array(26).fill(0);
  const base = 'a'.charCodeAt(0);

  for (const c of s1) need[c.charCodeAt(0) - base]++;

  for (let i = 0; i < s2.length; i++) {
    window[s2.charCodeAt(i) - base]++;
    if (i >= s1.length) window[s2.charCodeAt(i - s1.length) - base]--;
    if (i >= s1.length - 1 && need.every((v, idx) => v === window[idx])) return true;
  }

  return false;
}

console.log(checkInclusion('ab', 'eidbaooo')); // true
console.log(checkInclusion('ab', 'eidboaoo')); // false

module.exports = checkInclusion;

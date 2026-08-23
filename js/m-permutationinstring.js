// Permutation in String
// Determine if one string contains a permutation of another as a substring.
//
// Input: s1 = "ab", s2 = "eidbaooo"
// Output: true
//
// Sliding window of s1's length over s2, comparing 26-letter frequency
// counts of the window against s1's counts at every position.
//
// Trace with s1 = "ab" (need: a=1,b=1), s2 = "eidbaooo":
//   i=0 'e': window={e:1} - window too short to check yet (i < s1.length-1)
//   i=1 'i': window={e:1,i:1} - doesn't match need
//   i=2 'd': 'e' leaves, 'd' enters -> window={i:1,d:1} - doesn't match
//   i=3 'b': 'i' leaves, 'b' enters -> window={d:1,b:1} - doesn't match
//   i=4 'a': 'd' leaves, 'a' enters -> window={b:1,a:1} - matches need! -> true
//
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
    if (i >= s1.length - 1 && countsMatch(need, window)) return true;
  }

  return false;
}

function countsMatch(need, window) {
  for (let i = 0; i < need.length; i++) {
    if (need[i] !== window[i]) return false;
  }
  return true;
}

// Simpler (less efficient) alternative: sort s1 once, then for every window
// of the same length in s2, sort that window too and compare directly - a
// permutation is just a rearrangement, so two strings with the same sorted
// form are permutations of each other. No frequency arrays or sliding-window
// bookkeeping needed, just a direct check at every position.
//
// Time: O(n * m log m), Space: O(m)
function checkInclusionSimple(s1, s2) {
  const sortedS1 = s1.split('').sort().join('');

  for (let i = 0; i + s1.length <= s2.length; i++) {
    const window = s2.slice(i, i + s1.length);
    const sortedWindow = window.split('').sort().join('');
    if (sortedWindow === sortedS1) return true;
  }

  return false;
}

console.log(checkInclusion('ab', 'eidbaooo')); // true
console.log(checkInclusion('ab', 'eidboaoo')); // false
console.log(checkInclusionSimple('ab', 'eidbaooo')); // true
console.log(checkInclusionSimple('ab', 'eidboaoo')); // false

module.exports = checkInclusion;

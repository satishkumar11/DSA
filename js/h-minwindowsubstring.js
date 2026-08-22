// Minimum Window Substring
// Find the smallest substring of s that contains every character of t.
// Time: O(n + m), Space: O(charset)
function minWindow(s, t) {
  if (!s || !t) return '';

  const need = new Map();
  for (const c of t) need.set(c, (need.get(c) || 0) + 1);

  const required = need.size;
  let formed = 0;
  const windowCounts = new Map();
  let l = 0;
  let resLen = Infinity;
  let resStart = 0;

  for (let r = 0; r < s.length; r++) {
    const c = s[r];
    windowCounts.set(c, (windowCounts.get(c) || 0) + 1);
    if (need.has(c) && windowCounts.get(c) === need.get(c)) formed++;

    while (formed === required) {
      if (r - l + 1 < resLen) {
        resLen = r - l + 1;
        resStart = l;
      }
      const lc = s[l];
      windowCounts.set(lc, windowCounts.get(lc) - 1);
      if (need.has(lc) && windowCounts.get(lc) < need.get(lc)) formed--;
      l++;
    }
  }

  return resLen === Infinity ? '' : s.substring(resStart, resStart + resLen);
}

console.log(minWindow('ADOBECODEBANC', 'ABC')); // "BANC"

module.exports = minWindow;

// Minimum Window Substring
// Find the smallest substring of s that contains every character of t.
//
// Input: s = "ADOBECODEBANC", t = "ABC"
// Output: "BANC"
//
// Sliding window with a need/have character count; expand the right edge
// until valid, then shrink the left edge to find the smallest valid window.
//
// Trace with s = "ADOBECODEBANC", t = "ABC" (need: A=1,B=1,C=1):
//   expand r=0..5 ("ADOBEC"): once C is added, formed=3=required -> shrink
//     from the left: only 'A' at l=0 is essential, so shrinking stops right
//     after removing it -> first valid window "ADOBEC" (len 6), best so far
//   expand r=6..9 ("ODEB"): rebuilds toward a valid window but doesn't
//     re-trigger formed=3 until another 'A' arrives
//   expand r=10 ('A'): formed=3 again -> shrink from l=1: removes D, O, B,
//     E (none essential) then hits 'C' -> formed drops to 2, shrink stops;
//     no length improvement found this round (window was already too big)
//   expand r=11..12 ("NC"): formed=3 again once C arrives -> shrink from
//     l=6: removes O, D, E (not essential), then B - window "BANC" (len 4)
//     beats the previous best (6), then removing B itself breaks formed
//   final smallest window: "BANC"
//
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

// Simpler version: try every possible start, and for each one, just keep
// extending right until the window is valid - stop at the first valid
// window for that start (extending further only makes it bigger). No need
// to reason about when to shrink.
//
// Time: O(n^2), Space: O(charset)
function minWindowSimple(s, t) {
  if (!s || !t) return '';

  const need = new Map();
  for (const c of t) need.set(c, (need.get(c) || 0) + 1);
  const required = need.size;

  let best = '';

  for (let start = 0; start < s.length; start++) {
    const window = new Map();
    let formed = 0;

    for (let end = start; end < s.length; end++) {
      const c = s[end];
      window.set(c, (window.get(c) || 0) + 1);
      if (need.has(c) && window.get(c) === need.get(c)) formed++;

      if (formed === required) {
        const candidate = s.substring(start, end + 1);
        if (best === '' || candidate.length < best.length) best = candidate;
        break; // shortest window starting here - no point extending further
      }
    }
  }

  return best;
}

console.log(minWindow('ADOBECODEBANC', 'ABC')); // "BANC"
console.log(minWindowSimple('ADOBECODEBANC', 'ABC')); // "BANC"

module.exports = minWindow;

// Longest Repeating Character Replacement
// Find the longest substring achievable by replacing at most k characters with the same character.
// Time: O(n), Space: O(1) (fixed 26-letter alphabet)
function characterReplacement(s, k) {
  const counts = new Array(26).fill(0);
  const base = 'A'.charCodeAt(0);
  let maxCount = 0;
  let left = 0;
  let result = 0;

  for (let right = 0; right < s.length; right++) {
    const idx = s.charCodeAt(right) - base;
    counts[idx]++;
    maxCount = Math.max(maxCount, counts[idx]);

    while (right - left + 1 - maxCount > k) {
      counts[s.charCodeAt(left) - base]--;
      left++;
    }

    result = Math.max(result, right - left + 1);
  }

  return result;
}

console.log(characterReplacement('ABAB', 2)); // 4
console.log(characterReplacement('AABABBA', 1)); // 4

module.exports = characterReplacement;

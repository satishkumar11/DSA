// Roman to Integer
// Convert a Roman numeral string to its integer value.
// Sum symbol values left to right, but subtract a symbol whose value is
// less than the symbol immediately after it (subtractive notation).
// Time: O(n), Space: O(1)
function romanToInt(s) {
  const map = { I: 1, V: 5, X: 10, L: 50, C: 100, D: 500, M: 1000 };
  let total = 0;

  for (let i = 0; i < s.length; i++) {
    const cur = map[s[i]];
    const next = map[s[i + 1]];
    if (next && cur < next) total -= cur;
    else total += cur;
  }

  return total;
}

console.log(romanToInt('MCMXCIV')); // 1994

module.exports = romanToInt;

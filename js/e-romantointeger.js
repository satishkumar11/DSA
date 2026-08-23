// Roman to Integer
// Convert a Roman numeral string to its integer value.
//
// Input: s = "MCMXCIV"
// Output: 1994
//
// Sum symbol values left to right, but subtract a symbol whose value is
// less than the symbol immediately after it (subtractive notation).
//
// Trace with s = "MCMXCIV":
//   M: next=C(100) is not > M(1000)     -> add 1000    -> total=1000
//   C: next=M(1000) > C(100)            -> subtract 100 -> total=900
//   M: next=X(10) is not > M(1000)      -> add 1000    -> total=1900
//   X: next=C(100) > X(10)              -> subtract 10  -> total=1890
//   C: next=I(1) is not > C(100)        -> add 100     -> total=1990
//   I: next=V(5) > I(1)                 -> subtract 1   -> total=1989
//   V: no next                          -> add 5       -> total=1994
//
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

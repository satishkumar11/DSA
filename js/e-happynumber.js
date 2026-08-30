// Happy Number
// Repeatedly replace a number with the sum of the squares of its digits;
// determine whether this process eventually reaches 1.
//
// Input: n = 19
// Output: true
//
// Unhappy numbers fall into a repeating cycle instead of reaching 1, so
// track every value seen - if one repeats before hitting 1, it can never
// reach 1 (the same value always produces the same next value).
//
// Trace with n = 19:
//   19 -> 1^2+9^2 = 1+81 = 82
//   82 -> 8^2+2^2 = 64+4 = 68
//   68 -> 6^2+8^2 = 36+64 = 100
//   100 -> 1^2+0^2+0^2 = 1
//   n == 1 -> true
//
// Time: O(log n) per step, Space: O(log n) for the seen set
function isHappy(n) {
  const seen = new Set();

  while (n !== 1 && !seen.has(n)) {
    seen.add(n);
    n = sumOfSquaredDigits(n);
  }

  return n === 1;
}

function sumOfSquaredDigits(n) {
  let sum = 0;
  while (n > 0) {
    const digit = n % 10;
    sum += digit * digit;
    n = Math.floor(n / 10);
  }
  return sum;
}

console.log(isHappy(19)); // true

module.exports = isHappy;

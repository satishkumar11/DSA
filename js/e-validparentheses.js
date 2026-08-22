// Valid Parentheses
// Determine if a string of brackets is validly matched and nested.
// Push opening brackets onto a stack; on a closing bracket, pop and check
// it matches the expected opener, failing fast on any mismatch.
// Time: O(n), Space: O(n)
function isValid(s) {
  const stack = [];
  const pairs = { ')': '(', ']': '[', '}': '{' };

  for (const c of s) {
    if (c === '(' || c === '[' || c === '{') {
      stack.push(c);
    } else if (stack.pop() !== pairs[c]) {
      return false;
    }
  }

  return stack.length === 0;
}

console.log(isValid('()[]{}')); // true
console.log(isValid('(]')); // false

module.exports = isValid;

// hellointerview: https://www.hellointerview.com/learn/code/stack/valid-parentheses
// Valid Parentheses
// Determine if a string of brackets is validly matched and nested.
//
// Input: s = "()[]{}"
// Output: true
//
// Push opening brackets onto a stack; on a closing bracket, pop and check
// it matches the expected opener, failing fast on any mismatch.
//
// Trace with s = "()[]{}":
//   '(' -> push -> stack=[(]
//   ')' -> pop '(' , matches pairs[')'] -> stack=[]
//   '[' -> push -> stack=[[]
//   ']' -> pop '[' , matches pairs[']'] -> stack=[]
//   '{' -> push -> stack=[{]
//   '}' -> pop '{' , matches pairs['}'] -> stack=[]
//   stack empty at the end -> true
//
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

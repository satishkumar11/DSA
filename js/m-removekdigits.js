// Remove K Digits
// Remove k digits from a number string to make it as small as possible.
// Time: O(n), Space: O(n)
function removeKdigits(num, k) {
  const stack = [];

  for (const c of num) {
    while (k > 0 && stack.length && stack[stack.length - 1] > c) {
      stack.pop();
      k--;
    }
    stack.push(c);
  }

  while (k > 0) {
    stack.pop();
    k--;
  }

  while (stack.length > 1 && stack[0] === '0') stack.shift();

  const result = stack.join('');
  return result.length ? result : '0';
}

console.log(removeKdigits('1432219', 3)); // "1219"

module.exports = removeKdigits;

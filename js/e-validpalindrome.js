// Valid Palindrome
// Determine if a string is a palindrome, ignoring non-alphanumeric characters and case.
//
// Input: s = "A man, a plan, a canal: Panama"
// Output: true
//
// Two pointers from both ends, skipping non-alphanumeric characters and
// comparing letters case-insensitively until they meet in the middle.
//
// Trace with s = "A man, a plan, a canal: Panama" (spaces/punctuation skipped):
//   l='A', r='a' -> equal case-insensitively -> l++, r--
//   l='m', r='m' -> equal -> continue
//   ... pointers keep meeting equal letters (a/a, n/n, ...) all the way in
//   pointers cross in the middle having never found a mismatch -> true
//
// Time: O(n), Space: O(1)
function isPalindrome(s) {
  let l = 0;
  let r = s.length - 1;

  const isAlnum = (c) => /[a-z0-9]/i.test(c);

  while (l < r) {
    while (l < r && !isAlnum(s[l])) l++;
    while (l < r && !isAlnum(s[r])) r--;
    if (s[l].toLowerCase() !== s[r].toLowerCase()) return false;
    l++;
    r--;
  }

  return true;
}

console.log(isPalindrome('A man, a plan, a canal: Panama')); // true
console.log(isPalindrome('race a car')); // false

module.exports = isPalindrome;

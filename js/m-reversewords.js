// Reverse Words in a String
// Reverse the order of words in a sentence, collapsing extra whitespace.
// Trim and split the string on whitespace, reverse the resulting word
// list, and join with single spaces.
// Time: O(n), Space: O(n)
function reverseWords(s) {
  return s.trim().split(/\s+/).reverse().join(' ');
}

console.log(reverseWords('  the sky is blue  ')); // "blue is sky the"

module.exports = reverseWords;

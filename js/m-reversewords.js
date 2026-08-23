// Reverse Words in a String
// Reverse the order of words in a sentence, collapsing extra whitespace.
//
// Input: s = "  the sky is blue  "
// Output: "blue is sky the"
//
// Trim and split the string on whitespace, reverse the resulting word
// list, and join with single spaces.
//
// Trace with s = "  the sky is blue  ":
//   trim() -> "the sky is blue"
//   split(/\s+/) -> ["the", "sky", "is", "blue"]
//   reverse() -> ["blue", "is", "sky", "the"]
//   join(' ') -> "blue is sky the"
//
// Time: O(n), Space: O(n)
function reverseWords(s) {
  return s.trim().split(/\s+/).reverse().join(' ');
}

console.log(reverseWords('  the sky is blue  ')); // "blue is sky the"

module.exports = reverseWords;

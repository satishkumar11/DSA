// Longest Common Prefix
// Find the longest prefix shared by all strings in an array.
// Start with the first string as the candidate prefix, then repeatedly
// trim it until every other string starts with it.
// Time: O(n * m), Space: O(1)
function longestCommonPrefix(strs) {
  if (!strs.length) return '';
  let prefix = strs[0];

  for (let i = 1; i < strs.length; i++) {
    while (strs[i].indexOf(prefix) !== 0) {
      prefix = prefix.substring(0, prefix.length - 1);
      if (!prefix) return '';
    }
  }

  return prefix;
}

console.log(longestCommonPrefix(['flower', 'flow', 'flight'])); // "fl"

module.exports = longestCommonPrefix;

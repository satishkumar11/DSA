// First Non-Repeating Character
// Given a string, return the index of the first character that does not repeat.
// Return -1 if every character repeats.
//
// Input: s = "leetcode"
// Output: 0 ('l' never repeats and appears first)
//
// Count the frequency of every character in one pass using a hash map, then
// scan the string a second time and return the index of the first character
// whose count is exactly 1.
//
// Trace with s = "loveleetcode":
//   counts after pass 1 -> l:2, o:2, v:1, e:4, t:1, c:1, d:1
//   pass 2: l(2) skip, o(2) skip, v(1) -> return index 2
//
// Time: O(n), Space: O(k) where k is the number of distinct characters
function firstUniqChar(s) {
  const counts = new Map();

  for (const ch of s) {
    counts.set(ch, (counts.get(ch) || 0) + 1);
  }

  for (let i = 0; i < s.length; i++) {
    if (counts.get(s[i]) === 1) return i;
  }

  return -1;
}

// Alternate approach using indexOf/lastIndexOf instead of a hash map: a
// character is non-repeating exactly when its first occurrence and its last
// occurrence are the same index. Scan left to right and return the first
// index where that holds.
//
// Trace with s = "loveleetcode":
//   i=0 'l': indexOf=0, lastIndexOf=4 -> repeats, skip
//   i=1 'o': indexOf=1, lastIndexOf=7 -> repeats, skip
//   i=2 'v': indexOf=2, lastIndexOf=2 -> match -> return 2
//
// Time: O(n^2) (indexOf/lastIndexOf each scan the string), Space: O(1)
function firstUniqCharIndexOf(s) {
  for (let i = 0; i < s.length; i++) {
    if (s.indexOf(s[i]) === s.lastIndexOf(s[i])) return i;
  }
  return -1;
}

console.log(firstUniqChar('leetcode')); // 0
console.log(firstUniqChar('loveleetcode')); // 2
console.log(firstUniqChar('aabb')); // -1

console.log(firstUniqCharIndexOf('leetcode')); // 0
console.log(firstUniqCharIndexOf('loveleetcode')); // 2
console.log(firstUniqCharIndexOf('aabb')); // -1

module.exports = firstUniqChar;

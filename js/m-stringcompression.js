// String Compression
// Compress consecutive repeated characters in place using counts.
// Two pointers: a read pointer counts runs of identical characters while
// a write pointer overwrites the array in place with the char and its count.
// Time: O(n), Space: O(1)
function compress(chars) {
  let write = 0;
  let read = 0;

  while (read < chars.length) {
    const char = chars[read];
    let count = 0;
    while (read < chars.length && chars[read] === char) {
      read++;
      count++;
    }
    chars[write++] = char;
    if (count > 1) {
      for (const digit of String(count)) chars[write++] = digit;
    }
  }

  return write;
}

const chars = ['a', 'a', 'b', 'b', 'c', 'c', 'c'];
console.log(compress(chars), chars.slice(0, 6)); // 6 ['a','2','b','2','c','3']

module.exports = compress;

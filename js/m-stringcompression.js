// String Compression
// Compress consecutive repeated characters in place using counts.
//
// Input: chars = ["a","a","b","b","c","c","c"]
// Output: 6, ["a","2","b","2","c","3"]
//
// Two pointers: a read pointer counts runs of identical characters while
// a write pointer overwrites the array in place with the char and its count.
//
// Trace with chars = [a,a,b,b,c,c,c]:
//   run 'a': read passes both a's (count=2) -> write 'a' then '2' -> write=2
//   run 'b': read passes both b's (count=2) -> write 'b' then '2' -> write=4
//   run 'c': read passes all three c's (count=3) -> write 'c' then '3' -> write=6
//   read reaches the end -> return write=6, chars[0..5] = [a,2,b,2,c,3]
//
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
    // a run of 1 is written with no trailing number at all
    if (count > 1) {
      // count may be multi-digit (e.g. 12) - String(count) turns it into
      // "12" so each digit character gets its own array slot, one per write
      for (const digit of String(count)) chars[write++] = digit;
    }
  }

  return write;
}

const chars = ['a', 'a', 'b', 'b', 'c', 'c', 'c'];
console.log(compress(chars), chars.slice(0, 6)); // 6 ['a','2','b','2','c','3']

module.exports = compress;

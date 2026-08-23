import java.util.Arrays;

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
class StringCompression {
    public static int compress(char[] chars) {
        int write = 0, read = 0;

        while (read < chars.length) {
            char c = chars[read];
            int count = 0;
            while (read < chars.length && chars[read] == c) {
                read++;
                count++;
            }
            chars[write++] = c;
            // a run of 1 is written with no trailing number at all
            if (count > 1) {
                // count may be multi-digit (e.g. 12) - String.valueOf(count)
                // turns it into "12" so each digit character gets its own
                // array slot, one per write
                for (char digit : String.valueOf(count).toCharArray()) chars[write++] = digit;
            }
        }

        return write;
    }

    public static void main(String[] args) {
        char[] chars = {'a', 'a', 'b', 'b', 'c', 'c', 'c'};
        int len = compress(chars);
        System.out.println(len + " " + Arrays.toString(Arrays.copyOf(chars, len))); // 6 [a, 2, b, 2, c, 3]
    }
}

import java.util.Arrays;

// String Compression
// Compress consecutive repeated characters in place using counts.
// Two pointers: a read pointer counts runs of identical characters while
// a write pointer overwrites the array in place with the char and its count.
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
            if (count > 1) {
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

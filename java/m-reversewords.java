// Reverse Words in a String
// Reverse the order of words in a sentence, collapsing extra whitespace.
// Trim and split the string on whitespace, reverse the resulting word
// list, and join with single spaces.
// Time: O(n), Space: O(n)
class ReverseWords {
    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(' ');
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("  the sky is blue  ")); // "blue is sky the"
    }
}

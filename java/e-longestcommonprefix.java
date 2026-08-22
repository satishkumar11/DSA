// Longest Common Prefix
// Find the longest prefix shared by all strings in an array.
//
// Input: strs = ["flower", "flow", "flight"]
// Output: "fl"
//
// Start with the first string as the candidate prefix, then repeatedly
// trim it until every other string starts with it.
//
// Time: O(n * m), Space: O(1)
//
// Dry run with strs = ["flower", "flow", "flight"]:
//   prefix starts as "flower"
//   i=1 ("flow"):   "flower" doesn't fit -> trim to "flowe" -> "flow" (fits)   prefix = "flow"
//   i=2 ("flight"): "flow" doesn't fit -> trim to "flo" -> "fl" (fits)         prefix = "fl"
//   loop ends -> return "fl"
class LongestCommonPrefix {
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] {"flower", "flow", "flight"})); // "fl"
        System.out.println(longestCommonPrefix(new String[] {"dog", "racecar", "car"})); // "" (no common prefix)
    }
}

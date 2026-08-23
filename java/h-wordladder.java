import java.util.*;

// Word Ladder
// Find the shortest transformation sequence length from one word to another, changing one letter at a time.
//
// Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
// Output: 5
//
// BFS over words, generating every one-letter variation of the current
// word at each step; BFS guarantees the shortest transformation path.
//
// hit -> hot -> dot -> dog -> cog   (5 words = shortest ladder)
//
// BFS trace (only in-dictionary, unvisited variants get queued):
//   (hit, 1): only "hot" is a valid variant -> queue (hot, 2)
//   (hot, 2): valid variants "dot", "lot" -> queue (dot, 3), (lot, 3)
//   (dot, 3): valid variant "dog" -> queue (dog, 4)
//   (lot, 3): valid variant "log" -> queue (log, 4)
//   (dog, 4): valid variant "cog" -> queue (cog, 5)
//   (log, 4): "cog" already visited -> nothing new queued
//   (cog, 5): word equals endWord -> return 5
//
// Time: O(n * 26 * L), Space: O(n)
class WordLadder {
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return steps;

                char[] chars = word.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char original = chars[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[j] = c;
                        String next = new String(chars);
                        if (wordSet.contains(next) && !visited.contains(next)) {
                            visited.add(next);
                            queue.offer(next);
                        }
                    }
                    chars[j] = original;
                }
            }
            steps++;
        }

        return 0;
    }

    // Simpler version: instead of generating all 26*L letter variations of
    // the current word, just compare it directly against every remaining
    // word in the dictionary and check if they differ by exactly one letter.
    //
    // Time: O(n^2 * L), Space: O(n)
    public static int ladderLengthSimple(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        Set<String> remaining = new HashSet<>(wordList);
        List<String> queue = new ArrayList<>();
        queue.add(beginWord);
        int steps = 1;

        while (!queue.isEmpty()) {
            List<String> next = new ArrayList<>();

            for (String word : queue) {
                if (word.equals(endWord)) return steps;

                for (String candidate : remaining) {
                    if (isOneLetterAway(word, candidate)) next.add(candidate);
                }
            }
            remaining.removeAll(next);

            queue = next;
            steps++;
        }

        return 0;
    }

    private static boolean isOneLetterAway(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }

    public static void main(String[] args) {
        System.out.println(ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))); // 5
        System.out.println(ladderLengthSimple("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"))); // 5
    }
}

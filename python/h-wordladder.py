# Word Ladder
# Find the shortest transformation sequence length from one word to another, changing one letter at a time.
#
# Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
# Output: 5
#
# BFS over words, generating every one-letter variation of the current
# word at each step; BFS guarantees the shortest transformation path.
#
# hit -> hot -> dot -> dog -> cog   (5 words = shortest ladder)
#
# BFS trace (only in-dictionary, unvisited variants get queued):
#   (hit, 1): only "hot" is a valid variant -> queue (hot, 2)
#   (hot, 2): valid variants "dot", "lot" -> queue (dot, 3), (lot, 3)
#   (dot, 3): valid variant "dog" -> queue (dog, 4)
#   (lot, 3): valid variant "log" -> queue (log, 4)
#   (dog, 4): valid variant "cog" -> queue (cog, 5)
#   (log, 4): "cog" already visited -> nothing new queued
#   (cog, 5): word == endWord -> return 5
#
# Time: O(n * 26 * L), Space: O(n)
from collections import deque


def ladder_length(begin_word, end_word, word_list):
    word_set = set(word_list)
    if end_word not in word_set:
        return 0

    queue = deque([(begin_word, 1)])
    visited = {begin_word}

    while queue:
        word, steps = queue.popleft()
        if word == end_word:
            return steps

        for i in range(len(word)):
            for c in range(97, 123):
                next_word = word[:i] + chr(c) + word[i + 1:]
                if next_word in word_set and next_word not in visited:
                    visited.add(next_word)
                    queue.append((next_word, steps + 1))

    return 0


# Simpler version: instead of generating all 26*L letter variations of the
# current word, just compare it directly against every remaining word in
# the dictionary and check if they differ by exactly one letter.
#
# Time: O(n^2 * L), Space: O(n)
def ladder_length_simple(begin_word, end_word, word_list):
    if end_word not in word_list:
        return 0

    remaining = set(word_list)
    queue = [begin_word]
    steps = 1

    while queue:
        next_queue = []

        for word in queue:
            if word == end_word:
                return steps

            for candidate in remaining:
                if is_one_letter_away(word, candidate):
                    next_queue.append(candidate)
            for candidate in next_queue:
                remaining.discard(candidate)

        queue = next_queue
        steps += 1

    return 0


def is_one_letter_away(a, b):
    diff = 0
    for i in range(len(a)):
        if a[i] != b[i]:
            diff += 1
        if diff > 1:
            return False
    return diff == 1


print(ladder_length('hit', 'cog', ['hot', 'dot', 'dog', 'lot', 'log', 'cog']))  # 5
print(ladder_length_simple('hit', 'cog', ['hot', 'dot', 'dog', 'lot', 'log', 'cog']))  # 5

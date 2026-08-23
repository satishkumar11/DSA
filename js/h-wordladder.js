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
//   (cog, 5): word === endWord -> return 5
//
// Time: O(n * 26 * L), Space: O(n)
function ladderLength(beginWord, endWord, wordList) {
  const wordSet = new Set(wordList);
  if (!wordSet.has(endWord)) return 0;

  let queue = [[beginWord, 1]];
  const visited = new Set([beginWord]);

  while (queue.length) {
    const [word, steps] = queue.shift();
    if (word === endWord) return steps;

    for (let i = 0; i < word.length; i++) {
      for (let c = 97; c <= 122; c++) {
        const next = word.slice(0, i) + String.fromCharCode(c) + word.slice(i + 1);
        if (wordSet.has(next) && !visited.has(next)) {
          visited.add(next);
          queue.push([next, steps + 1]);
        }
      }
    }
  }

  return 0;
}

// Simpler version: instead of generating all 26*L letter variations of the
// current word, just compare it directly against every remaining word in
// the dictionary and check if they differ by exactly one letter.
//
// Time: O(n^2 * L), Space: O(n)
function ladderLengthSimple(beginWord, endWord, wordList) {
  if (!wordList.includes(endWord)) return 0;

  const remaining = new Set(wordList);
  let queue = [beginWord];
  let steps = 1;

  while (queue.length) {
    const next = [];

    for (const word of queue) {
      if (word === endWord) return steps;

      for (const candidate of remaining) {
        if (isOneLetterAway(word, candidate)) next.push(candidate);
      }
      for (const candidate of next) remaining.delete(candidate);
    }

    queue = next;
    steps++;
  }

  return 0;
}

function isOneLetterAway(a, b) {
  let diff = 0;
  for (let i = 0; i < a.length; i++) {
    if (a[i] !== b[i]) diff++;
    if (diff > 1) return false;
  }
  return diff === 1;
}

console.log(ladderLength('hit', 'cog', ['hot', 'dot', 'dog', 'lot', 'log', 'cog'])); // 5
console.log(ladderLengthSimple('hit', 'cog', ['hot', 'dot', 'dog', 'lot', 'log', 'cog'])); // 5

module.exports = ladderLength;

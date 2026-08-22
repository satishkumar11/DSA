// Word Ladder
// Find the shortest transformation sequence length from one word to another, changing one letter at a time.
//
// BFS over words, generating every one-letter variation of the current
// word at each step; BFS guarantees the shortest transformation path.
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

console.log(ladderLength('hit', 'cog', ['hot', 'dot', 'dog', 'lot', 'log', 'cog'])); // 5

module.exports = ladderLength;

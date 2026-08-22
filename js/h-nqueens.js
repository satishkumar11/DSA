// N-Queens
// Place n queens on an n x n chessboard so that no two attack each other.
// Backtracking placing one queen per row; track occupied columns and
// diagonals with sets so each placement can be checked in O(1).
// Time: O(n!), Space: O(n^2)
function solveNQueens(n) {
  const results = [];
  const cols = new Set();
  const diag1 = new Set();
  const diag2 = new Set();
  const board = Array.from({ length: n }, () => new Array(n).fill('.'));

  function backtrack(row) {
    if (row === n) {
      results.push(board.map((r) => r.join('')));
      return;
    }

    for (let col = 0; col < n; col++) {
      if (cols.has(col) || diag1.has(row - col) || diag2.has(row + col)) continue;

      cols.add(col);
      diag1.add(row - col);
      diag2.add(row + col);
      board[row][col] = 'Q';

      backtrack(row + 1);

      board[row][col] = '.';
      cols.delete(col);
      diag1.delete(row - col);
      diag2.delete(row + col);
    }
  }

  backtrack(0);
  return results;
}

console.log(solveNQueens(4).length); // 2

module.exports = solveNQueens;

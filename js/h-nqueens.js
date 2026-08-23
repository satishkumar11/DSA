// hellointerview: https://www.hellointerview.com/learn/code/backtracking/n-queens
// N-Queens
// Place n queens on an n x n chessboard so that no two attack each other.
//
// Input: n = 4
// Output: 2 distinct solutions
//
// Backtracking placing one queen per row; track occupied columns and
// diagonals with sets so each placement can be checked in O(1).
//
// One valid solution for n = 4:
// . Q . .
// . . . Q
// Q . . .
// . . Q .
//
// Trace intuition for n=4: row 0's queen at column 0 forces row 1's queen
// away from column 0 (same column) and column 1 (diagonal) - only columns
// 2 and 3 are legal. Placing row 1 at column 2 then blocks row 2 out of
// every column (all attacked), so that whole branch dies and backtracks.
// Working through all placements this way, only two full boards survive
// with no shared column or diagonal - one of them is:
//   . Q . .
//   . . . Q
//   Q . . .
//   . . Q .
//
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

// Simpler version: instead of tracking occupied columns/diagonals in sets
// (the row-col / row+col trick), just remember which column each earlier
// row's queen used, and directly scan those earlier rows to check for a
// conflict. More work per placement, but the safety check itself is a plain
// "look at what's already on the board" loop instead of a diagonal-math trick.
//
// Time: O(n! * n), Space: O(n)
function solveNQueensSimple(n) {
  const results = [];
  const placedCols = new Array(n).fill(-1); // placedCols[row] = column used in that row

  function isSafe(row, col) {
    for (let r = 0; r < row; r++) {
      const c = placedCols[r];
      if (c === col) return false; // same column
      if (Math.abs(c - col) === row - r) return false; // same diagonal
    }
    return true;
  }

  function backtrack(row) {
    if (row === n) {
      const board = placedCols.map((col) => {
        let rowStr = '';
        for (let c = 0; c < n; c++) rowStr += c === col ? 'Q' : '.';
        return rowStr;
      });
      results.push(board);
      return;
    }

    for (let col = 0; col < n; col++) {
      if (isSafe(row, col)) {
        placedCols[row] = col;
        backtrack(row + 1);
        placedCols[row] = -1;
      }
    }
  }

  backtrack(0);
  return results;
}

console.log(solveNQueens(4).length); // 2
console.log(solveNQueensSimple(4).length); // 2

module.exports = solveNQueens;

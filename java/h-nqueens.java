// hellointerview: https://www.hellointerview.com/learn/code/backtracking/n-queens
import java.util.ArrayList;
import java.util.List;

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
class NQueens {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> results = new ArrayList<>();
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n];
        boolean[] diag2 = new boolean[2 * n];
        char[][] board = new char[n][n];
        for (char[] row : board) java.util.Arrays.fill(row, '.');

        backtrack(0, n, cols, diag1, diag2, board, results);
        return results;
    }

    private static void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2,
                                   char[][] board, List<List<String>> results) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (char[] r : board) solution.add(new String(r));
            results.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;
            if (cols[col] || diag1[d1] || diag2[d2]) continue;

            cols[col] = diag1[d1] = diag2[d2] = true;
            board[row][col] = 'Q';

            backtrack(row + 1, n, cols, diag1, diag2, board, results);

            board[row][col] = '.';
            cols[col] = diag1[d1] = diag2[d2] = false;
        }
    }

    // Simpler version: instead of tracking occupied columns/diagonals in
    // boolean arrays (the row-col / row+col trick), just remember which
    // column each earlier row's queen used, and directly scan those earlier
    // rows to check for a conflict. More work per placement, but the safety
    // check itself is a plain "look at what's already on the board" loop
    // instead of a diagonal-math trick.
    //
    // Time: O(n! * n), Space: O(n)
    public static List<List<String>> solveNQueensSimple(int n) {
        List<List<String>> results = new ArrayList<>();
        int[] placedCols = new int[n];
        java.util.Arrays.fill(placedCols, -1);

        backtrackSimple(0, n, placedCols, results);
        return results;
    }

    private static void backtrackSimple(int row, int n, int[] placedCols, List<List<String>> results) {
        if (row == n) {
            List<String> solution = new ArrayList<>();
            for (int col : placedCols) {
                StringBuilder rowStr = new StringBuilder();
                for (int c = 0; c < n; c++) rowStr.append(c == col ? 'Q' : '.');
                solution.add(rowStr.toString());
            }
            results.add(solution);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(row, col, placedCols)) {
                placedCols[row] = col;
                backtrackSimple(row + 1, n, placedCols, results);
                placedCols[row] = -1;
            }
        }
    }

    private static boolean isSafe(int row, int col, int[] placedCols) {
        for (int r = 0; r < row; r++) {
            int c = placedCols[r];
            if (c == col) return false; // same column
            if (Math.abs(c - col) == row - r) return false; // same diagonal
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(4).size()); // 2
        System.out.println(solveNQueensSimple(4).size()); // 2
    }
}

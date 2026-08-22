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

    public static void main(String[] args) {
        System.out.println(solveNQueens(4).size()); // 2
    }
}

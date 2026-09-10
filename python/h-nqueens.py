# hellointerview: https://www.hellointerview.com/learn/code/backtracking/n-queens
# N-Queens
# Place n queens on an n x n chessboard so that no two attack each other.
#
# Input: n = 4
# Output: 2 distinct solutions
#
# Backtracking placing one queen per row; track occupied columns and
# diagonals with sets so each placement can be checked in O(1).
#
# One valid solution for n = 4:
# . Q . .
# . . . Q
# Q . . .
# . . Q .
#
# Trace intuition for n=4: row 0's queen at column 0 forces row 1's queen
# away from column 0 (same column) and column 1 (diagonal) - only columns
# 2 and 3 are legal. Placing row 1 at column 2 then blocks row 2 out of
# every column (all attacked), so that whole branch dies and backtracks.
# Working through all placements this way, only two full boards survive
# with no shared column or diagonal - one of them is:
#   . Q . .
#   . . . Q
#   Q . . .
#   . . Q .
#
# Time: O(n!), Space: O(n^2)
def solve_n_queens(n):
    results = []
    cols = set()
    diag1 = set()
    diag2 = set()
    board = []
    for i in range(n):
        board.append(['.'] * n)

    def backtrack(row):
        if row == n:
            solution = []
            for r in board:
                solution.append(''.join(r))
            results.append(solution)
            return

        for col in range(n):
            if col in cols or (row - col) in diag1 or (row + col) in diag2:
                continue

            cols.add(col)
            diag1.add(row - col)
            diag2.add(row + col)
            board[row][col] = 'Q'

            backtrack(row + 1)

            board[row][col] = '.'
            cols.discard(col)
            diag1.discard(row - col)
            diag2.discard(row + col)

    backtrack(0)
    return results


# Simpler version: instead of tracking occupied columns/diagonals in sets
# (the row-col / row+col trick), just remember which column each earlier
# row's queen used, and directly scan those earlier rows to check for a
# conflict. More work per placement, but the safety check itself is a plain
# "look at what's already on the board" loop instead of a diagonal-math trick.
#
# Time: O(n! * n), Space: O(n)
def solve_n_queens_simple(n):
    results = []
    placed_cols = [-1] * n  # placed_cols[row] = column used in that row

    def is_safe(row, col):
        for r in range(row):
            c = placed_cols[r]
            if c == col:
                return False  # same column
            if abs(c - col) == row - r:
                return False  # same diagonal
        return True

    def backtrack(row):
        if row == n:
            board = []
            for col in placed_cols:
                row_str = ''
                for c in range(n):
                    if c == col:
                        row_str += 'Q'
                    else:
                        row_str += '.'
                board.append(row_str)
            results.append(board)
            return

        for col in range(n):
            if is_safe(row, col):
                placed_cols[row] = col
                backtrack(row + 1)
                placed_cols[row] = -1

    backtrack(0)
    return results


print(len(solve_n_queens(4)))  # 2
print(len(solve_n_queens_simple(4)))  # 2

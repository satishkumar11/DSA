// hellointerview: https://www.hellointerview.com/learn/code/depth-first-search/flood-fill
import java.util.Arrays;

// Flood Fill
// Recolor a connected region of a 2D image starting from a given pixel.
//
// Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
// Output: [[2,2,2],[2,2,0],[1,0,1]]
//
// DFS from the starting pixel, recoloring every connected pixel that
// matches the original color before the fill began.
//
// Time: O(rows * cols), Space: O(rows * cols)
class FloodFill {
    public static int[][] floodFill(int[][] image, int startRow, int startCol, int newColor) {
        int numRows = image.length;
        int numCols = image[0].length;
        int originalColor = image[startRow][startCol];
        if (originalColor == newColor) return image;
        dfs(image, startRow, startCol, originalColor, newColor, numRows, numCols);
        return image;
    }

    private static void dfs(int[][] image, int row, int col, int originalColor, int newColor, int numRows, int numCols) {
        if (image[row][col] == originalColor) {
            image[row][col] = newColor;
            if (row >= 1) dfs(image, row - 1, col, originalColor, newColor, numRows, numCols);
            if (row + 1 < numRows) dfs(image, row + 1, col, originalColor, newColor, numRows, numCols);
            if (col >= 1) dfs(image, row, col - 1, originalColor, newColor, numRows, numCols);
            if (col + 1 < numCols) dfs(image, row, col + 1, originalColor, newColor, numRows, numCols);
        }
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        for (int[] r : floodFill(image, 1, 1, 2)) System.out.println(Arrays.toString(r));
        // [2, 2, 2] [2, 2, 0] [2, 0, 1]
    }
}

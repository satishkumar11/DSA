import java.util.Arrays;

// Flood Fill
// Recolor a connected region of a 2D image starting from a given pixel.
// Time: O(rows * cols), Space: O(rows * cols)
class FloodFill {
    public static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int startColor = image[sr][sc];
        if (startColor != color) dfs(image, sr, sc, startColor, color);
        return image;
    }

    private static void dfs(int[][] image, int r, int c, int startColor, int color) {
        if (r < 0 || c < 0 || r >= image.length || c >= image[0].length || image[r][c] != startColor) return;
        image[r][c] = color;
        dfs(image, r + 1, c, startColor, color);
        dfs(image, r - 1, c, startColor, color);
        dfs(image, r, c + 1, startColor, color);
        dfs(image, r, c - 1, startColor, color);
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        for (int[] row : floodFill(image, 1, 1, 2)) System.out.println(Arrays.toString(row));
        // [2, 2, 2] [2, 2, 0] [2, 0, 1]
    }
}

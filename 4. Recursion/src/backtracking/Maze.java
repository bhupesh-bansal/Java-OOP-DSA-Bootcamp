// Print number of possible paths from the top-left corner to the bottom-right corner of an m × n matrix,
// where at each step you can move only right or down.
package backtracking;

public class Maze {
    static void main() {
        System.out.println(maze(3,3));
    }

    static int maze(int rows, int cols) {
        if(rows == 1 || cols == 1) {
            return 1;
        }

        int left = maze(rows-1, cols);
        int right = maze(rows, cols-1);

        int count = left + right;

        return count;
    }
}

// Suppose there is an obstacle at (1,1) in the matrix on which you can't step,
// Print all possible paths from the top-left corner to the bottom-right corner of an m × n matrix,
// where at each step you can move vertical, horizontal or diagonal.
package backtracking;

import java.util.ArrayList;

public class PathWithObstacle {
    static void main() {

        boolean [][] maze = {
                {true, true, true},
                {true, false, true},
                {true, true, true}
        };

        retPath("", maze, 0, 0);

    }

    static void retPath(String p,boolean[][] maze, int r, int c) {
        if(r == maze.length - 1 && c == maze[0].length - 1) {
            System.out.println(p);
            return;
        }

        if(maze[r][c] == false) {
            return;
        }

        if(r < maze.length -1 && c< maze[0].length -1) {
            retPath(p + 'D', maze, r+1, c+1);
        }
        if(r < maze.length -1) {
            retPath(p + 'V', maze, r+1, c);
        }
        if(c< maze[0].length -1) {
            retPath(p + 'H', maze, r, c + 1);
        }
    }
}

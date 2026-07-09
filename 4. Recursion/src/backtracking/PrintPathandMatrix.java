package backtracking;

import java.util.Arrays;

public class PrintPathandMatrix {
    static void main() {
        boolean[][] maze = {
                {true, true, true},
                {true, true, true},
                {true, true, true}
        };
        int[][] path = new int[maze.length][maze[0].length];
        print("", maze, 0, 0, path, 1);
    }

    static void print(String p, boolean[][] maze, int r, int c, int[][] path, int step) {
        if(r == maze.length - 1 && c == maze[0].length - 1){
            path[r][c] = step;
            for(int[] num: path) {
                System.out.println(Arrays.toString(num));
            }
            System.out.println(p);
           System.out.println();
            return;
        }

        if(!maze[r][c]) {
            return;
        }

        maze[r][c] = false;
        path[r][c] = step;

        if(r < maze.length -1) {
            print(p + 'D', maze, r+1, c, path, step + 1);
        }
        if(c < maze[0].length -1) {
            print(p + 'R', maze, r, c+1, path, step + 1);
        }
        if(r > 0) {
            print(p + 'U', maze, r-1, c, path, step + 1);
        }
        if(c > 0) {
            print(p + 'L', maze, r, c-1, path, step + 1);
        }

        maze[r][c] = true;
        path[r][c] = 0;
    }
}

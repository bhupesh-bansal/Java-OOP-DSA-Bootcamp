// Print all possible paths from the top-left corner to the bottom-right corner of an m × n matrix,
// where at each step you can move vertical, horizontal or diagonal.
package backtracking;

import java.util.ArrayList;

public class PathWithDiagonalMove {
    static void main() {
        System.out.println(retPath("", 3, 3));
    }

    static ArrayList<String> retPath(String p, int r, int c) {
        if(r == 1 && c == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        ArrayList<String> list = new ArrayList<>();

        if(r > 1 && c > 1) {
            list.addAll(retPath(p + 'D', r-1, c-1));
        }
        if(r > 1) {
            list.addAll(retPath(p + 'V', r-1, c));
        }
        if(c > 1) {
            list.addAll(retPath(p + 'H', r, c-1));
        }

        return list;

    }
}

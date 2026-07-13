package backtracking;

public class NKnights {
    static void main() {
        int n = 4;
        boolean[][] board = new boolean[n][n];
        System.out.println(knights(board, 0, 0, n));
    }

    static int knights(boolean[][] board, int row, int col, int knights) {
        if(knights == 0) {
            display(board);
            System.out.println();
            return 1;
        }

        int count = 0;

        if(row == board.length) {
            return 0;
        }

        if(col == board.length) {
            count += knights(board, row+1, 0, knights);
            return count;
        }

        if(isSafe(board, row, col)) {
            board[row][col] = true;
            count += knights(board, row, col+1, knights-1);
            board[row][col] = false;
        }

        count += knights(board, row, col+1, knights);

        return count;
    }

    static boolean isValid(boolean[][] board,int row, int col) {
        return row >= 0 && col >= 0 && row < board.length && col < board.length;
    }

    static boolean isSafe(boolean[][] board,int row, int col) {

        if(isValid(board, row-2,col-1) && board[row-2][col-1]) {
            return false;
        }

        if(isValid(board, row-2,col+1) && board[row-2][col+1]) {
            return false;
        }

        if(isValid(board, row-1,col-2) && board[row-1][col-2]) {
            return false;
        }

        if(isValid(board, row-1,col+2) && board[row-1][col+2]) {
            return false;
        }

        return true;
    }

    static void display(boolean[][] board) {
        for(boolean[] row: board) {
            for(boolean element: row) {
                if(element) {
                    System.out.print("K ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}

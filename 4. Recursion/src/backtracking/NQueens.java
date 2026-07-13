package backtracking;

public class NQueens {
    static void main() {
        int n = 4;
        boolean[][] board = new boolean[n][n];
        System.out.println(queens(board, 0));
    }

    static int queens(boolean[][] board, int row) {
        if(row == board.length){
            // this will print the required pattern
            display(board);
            System.out.println();
            return 1;
        }

        int count = 0;

        //check every col of row and try to place the queen if it's safe
        for(int col = 0; col< board.length; col ++) {
            if(isSafe(board, row, col)) {
                board[row][col] = true;
                count += queens(board, row + 1);
                board[row][col] = false;
            }
        }

        return count;
    }

    static boolean isSafe(boolean[][] board, int row, int col) {

        //check vertical
        for(int i = 0; i < row; i++) {
            if(board[i][col]) {
                return false;
            }
        }

        //Left Diagonal
        int maxLeft = Math.min(row, col);
        for(int i = 1; i<= maxLeft; i++) {
            if(board[row-i][col-i]) {
                return false;
            }
        }

        //Right Diagonal
        int maxRight = Math.min(row, board.length - 1 - col);
        for(int i = 1; i <= maxRight; i++) {
            if(board[row-i][col+i]) {
                return false;
            }
        }

        return true;
    }

    static void display(boolean[][] board){
        for(boolean[] row : board) {
            for(boolean element : row) {
                if(element) {
                    System.out.print("Q ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }
}

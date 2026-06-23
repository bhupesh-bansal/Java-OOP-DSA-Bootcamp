public class RecursivePatternPrinting {
    static void main() {
        print1(4,0);
        print2(4,0);
    }

    static void print1(int row, int col) {
        if(row == 0) {
            return;
        }

        if(col < row) {
            System.out.print("* ");
            print1(row, col+1);
        } else {
            System.out.println("");
            print1(row-1, 0);
        }
    }

    static void print2(int row, int col) {
        if(row == 0) {
            return;
        }

        if(col < row) {
            print2(row, col+1);
            System.out.print("* ");
        } else {
            print2(row-1, 0);
            System.out.println("");
        }
    }
}

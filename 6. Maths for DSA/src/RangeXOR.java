public class RangeXOR {
    static void main() {
        int start = 3;
        int end = 9;

        int rangeXOR = xor(start-1)^xor(end);

        System.out.println(rangeXOR);
    }
    static int xor(int n) {
        //pattern of xor from 0 till n
        if(n%4 == 0) {
            return n;
        }
        if(n%4 == 1) {
            return 1;
        }
        if(n%4 == 2) {
            return n+1;
        }
            return 0;
    }
}

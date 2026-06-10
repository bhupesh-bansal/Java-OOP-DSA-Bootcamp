public class NumSetBits {
    static void main() {
        int num = 6;
        System.out.println(numBits(num));
    }

    static int numBits(int num) {
        int count = 0;
        while(num > 0) {
            count++;
            num -= (num & (-num));
        }
        return count;
    }
}

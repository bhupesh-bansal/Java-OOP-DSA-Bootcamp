public class PowerOf2 {
    static void main() {
        int num = 32;

        //if we and(&) with number-1 and it yield 0, the number will be power of 2.
        boolean ans = ((num & (num-1)) == 0);
        System.out.print(ans);
    }
}

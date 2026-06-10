public class NumDigits {
    static void main() {
        int num = 345899;
        int base = 10;

        int digits = (int)(Math.log(num)/Math.log(base)) + 1;
        System.out.println(digits);
    }
}

public class ExponentCalculation {
    static void main() {
        int num = 3;
        int exponent = 6;
        System.out.println(result(num,exponent));
    }

    static int result(int num, int exponent) {
        int ans = 1;
        int base = num;
        int power = exponent;
        while(power != 0) {
            if((power&1) == 1) {
                ans *= base;
            }
            base *= base;
            power = power>>1;
        }
        return ans;
    }
}

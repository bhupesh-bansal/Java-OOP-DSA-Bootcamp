public class findOddNum {
    static void main() {
        int num = 1000000;

        if((num & 1) == 1) {
            System.out.println("Odd");
        } else {
            System.out.println("Even");
        }
    }
}

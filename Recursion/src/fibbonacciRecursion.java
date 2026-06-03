//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class fibbonacciRecursion {
    public static void main(String[] args) {
        System.out.println(fibo(15));
    }

    static int fibo(int n) {
        if(n< 0 ) {
            System.out.println("Please provide a positive number.");
            return -1;
        }
        if(n < 2) {
            return n;
        }
        return fibo(n-1) + fibo(n-2);
    }
}
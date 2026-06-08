public class FibonacciFormula {
    static void main() {
        System.out.println(fibo(50));
    }

    static long fibo(int n) {
        double sqRoot = Math.sqrt(5);
        double phi = ((1 + sqRoot)/2);
        double psi = ((1 - sqRoot)/2);
        return (long) (((Math.pow(phi, n) - Math.pow(psi, n))/sqRoot));
    }
}
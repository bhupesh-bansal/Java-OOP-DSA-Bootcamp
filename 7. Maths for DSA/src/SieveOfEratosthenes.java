//Find all the prime numbers less than 40 using sieve of Eratosthenes
//Time-Complexity of this algorithm is O(n * log(log(n)))

public class SieveOfEratosthenes {
    static void main() {
        int n = 40;
        boolean[] nonPrime = new boolean[n + 1];
        sieve(n, nonPrime);
    }

    static void sieve(int n, boolean[] nonPrime) {
        for(int i = 2; i*i<= n; i++) {
            if(!nonPrime[i]) {
                for(int j = i*2; j<= n; j +=i) {
                    nonPrime[j] = true;
                }
            }
        }
        for(int i = 2; i<=n; i++) {
            if(!nonPrime[i]) {
                System.out.print(i + " ");
            }
        }
    }
}

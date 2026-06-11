//find n-th bit of a number

public class Findbit {
    static void main() {
        int num = 166;
        int n = 8;
        int mask = 1 << (n-1);
        System.out.println(166 & mask);
    }
}

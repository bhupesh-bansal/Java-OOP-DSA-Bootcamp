import java.util.ArrayList;

public class NumFactors {
    static void main() {
        int n = 20;
        factors(n);
    }

    //Time Complexity of this method will be O(sqrt(n))
    static void factors(int n) {
        //ArrayList is maintained to print the factors in ascending order.
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= Math.sqrt(n) ;i++) {
            if(n%i == 0) {
                if(i == n/i) {
                    System.out.print(i + " ");
                } else {
                    System.out.print(i + " ");
                    list.add(n/i);
                }
            }
        }

        //In the ArrayList, the factors will be in descending order that's why we're starting the loop from end.
        for(int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }
}

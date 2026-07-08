// Print all the permutations for a given String.

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    static void main() {
//        permutations("","abc");
//        System.out.println(retPermutations("", "abc"));
        System.out.println(numPermutations("", "abc"));
    }

    static void permutations(String p, String up) {
         if(up.isEmpty()) {
             System.out.println(p);
             return;
         }

         char ch = up.charAt(0);

         // Here we're making variable num of recursion calls at every step.
         // That's why we're using a for loop.
         for(int i =0; i<= p.length(); i++) {
             String first = p.substring(0,i);
             String last = p.substring(i);
             permutations(first + ch + last, up.substring(1));
         }
    }

    // function that returns an arraylist instead of simply printing the values.
    static ArrayList<String> retPermutations(String p, String up) {
        if(up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        char ch = up.charAt(0);
        ArrayList<String> list= new ArrayList<>();

        // i<= p.length() because number of places(gaps) at which we can add the character is num alphabets + 1.
        for(int i =0; i<=p.length(); i++) {
            String first = p.substring(0,i);
            String last = p.substring(i,p.length());

            list.addAll(retPermutations(first + ch + last, up.substring(1)));
        }

        return list;
    }

    //function to count number of recursion calls
    static int numPermutations(String p, String up) {
        if(up.isEmpty()) {
            return 1;
        }

        char ch = up.charAt(0);
        int count = 0;

        for(int i = 0; i<=p.length(); i++) {
            String first = p.substring(0,i);
            String last = p.substring(i,p.length());

            count = count + numPermutations(first + ch + last, up.substring(1));
        }
        return count;
    }
}

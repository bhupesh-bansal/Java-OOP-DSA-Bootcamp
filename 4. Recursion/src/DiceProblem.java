// Given a target number N, print all possible sequences of dice rolls (using a custom faced die) whose sum is exactly N.

import java.util.ArrayList;

public class DiceProblem {
    static void main() {
        System.out.println(die("", 4, 8));
    }

    static ArrayList<String> die(String p, int num, int faces) {
        if(num == 0) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        ArrayList<String> list = new ArrayList<>();

        for(int i = 1; i<= num && i<= faces; i++) {
            list.addAll(die(p+i, num-i, faces));
        }

        return list;
    }
}

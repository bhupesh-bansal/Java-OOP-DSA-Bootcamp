package SubSequenceQuestions;

import java.util.*;

public class SubSetDuplicate {
    static void main() {
        int[] arr = {1,2,2};
        System.out.println(subSet(arr));
    }

    static List<List<Integer>> subSet(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> outer = new ArrayList<>();

        outer.add(new ArrayList<>());

        int start;
        int end =0;

        for(int i =0; i< arr.length; i++) {
            start = 0;
            //check if the previous element in the arr is same or different
            if(i > 0 && arr[i] == arr[i-1]) {
                start = end + 1;
            }
            end = outer.size() -1;
            int n = outer.size();
            for(int j = start; j< n; j++) {
                ArrayList<Integer> internal = new ArrayList<>(outer.get(j));
                internal.add(arr[i]);

                outer.add(internal);
            }
        }

        return outer;
    }
}

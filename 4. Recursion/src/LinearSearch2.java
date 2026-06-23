//Return all occurences of a particular element in an array.

import java.util.ArrayList;

public class LinearSearch2 {
    static void main() {
        int[] arr = {1,2,3,4,5,4,4};
        System.out.println(search2(arr, 4, 0));
    }

    //this is more optimised way
    static ArrayList<Integer> search(int[] arr, int target, int index, ArrayList<Integer> list) {
        if(index == arr.length) {
            return list;
        }

        if(arr[index] == target) {
            list.add(index);
        }

        return search(arr, target, index+1, list);
    }

    //Return the ArrayList without taking it as an argument
    static ArrayList<Integer> search2 (int[] arr, int target, int index) {
        ArrayList<Integer> list = new ArrayList<>();
        if(index == arr.length) {
            return list;
        }
        if(arr[index] == target) {
            list.add(index);
        }

        ArrayList<Integer> ans = search2(arr, target, index + 1);

        list.addAll(ans);

        return list;
    }
}

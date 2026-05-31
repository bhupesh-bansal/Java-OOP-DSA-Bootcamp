package InsertionSort;

import java.util.Arrays;

public class insertion {
    public static void main(String[] args) {
        int[] arr = {-300, 0, 5, -15, 4};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void insertionSort (int[] arr) {
        //i and j break down the array in parts and sorting is done in these parts of array.
        for(int i = 0; i<= arr.length-2; i++) {
            //j will define the end of the portion of array till which we will sort in a specific step.
            for(int j = i+1; j>0; j--) {
                if(arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                } else break;
            }
        }
    }
}

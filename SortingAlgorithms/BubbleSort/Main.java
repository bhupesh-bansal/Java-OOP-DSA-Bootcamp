package BubbleSort;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = { 5, 3, 2, 4, 1};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void bubbleSort (int[] arr) {
        boolean swapped; //flag variable to keep a track if swapping happens in a loop or not.

        //run the outer loop n-1 times
        for(int i = 0; i < arr.length; i++) {
            swapped = false;
            // on each step, the element with max-value will come at end.
            // j < arr.length - i because after every loop, the righmost element swapped is always
            // sorted (largest value) and hence it is absurd to compare it again.
            for(int j = 1; j < arr.length - i; j++) {
                if(arr[j] < arr[j-1]) {
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;

                    swapped = true;
                }
            }

            // if in a step, swapping does not happen that means the array is sorted
            // and hence there is no need for further steps.
            if(!swapped) {
                break;
            }
        }
    }
}
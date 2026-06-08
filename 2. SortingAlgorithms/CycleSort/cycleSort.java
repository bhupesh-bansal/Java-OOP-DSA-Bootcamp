package CycleSort;

import java.util.Arrays;

public class cycleSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 1, 4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    // My attempt
   /* static void cycleSort(int[] arr) {
        for(int i = 0; i< arr.length; i++) {
            // loop will run until the index has it's correct value.
            while(arr[i] != i+1) {
                if (arr[i] != i + 1) {
                    //swap the element with another element at the correct index of 1st element.
                    int temp = arr[i];
                    arr[i] = arr[temp - 1];
                    arr[temp - 1] = temp;
                }
            }
        }
    } */

    //Optimised solution or Correct solution:
    static void sort(int[] arr) {
        int i = 0; //counter variable
        while(i < arr.length) {
            // correct contains the actual index value of the element at i
            int correct = arr[i]-1;
            // this will swap until the index i has its correct value
            if(arr[correct] != arr[i]) {
                swap(arr, i, correct);
            } else {
                i++; // if the index has its correct value then move to the next index.
            }
        }
    }

    static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}

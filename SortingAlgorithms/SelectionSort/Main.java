package SelectionSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] arr = {-4, 3, 0, -36, 49};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void selectionSort(int[] arr) {
            for(int i = 0; i< arr.length-1; i++) {
                //find the last index of unsorted array at every step
                int last = arr.length-i-1;
                //find the index of max. element from unsorted part of array
                int maxIndex = max(arr, 0, last);
                //place the max. element at the end of the unsorted array(which shrinks at every step)
                int temp = arr[last];
                arr[last] = arr[maxIndex];
                arr[maxIndex] = temp;
            }
    }

    static int max (int[] arr, int startIndex, int endIndex){
        int maxIndex = startIndex;
        for(int i = startIndex; i<= endIndex; i++) {
            if(arr[maxIndex] < arr[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}

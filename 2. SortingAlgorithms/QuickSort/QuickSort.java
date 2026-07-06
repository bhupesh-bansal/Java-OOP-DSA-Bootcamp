package QuickSort;

import java.util.Arrays;

public class QuickSort {
    static void main() {
        int[] arr = {1, 3, 4, 5, 2};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    static void quickSort(int[] arr, int low, int high) {
        if(low >= high) {
            return;
        }

        int start = low;
        int end = high;
        int pivot = arr[start + (end - start)/2];

        while(start <= end) {
            while(arr[start] < pivot) {
                start++;
            }
            while(arr[end] > pivot) {
                end--;
            }

            if(start <= end) {
                int temp = arr[end];
                arr[end] = arr[start];
                arr[start] = temp;
                start++;
                end--;
            }

            quickSort(arr, low, end);
            quickSort(arr, start, high);
        }
    }
}

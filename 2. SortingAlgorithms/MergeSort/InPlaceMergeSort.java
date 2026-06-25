package MergeSort;

import java.util.Arrays;

public class InPlaceMergeSort {
    static void main() {
        int[] arr = {8,3,6,4,1,2};
        mergeSort(arr, 0 , arr.length);
        System.out.println(Arrays.toString(arr));
    }

    static void mergeSort(int[] arr, int start, int end) {
        if(end - start == 1) {
            return ;
        }

        int mid = start + (end - start)/2;

        mergeSort(arr, start, mid);
        mergeSort(arr, mid, end);

        merge(arr, start, mid, end);
    }

    static void merge (int[] arr, int start, int mid, int end) {
        int[] mix = new int[end - start];

        int i = start;
        int j = mid;
        int k = 0;

        while(i< mid && j < end) {
            if(arr[i] < arr[j]) {
                mix[k] = arr[i];
                i++;
            } else {
                mix[k] = arr[j];
                j++;
            }
            k++;
        }

        while(i<mid) {
            mix[k] = arr[i];
            i++;
            k++;
        }

        while(j < end) {
            mix[k] = arr[j];
            j++;
            k++;
        }

        for(int index = 0; index < mix.length; index++) {
            arr[start + index] = mix[index];
        }
    }
}

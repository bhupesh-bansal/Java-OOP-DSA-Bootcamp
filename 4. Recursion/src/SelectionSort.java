import java.util.Arrays;

public class SelectionSort {
    static void main() {
        int[] arr = {1,3,0,5,2,4};
        selectionSort(arr, 0, arr.length-1, 0);
        System.out.println(Arrays.toString(arr));
    }

    static void selectionSort(int[] arr, int index, int end, int maxIndex) {
        if(end == 0) {
            return;
        }

        if(index<=end) {
            //this will find the max of array during a particular round
            if(arr[maxIndex]<arr[index]) {
                maxIndex = index;
            }
            selectionSort(arr, index+1, end, maxIndex);
        } else{
            swap(arr, maxIndex, end);
            selectionSort(arr, 0, end-1,0);
        }
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}

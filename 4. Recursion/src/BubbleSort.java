import java.lang.reflect.Array;
import java.util.Arrays;

public class BubbleSort {
    static void main() {
        int[] arr = {1,3,2,5};
        sort(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    static void sort(int[] arr, int index, int end) {
        if(end == 0) {
            return;
        }

        if(index<end) {
            if(arr[index] > arr[index+1]) {
                swap(arr, index);
            }
            sort(arr,index+1, end);
        } else{
            sort(arr, 0,end-1);
        }
    }

    static void swap(int[] arr, int index) {
        int temp = arr[index];
        arr[index] = arr[index+1];
        arr[index+1] = temp;
    }
}

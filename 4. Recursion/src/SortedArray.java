//Through recursion, check whether the array is sorted or not.

public class SortedArray {
    static void main() {
        int[] arr = {1,2,3,3,5};
        System.out.println(isSorted(arr, 0));
    }

    static boolean isSorted(int[] arr, int index) {
        if(index == (arr.length-1)) {
            return true;
        }

            return arr[index] <= arr[index+1] && isSorted(arr, index + 1);
    }
}

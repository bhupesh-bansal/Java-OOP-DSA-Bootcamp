// Linear search through recursion

public class LinearSearch {
    static void main() {
        int[] arr = {1,2,3,4,5};
        System.out.println(search(arr, 2, 0));
    }

    static int search(int[] arr, int target, int index) {
        if(index == arr.length) {
            return -1;
        }

        if(arr[index] == target){
            return index;
        } else {
            return search(arr, target, index + 1);
        }
    }
}

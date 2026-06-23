public class BinarySearchRecursion {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 6;
        System.out.println(search(arr, target, 0, arr.length));
    }

    static int search(int[] nums, int target, int start, int end) {
        int mid = start + (end-start)/2;
        if(start > end) {
            return -1;
        }

        if(nums[mid] == target) {
            return mid;
        } else if(nums[mid] > target) {
            return search(nums, target, start, mid-1);
        }
        return search(nums, target, mid+1, end);
    }
}

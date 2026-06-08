package CycleSort;

//leetcode problem - 645 https://leetcode.com/problems/set-mismatch/

public class setMismatch {
    public static void main(String[] args) {

    }

    static int[] findErrorNums(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            int correctIndex = nums[i] - 1;
            if(nums[i] != nums[correctIndex]) {
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }

        for(int j = 0; j<nums.length; j++) {
            if(nums[j] != j + 1) {
                return new int[] {nums[j], j+1};
            }
        }
        return new int[] {-1, -1};
    }
}
package CycleSort;

public class firstMissingPositive {
    // leetcode hard - Q41 https://leetcode.com/problems/first-missing-positive

    public static void main(String[] args) {

    }

    static int firstMissingPositive(int[] nums) {
        int i = 0;

        while(i < nums.length) {
            if(nums[i]>0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                int correctIndex = nums[i] - 1;
                int temp = nums[i];
                nums[i] = nums[correctIndex];
                nums[correctIndex] = temp;
            } else {
                i++;
            }
        }

        for(int j = 0; j<nums.length; j++) {
            if( nums[j] != j + 1) {
                return j + 1;
            }
        }
        return nums.length + 1;
    }
}

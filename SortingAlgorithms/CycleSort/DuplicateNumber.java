package CycleSort;

//leetcode medium - Q287(https://leetcode.com/problems/find-the-duplicate-number/)

//First approach - I thought of sorting the full array with cycle sort first and then return the last index.
//last index will be the repeated element after sorting.
//Correct approach - in no swapping case, check whether the element you're trying to swap is already at it's correct position or not.
//If not, then that is the required answer.

public class DuplicateNumber {
    public int findDuplicate(int[] nums) {
        int i = 0;

        while(i<nums.length) {
            int correctIndex = nums[i] - 1;

            if(nums[i] != i+1) {
                if(nums[i] != nums[correctIndex]) {
                    int temp = nums[i];
                    nums[i] = nums[correctIndex];
                    nums[correctIndex] = temp;
                } else {
                    return nums[i];
                }
            } else {
                i++;
            }

        }
        return -1;
    }
}

package Microsoft;

/*
Given an array nums, reorder it "in-place" such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example 1:
num = [3,5,2,1,6,4]
O/P = [3,5,1,6,2,4]
*/

public class Q280WiggleSort {

    public void wiggleSort(int[] nums) {

        for (int i = 0; i < nums.length - 1; i++) 
        {
            // even index should have smaller elements
            if (i % 2 == 0 && nums[i] > nums[i + 1])
                swap(nums, i, i + 1);
            // odd index should have larger elements
            else if (i % 2 != 0 && nums[i] < nums[i + 1])
                swap(nums, i, i + 1);
        }
    }

    private void swap(int[] nums, int a, int b) {
        
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
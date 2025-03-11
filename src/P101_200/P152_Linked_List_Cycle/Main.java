package P101_200.P152_Linked_List_Cycle;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {2,3,-2,4};

        System.out.println(solution.maxProduct(nums1));
    }

    public int maxProduct(int[] nums) {
        int n = nums.length; //length of input array

        if (n == 0) { //if array is empty
            return 0;
        }

        int maxProduct = nums[0]; //box for max product
        int minProduct = nums[0]; //and for min
        int result = nums[0]; //box for result

        for (int i = 1; i < n; i++) {
            //swap maxProduct and minProduct if the current number is negative
            if (nums[i] < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            //update maxProduct and minProduct for the current position
            maxProduct = Math.max(nums[i], maxProduct * nums[i]);
            minProduct = Math.min(nums[i], minProduct * nums[i]);

            //update the global result
            result = Math.max(result, maxProduct);
        }

        return result;
    }
}

//Given an integer array nums, find a /subarray that has the largest product, and return the product.
//The test cases are generated so that the answer will fit in a 32-bit integer.

//Example 1:
//Input: nums = [2,3,-2,4]
//Output: 6
//Explanation: [2,3] has the largest product 6.

//Example 2:
//Input: nums = [-2,0,-1]
//Output: 0
//Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

//Constraints:
//1 <= nums.length <= 2 * 104
//-10 <= nums[i] <= 10
//The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

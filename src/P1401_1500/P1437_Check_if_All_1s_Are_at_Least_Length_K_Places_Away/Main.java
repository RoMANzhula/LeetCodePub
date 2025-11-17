package P1401_1500.P1437_Check_if_All_1s_Are_at_Least_Length_K_Places_Away;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {1,0,0,0,1,0,0,1};
        int k1 = 2;

        System.out.println(solution.kLengthApart(nums1, k1)); // output: true
    }

    public boolean kLengthApart(int[] nums, int k) {
        int lastOne = -1; // position of the previous 1

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (lastOne != -1 && i - lastOne - 1 < k) {
                    return false;
                }

                lastOne = i;
            }
        }

        return true;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//Given an binary array nums and an integer k, return true if all 1's are at least k places away from each other,
// otherwise return false.

//Example 1:
//Input: nums = [1,0,0,0,1,0,0,1], k = 2
//Output: true
//Explanation: Each of the 1s are at least 2 places away from each other.

//Example 2:
//Input: nums = [1,0,0,1,0,1], k = 2
//Output: false
//Explanation: The second 1 and third 1 are only one apart from each other.

//Constraints:
//1 <= nums.length <= 105
//0 <= k <= nums.length
//nums[i] is 0 or 1

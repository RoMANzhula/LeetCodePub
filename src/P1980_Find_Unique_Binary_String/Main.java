package P1980_Find_Unique_Binary_String;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String[] nums1 = {"01", "10"};
        String[] nums2 = {"00", "01"};
        String[] nums3 = {"111", "011", "001"};

        System.out.println(findDifferentBinary(nums1));
        System.out.println(findDifferentBinary(nums2));
        System.out.println(findDifferentBinary(nums3));

        System.out.println("------------");

        System.out.println(findDifferentBinaryString(nums1));
        System.out.println(findDifferentBinaryString(nums2));
        System.out.println(findDifferentBinaryString(nums3));
    }

    //more quickly
    public static String findDifferentBinaryString(String[] nums) {
        Set<String> set = new HashSet<>();

        for (String num : nums) {
            set.add(num);
        }

        int n = nums.length;
        for (int i = 0; i < (1 << n); i++) {
            String binaryString = Integer.toBinaryString(i);
            while (binaryString.length() < n) {
                binaryString = "0" + binaryString;
            }
            if (!set.contains(binaryString)) {
                return binaryString;
            }
        }

        return "";
    }

    public static String findDifferentBinary(String[] nums) {
        int n = nums.length;
        Set<String> set = new HashSet<>();

        //add all possible binary rows with length n to Set
        for (int i = 0; i < (1 << n); i++) {
            StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(i));
            while (binaryString.length() < n) {
                binaryString.insert(0, '0'); //add zero to needs length
            }
            set.add(binaryString.toString());
        }

        //remote rows which already existed from array nums
        for (String num : nums) {
            set.remove(num);
        }

        //comeback somebody not repeat binary row
        return set.iterator().next();
    }
}

//Given an array of strings nums containing n unique binary strings each of length n, return a binary string of
// length n that does not appear in nums. If there are multiple answers, you may return any of them.
//
//Example 1:
//
//Input: nums = ["01","10"]
//Output: "11"
//Explanation: "11" does not appear in nums. "00" would also be correct.
//Example 2:
//
//Input: nums = ["00","01"]
//Output: "11"
//Explanation: "11" does not appear in nums. "10" would also be correct.
//Example 3:
//
//Input: nums = ["111","011","001"]
//Output: "101"
//Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.
//
//Constraints:
//
//n == nums.length
//1 <= n <= 16
//nums[i].length == n
//nums[i] is either '0' or '1'.
//All the strings of nums are unique.

package P2001_2100.P2053_Kth_Distinct_String_in_an_Array;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] arr1 = {"d","b","c","b","c","a"};
        int k1 = 2;
        System.out.println(solution.kthDistinct(arr1, k1)); // Output: "a"

        String[] arr2 = {"aaa","aa","a"};
        int k2 = 1;
        System.out.println(solution.kthDistinct(arr2, k2)); // Output: "aaa"

        String[] arr3 = {"a","b","a"};
        int k3 = 3;
        System.out.println(solution.kthDistinct(arr3, k3)); // Output: ""
    }

    public String kthDistinct(String[] arr, int k) {
        // Step 1: Count the frequency of each string
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String str : arr) {
            frequencyMap.put(str, frequencyMap.getOrDefault(str, 0) + 1);
        }

        // Step 2: Find the k-th distinct string
        int distinctCount = 0;
        for (String str : arr) {
            if (frequencyMap.get(str) == 1) {
                distinctCount++;
                if (distinctCount == k) {
                    return str;
                }
            }
        }

        // If there are fewer than k distinct strings, return an empty string
        return "";
    }
}

//Explanation:
//Step 1: We create a HashMap called frequencyMap to store the count of each string in the array.
//Step 2: We iterate over the array a second time. For each string that has a frequency of 1 (i.e., it is distinct),
// we increase our distinctCount.
//If distinctCount matches k, we return the current string.
//If we finish iterating through the array without finding the k-th distinct string, we return an empty string.
//This solution ensures that we correctly handle the order of appearance and the distinctness of strings, satisfying
// the problem constraints efficiently.


//A distinct string is a string that is present only once in an array.
//Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are
// fewer than k distinct strings, return an empty string "".
//Note that the strings are considered in the order in which they appear in the array.
//
//Example 1:
//Input: arr = ["d","b","c","b","c","a"], k = 2
//Output: "a"
//Explanation:
//The only distinct strings in arr are "d" and "a".
//"d" appears 1st, so it is the 1st distinct string.
//"a" appears 2nd, so it is the 2nd distinct string.
//Since k == 2, "a" is returned.

//Example 2:
//Input: arr = ["aaa","aa","a"], k = 1
//Output: "aaa"
//Explanation:
//All strings in arr are distinct, so the 1st string "aaa" is returned.

//Example 3:
//Input: arr = ["a","b","a"], k = 3
//Output: ""
//Explanation:
//The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".
//
//Constraints:
//1 <= k <= arr.length <= 1000
//1 <= arr[i].length <= 5
//arr[i] consists of lowercase English letters.
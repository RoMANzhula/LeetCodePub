package P101_200.P169_Majority_Element;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] nums1 = {3, 2, 3};
        int[] nums2 = {2, 2, 1, 1, 1, 2, 2};

        System.out.println(solution.majorityElement(nums1)); // output 3
        System.out.println(solution.majorityElement(nums2)); // output 2
    }

//    public int majorityElement(int[] nums) {
//        int mostFrequentNumber = -1;
//        int maxFrequency = 0;
//
//        for (int num : nums) {
//            int frequency = 0;
//            for (int i : nums) {
//                if (i == num) {
//                    frequency++;
//                }
//            }
//            if (frequency > maxFrequency) {
//                maxFrequency = frequency;
//                mostFrequentNumber = num;
//            }
//        }
//
//        return mostFrequentNumber;
//    }

    // faster solve
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == candidate) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    candidate = nums[i];
                    count = 1;
                }
            }
        }

        return candidate;
    }

}

//Цей код (нижній варіант) визначає клас MajorityElement з методом majorityElement, який реалізує алгоритм голосування Бойера-Мура для
// пошуку більшість елементу в масиві nums.


//Given an array nums of size n, return the majority element.
//The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority
// element always exists in the array.

//Example 1:
//Input: nums = [3,2,3]
//Output: 3

//Example 2:
//Input: nums = [2,2,1,1,1,2,2]
//Output: 2
//
//Constraints:
//n == nums.length
//1 <= n <= 5 * 104
//-109 <= nums[i] <= 109
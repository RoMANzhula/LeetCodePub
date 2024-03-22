package P301_400.P387_First_Unique_Character_in_a_String;

public class Main {
    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));
        System.out.println(firstUniqChar("aabb"));
    }

    public static int firstUniqChar(String s) {
        char[] sChars = s.toCharArray();

        for (int i = 0; i < sChars.length; i++) {
            boolean isUnique = true;

            for (int j = 0; j < sChars.length; j++) {
                if (i != j && sChars[i] == sChars[j]) {
                    isUnique = false;
                    break;
                }
            }

            if (isUnique) {
                return i;
            }
        }

        return -1;
    }

    //faster solve
    //    public int firstUniqChar(String s) {
    //        Map<Character, Integer> frequencyMap = new HashMap<>();
    //
    //        // Count frequency of each char in line
    //        for (char c : s.toCharArray()) {
    //            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
    //        }
    //
    //        // Find first unique char
    //        for (int i = 0; i < s.length(); i++) {
    //            if (frequencyMap.get(s.charAt(i)) == 1) {
    //                return i;
    //            }
    //        }
    //
    //        return -1; // If not unique char
    //    }
}

//Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.

//Example 1:
//Input: s = "leetcode"
//Output: 0

//Example 2:
//Input: s = "loveleetcode"
//Output: 2

//Example 3:
//Input: s = "aabb"
//Output: -1

//Constraints:
//1 <= s.length <= 105
//s consists of only lowercase English letters.

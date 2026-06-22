package P1101_1200.P1189_Maximum_Number_of_Balloons;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.maxNumberOfBalloons("nlaebolko"));         // 1
        System.out.println(solution.maxNumberOfBalloons("loonbalxballpoon"));  // 2
        System.out.println(solution.maxNumberOfBalloons("leetcode"));          // 0
    }

    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];

        for (char c : text.toCharArray()) {
            count[c - 'a']++;
        }

        return Math.min(
                Math.min(count['b' - 'a'], count['a' - 'a']),
                Math.min(
                        Math.min(count['l' - 'a'] / 2, count['o' - 'a'] / 2),
                        count['n' - 'a']
                )
        );
    }

}

//Complexity:
// time - O(n)
// space - O(1(


//Given a string text, you want to use the characters of text to form as many instances of the word "balloon" as
// possible.
//You can use each character in text at most once. Return the maximum number of instances that can be formed.

//Example 1:
//Input: text = "nlaebolko"
//Output: 1

//Example 2:
//Input: text = "loonbalxballpoon"
//Output: 2

//Example 3:
//Input: text = "leetcode"
//Output: 0

//Constraints:
//1 <= text.length <= 104
//text consists of lower case English letters only.

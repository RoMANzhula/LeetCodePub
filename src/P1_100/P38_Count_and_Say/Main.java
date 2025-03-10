package P1_100.P38_Count_and_Say;

public class Main {

    public static void main(String[] args) {
        System.out.println(countAndSay(2));
    }

    public static String countAndSay(int n) {
        if (n == 1) { //for exception story
            return "1";
        }

        String prevTerm = countAndSay(n - 1); //recursively calls itself
        StringBuilder result = new StringBuilder(); //our buffer for sequence result

        int count = 1; //counter for pair numbers(elements)
        for (int i = 0; i < prevTerm.length(); i++) { //Cycle will go through each character
            char currentDigit = prevTerm.charAt(i); //each character by index
            if (i + 1 < prevTerm.length() && prevTerm.charAt(i + 1) == currentDigit) { //if pair
                count++; //saving it
            } else { //if not pair
                result.append(count).append(currentDigit); //save character's counter and this character
                count = 1; //go to next character
            }
        }

        return result.toString(); //return our result's buffer
    }

}


//The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
//countAndSay(1) = "1"
//countAndSay(n) is the run-length encoding of countAndSay(n - 1).
//Run-length encoding (RLE) is a string compression method that works by replacing consecutive identical
// characters (repeated 2 or more times) with the concatenation of the character and the number marking the count of
// the characters (length of the run). For example, to compress the string "3322251" we replace "33" with "23",
// replace "222" with "32", replace "5" with "15" and replace "1" with "11". Thus the compressed string
// becomes "23321511".
//Given a positive integer n, return the nth element of the count-and-say sequence.

//Example 1:
//Input: n = 4
//Output: "1211"
//Explanation:
//countAndSay(1) = "1"
//countAndSay(2) = RLE of "1" = "11"
//countAndSay(3) = RLE of "11" = "21"
//countAndSay(4) = RLE of "21" = "1211"

//Example 2:
//Input: n = 1
//Output: "1"
//Explanation:
//This is the base case.

//Constraints:
//1 <= n <= 30
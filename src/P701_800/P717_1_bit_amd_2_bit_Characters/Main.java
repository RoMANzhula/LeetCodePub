package P701_800.P717_1_bit_amd_2_bit_Characters;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] bits1 = {1, 0, 0};
        int[] bits2 = {1, 1, 1, 0};

        System.out.println(solution.isOneBitCharacter(bits1)); // true
        System.out.println(solution.isOneBitCharacter(bits2)); // false
    }

    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;

        while (i < n - 1) {
            if (bits[i] == 1) {
                i += 2; // two-bit character
            } else {
                i += 1; // one-bit character
            }
        }

        // шf we stopped exactly at the last index, it's a one-bit character
        return i == n - 1;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//We have two special characters:
//The first character can be represented by one bit 0.
//The second character can be represented by two bits (10 or 11).
//Given a binary array bits that ends with 0, return true if the last character must be a one-bit character.

//Example 1:
//Input: bits = [1,0,0]
//Output: true
//Explanation: The only way to decode it is two-bit character and one-bit character.
//So the last character is one-bit character.

//Example 2:
//Input: bits = [1,1,1,0]
//Output: false
//Explanation: The only way to decode it is two-bit character and two-bit character.
//So the last character is not one-bit character.

//Constraints:
//1 <= bits.length <= 1000
//bits[i] is either 0 or 1.

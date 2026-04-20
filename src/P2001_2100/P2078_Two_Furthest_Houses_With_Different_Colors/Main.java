package P2001_2100.P2078_Two_Furthest_Houses_With_Different_Colors;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] colors1 = {1,1,1,6,1,1,1};
        System.out.println(solution.maxDistance(colors1)); // 3

        int[] colors2 = {1,8,3,8,3};
        System.out.println(solution.maxDistance(colors2)); // 4

        int[] colors3 = {0,1};
        System.out.println(solution.maxDistance(colors3)); // 1
    }

    public int maxDistance(int[] colors) {
        int n = colors.length;
        int maxDist = 0;

        // compare with first house
        for (int i = n - 1; i >= 0; i--) {
            if (colors[i] != colors[0]) {
                maxDist = Math.max(maxDist, i - 0);
                break;
            }
        }

        // cmpare with last house
        for (int i = 0; i < n; i++) {
            if (colors[i] != colors[n - 1]) {
                maxDist = Math.max(maxDist, (n - 1) - i);
                break;
            }
        }

        return maxDist;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//There are n houses evenly lined up on the street, and each house is beautifully painted. You are given a 0-indexed
// integer array colors of length n, where colors[i] represents the color of the ith house.
//Return the maximum distance between two houses with different colors.
//The distance between the ith and jth houses is abs(i - j), where abs(x) is the absolute value of x.

//Example 1:
//Input: colors = [1,1,1,6,1,1,1]
//Output: 3
//Explanation: In the above image, color 1 is blue, and color 6 is red.
//The furthest two houses with different colors are house 0 and house 3.
//House 0 has color 1, and house 3 has color 6. The distance between them is abs(0 - 3) = 3.
//Note that houses 3 and 6 can also produce the optimal answer.

//Example 2:
//Input: colors = [1,8,3,8,3]
//Output: 4
//Explanation: In the above image, color 1 is blue, color 8 is yellow, and color 3 is green.
//The furthest two houses with different colors are house 0 and house 4.
//House 0 has color 1, and house 4 has color 3. The distance between them is abs(0 - 4) = 4.

//Example 3:
//Input: colors = [0,1]
//Output: 1
//Explanation: The furthest two houses with different colors are house 0 and house 1.
//House 0 has color 0, and house 1 has color 1. The distance between them is abs(0 - 1) = 1.

//Constraints:
//n == colors.length
//2 <= n <= 100
//0 <= colors[i] <= 100
//Test data are generated such that at least two houses have different colors.

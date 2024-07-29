package P1301_1400.P1395_Count_Number_of_Team;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] rating1 = {2, 5, 3, 4, 1};
        System.out.println(solution.numTeams(rating1)); // Output: 3

        int[] rating2 = {2, 1, 3};
        System.out.println(solution.numTeams(rating2)); // Output: 0

        int[] rating3 = {1, 2, 3, 4};
        System.out.println(solution.numTeams(rating3)); // Output: 4
    }

    public int numTeams(int[] rating) {
        int n = rating.length;
        int count = 0;

        for (int j = 1; j < n - 1; j++) {
            int leftLess = 0, leftGreater = 0, rightLess = 0, rightGreater = 0;

            // Count the number of elements less than and greater than rating[j] to the left of j
            for (int i = 0; i < j; i++) {
                if (rating[i] < rating[j]) {
                    leftLess++;
                } else if (rating[i] > rating[j]) {
                    leftGreater++;
                }
            }

            // Count the number of elements less than and greater than rating[j] to the right of j
            for (int k = j + 1; k < n; k++) {
                if (rating[k] < rating[j]) {
                    rightLess++;
                } else if (rating[k] > rating[j]) {
                    rightGreater++;
                }
            }

            // Combine the counts to form valid teams
            count += leftLess * rightGreater + leftGreater * rightLess;
        }

        return count;
    }
}

//Explanation:
//Initialization: We initialize count to 0. This will hold the number of valid teams.
//Loop through all middle soldiers: We iterate over each possible middle soldier j (from index 1 to n-2).
//Count left and right conditions:
//leftLess: Number of soldiers to the left of j with a rating less than rating[j].
//leftGreater: Number of soldiers to the left of j with a rating greater than rating[j].
//rightLess: Number of soldiers to the right of j with a rating less than rating[j].
//rightGreater: Number of soldiers to the right of j with a rating greater than rating[j].
//Form valid teams: For each j, the number of valid teams can be calculated by combining counts:
//Teams of the form (i, j, k) where rating[i] < rating[j] < rating[k] are counted as leftLess * rightGreater.
//Teams of the form (i, j, k) where rating[i] > rating[j] > rating[k] are counted as leftGreater * rightLess.
//Sum the counts: Add the valid teams formed for each j to the total count.
//Return the result: After processing all possible middle soldiers, return the total count of valid teams.
//This approach ensures that we efficiently count all possible valid teams in the array. The complexity is O(n^2),
// which is acceptable given the constraints (n up to 1000).


//There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
//You have to form a team of 3 soldiers amongst them under the following rules:
//Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
//A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
//Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
//
//Example 1:
//Input: rating = [2,5,3,4,1]
//Output: 3
//Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).

//Example 2:
//Input: rating = [2,1,3]
//Output: 0
//Explanation: We can't form any team given the conditions.

//Example 3:
//Input: rating = [1,2,3,4]
//Output: 4
//
//Constraints:
//n == rating.length
//3 <= n <= 1000
//1 <= rating[i] <= 105
//All the integers in rating are unique.

package P2501_2600.P2582_Pass_the_Pillow;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n = 4;
        int time = 5;
        System.out.println(solution.passThePillow(n, time)); // Output: 2

        n = 3;
        time = 2;
        System.out.println(solution.passThePillow(n, time)); // Output: 3
    }

    public int passThePillow(int n, int time) {
        int currentPosition = 1; // The pillow starts with the first person.
        int direction = 1; // 1 means moving forward, -1 means moving backward.

        for (int t = 1; t <= time; t++) {
            currentPosition += direction;

            // If the pillow reaches the first or last person, change the direction.
            if (currentPosition == n) {
                direction = -1;
            } else if (currentPosition == 1) {
                direction = 1;
            }
        }

        return currentPosition;
    }
}

//Explanation of the Code:
//Initialization:
//currentPosition starts at 1 because the first person is initially holding the pillow.
//direction starts at 1 to indicate the initial forward passing direction.
//Loop through each second:
//In each iteration, update currentPosition by adding direction.
//If currentPosition reaches n (the last person), set direction to -1 to indicate the backward direction.
//If currentPosition reaches 1 (the first person), set direction to 1 to indicate the forward direction.
//By running this simulation loop, we can determine who holds the pillow after the specified number of seconds. This
// approach ensures that we correctly handle the direction changes at the ends of the line.


//There are n people standing in a line labeled from 1 to n. The first person in the line is holding a
// pillow initially. Every second, the person holding the pillow passes it to the next person standing in the
// line. Once the pillow reaches the end of the line, the direction changes, and people continue passing the
// pillow in the opposite direction.
//For example, once the pillow reaches the nth person they pass it to the n - 1th person, then to
// the n - 2th person and so on.
//Given the two positive integers n and time, return the index of the person holding the pillow after time seconds.
//
//Example 1:
//Input: n = 4, time = 5
//Output: 2
//Explanation: People pass the pillow in the following way: 1 -> 2 -> 3 -> 4 -> 3 -> 2.
//After five seconds, the 2nd person is holding the pillow.

//Example 2:
//Input: n = 3, time = 2
//Output: 3
//Explanation: People pass the pillow in the following way: 1 -> 2 -> 3.
//After two seconds, the 3rd person is holding the pillow.
//
//Constraints:
//2 <= n <= 1000
//1 <= time <= 1000
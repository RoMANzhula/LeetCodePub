package P1_100.P11_Container_With_Most_Water;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        int[] height1 = {1,8,6,2,5,4,8,3,7};
        System.out.println("Max area (Test 1): " + solution.maxArea(height1)); // Output: 49

        int[] height2 = {1,1};
        System.out.println("Max area (Test 2): " + solution.maxArea(height2)); // Output: 1
    }

    public int maxArea(int[] height) {
        int maxArea = 0, left = 0, right = height.length - 1;

        // two-pointer approach
        while (left < right) {
            int width = right - left;
            int currentHeight = Math.min(height[left], height[right]);
            int area = width * currentHeight;
            maxArea = Math.max(maxArea, area);

            // move the pointer pointing to the shorter line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

}

//Explanation:
//We use two pointers: left starts from the beginning, right from the end.
//At each step, we calculate the area formed between height[left] and height[right].
//We move the pointer pointing to the shorter height, because that’s the only way we can possibly find a
// taller line and increase the area.
//Complexity:
// time - O(n)
// space - O(1)


//You are given an integer array height of length n. There are n vertical lines drawn such that the two
// endpoints of the ith line are (i, 0) and (i, height[i]).
//Find two lines that together with the x-axis form a container, such that the container contains the most water.
//Return the maximum amount of water a container can store.
//Notice that you may not slant the container.

//Example 1:
//Input: height = [1,8,6,2,5,4,8,3,7]
//Output: 49
//Explanation: The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max
// area of water (blue section) the container can contain is 49.

//Example 2:
//Input: height = [1,1]
//Output: 1

//Constraints:
//n == height.length
//2 <= n <= 105
//0 <= height[i] <= 104

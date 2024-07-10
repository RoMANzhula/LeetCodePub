package P1501_1600.P1598_Crawler_Lof_Folder;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] logs1 = {"d1/", "d2/", "../", "d21/", "./"};
        System.out.println(solution.minOperations(logs1)); // Output: 2

        String[] logs2 = {"d1/", "d2/", "./", "d3/", "../", "d31/"};
        System.out.println(solution.minOperations(logs2)); // Output: 3

        String[] logs3 = {"d1/", "../", "../", "../"};
        System.out.println(solution.minOperations(logs3)); // Output: 0
    }

    public int minOperations(String[] logs) {
        int depth = 0;

        for (String log : logs) {
            if (log.equals("../")) {
                if (depth > 0) {
                    depth--;
                }
            } else if (!log.equals("./")) {
                depth++;
            }
        }

        return depth;
    }

}

//Explanation:
//logs1: The sequence of operations ends up 2 levels deep in the folder structure.
//
//Initial depth: 0
//"d1/": depth becomes 1
//"d2/": depth becomes 2
//"../": depth becomes 1
//"d21/": depth becomes 2
//"./": depth remains 2
//Thus, the minimum operations to return to the main folder is 2.
//logs2: The sequence of operations ends up 3 levels deep.
//
//Initial depth: 0
//"d1/": depth becomes 1
//"d2/": depth becomes 2
//"./": depth remains 2
//"d3/": depth becomes 3
//"../": depth becomes 2
//"d31/": depth becomes 3
//Thus, the minimum operations to return to the main folder is 3.
//logs3: The sequence of operations results in no movement from the main folder.
//
//Initial depth: 0
//"d1/": depth becomes 1
//"../": depth becomes 0
//"../": depth remains 0
//"../": depth remains 0
//Thus, the minimum operations to return to the main folder is 0.
//This solution efficiently simulates the operations and computes the required depth correctly.



//The Leetcode file system keeps a log each time some user performs a change folder operation.
//The operations are described below:
//"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in
// the same folder).
//"./" : Remain in the same folder.
//"x/" : Move to the child folder named x (This folder is guaranteed to always exist).
//You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
//The file system starts in the main folder, then the operations in logs are performed.
//Return the minimum number of operations needed to go back to the main folder after the change folder operations.
//
//Example 1:
//Input: logs = ["d1/","d2/","../","d21/","./"]
//Output: 2
//Explanation: Use this change folder operation "../" 2 times and go back to the main folder.

//Example 2:
//Input: logs = ["d1/","d2/","./","d3/","../","d31/"]
//Output: 3

//Example 3:
//Input: logs = ["d1/","../","../","../"]
//Output: 0
//
//Constraints:
//1 <= logs.length <= 103
//2 <= logs[i].length <= 10
//logs[i] contains lowercase English letters, digits, '.', and '/'.
//logs[i] follows the format described in the statement.
//Folder names consist of lowercase English letters and digits.
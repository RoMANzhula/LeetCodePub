package P1201_1300.P1233_Remove_Sub_Folders_from_the_Filesystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        String[] folder1 = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
        System.out.println(solution.removeSubfolders(folder1)); // Output: ["/a", "/c/d", "/c/f"]

        String[] folder2 = {"/a","/a/b/c","/a/b/d"};
        System.out.println(solution.removeSubfolders(folder2)); // Output: ["/a"]

        String[] folder3 = {"/a/b/c","/a/b/ca","/a/b/d"};
        System.out.println(solution.removeSubfolders(folder3)); // Output: ["/a/b/c", "/a/b/ca", "/a/b/d"]
    }

    public List<String> removeSubfolders(String[] folder) {
        // Sort folders lexicographically
        Arrays.sort(folder);

        List<String> result = new ArrayList<>();

        // Add the first folder to the result
        result.add(folder[0]);

        for (int i = 1; i < folder.length; i++) {
            String prevFolder = result.get(result.size() - 1);
            String currFolder = folder[i];

            // Check if the current folder is a sub-folder of the previous one in the result list
            if (!currFolder.startsWith(prevFolder + "/")) {
                result.add(currFolder);
            }
        }

        return result;
    }

}

//Explanation of the Code
//Sorting: Sorting the folders lexicographically arranges sub-folders right after their parent folders.
//Adding to Result: After sorting, the first folder is always added to the result.
//Sub-folder Check: For each subsequent folder, if it doesn’t start with the last folder in the result list
// followed by a /, it’s added to the result. This ensures that sub-folders are ignored.
//Complexity Analysis
//Time Complexity:
// O(nlogn) due to sorting, where n is the number of folders.
//Space Complexity:
// O(n) for storing the result.


//Given a list of folders folder, return the folders after removing all sub-folders in those folders. You
// may return the answer in any order.
//If a folder[i] is located within another folder[j], it is called a sub-folder of it. A sub-folder of
// folder[j] must start with folder[j], followed by a "/". For example, "/a/b" is a sub-folder of "/a", but
// "/b" is not a sub-folder of "/a/b/c".
//The format of a path is one or more concatenated strings of the form: '/' followed by one or
// more lowercase English letters.
//For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
//
//Example 1:
//Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
//Output: ["/a","/c/d","/c/f"]
//Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.

//Example 2:
//Input: folder = ["/a","/a/b/c","/a/b/d"]
//Output: ["/a"]
//Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".

//Example 3:
//Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
//Output: ["/a/b/c","/a/b/ca","/a/b/d"]
//
//Constraints:
//1 <= folder.length <= 4 * 104
//2 <= folder[i].length <= 100
//folder[i] contains only lowercase letters and '/'.
//folder[i] always starts with the character '/'.
//Each folder name is unique.

package P1_100.P93_Restore_IP_Addresses;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        System.out.println(solution.restoreIpAddresses("25525511135"));
        // Output: [255.255.11.135, 255.255.111.35]

        System.out.println(solution.restoreIpAddresses("0000"));
        // Output: [0.0.0.0]

        System.out.println(solution.restoreIpAddresses("101023"));
        // Output: [1.0.10.23, 1.0.102.3, 10.1.0.23, 10.10.2.3, 101.0.2.3]
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(String s, int index, List<String> current, List<String> result) {
        // if we already have 4 parts and reached the end of string -> valid IP
        if (current.size() == 4) {
            if (index == s.length()) {
                result.add(String.join(".", current));
            }

            return;
        }

        // try segment length 1 to 3
        for (int len = 1; len <= 3; len++) {
            if (index + len > s.length()) break; // out of range
            String part = s.substring(index, index + len);

            // check if valid part
            if (isValidPart(part)) {
                current.add(part);
                backtrack(s, index + len, current, result);
                current.remove(current.size() - 1); // backtrack
            }
        }
    }

    private boolean isValidPart(String part) {
        // no leading zero unless "0"
        if (part.length() > 1 && part.startsWith("0")) return false;
        // convert to int and check range
        int num = Integer.parseInt(part);

        return num >= 0 && num <= 255;
    }

}

//Complexity:
// time - O(n)
// space - O(1)


//A valid IP address consists of exactly four integers separated by single dots. Each integer is
// between 0 and 255 (inclusive) and cannot have leading zeros.
//For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and
// "192.168@1.1" are invalid IP addresses.
//Given a string s containing only digits, return all possible valid IP addresses that can be formed by
// inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the
// valid IP addresses in any order.

//Example 1:
//Input: s = "25525511135"
//Output: ["255.255.11.135","255.255.111.35"]

//Example 2:
//Input: s = "0000"
//Output: ["0.0.0.0"]

//Example 3:
//Input: s = "101023"
//Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]

//Constraints:
//1 <= s.length <= 20
//s consists of digits only.

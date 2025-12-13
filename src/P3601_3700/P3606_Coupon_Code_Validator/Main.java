package P3601_3700.P3606_Coupon_Code_Validator;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String[] code1 = {"SAVE20", "", "PHARMA5", "SAVE@20"};
        String[] businessLine1 = {"restaurant", "grocery", "pharmacy", "restaurant"};
        boolean[] isActive1 = {true, true, true, true};

        System.out.println(solution.validateCoupons(code1, businessLine1, isActive1)); // [PHARMA5, SAVE20]

        String[] code2 = {"GROCERY15", "ELECTRONICS_50", "DISCOUNT10"};
        String[] businessLine2 = {"grocery", "electronics", "invalid"};
        boolean[] isActive2 = {false, true, true};

        System.out.println(solution.validateCoupons(code2, businessLine2, isActive2)); //[ELECTRONICS_50]
    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        // fixed order of business lines
        List<String> order = Arrays.asList(
                "electronics", "grocery", "pharmacy", "restaurant"
        );

        // initialize map with empty lists for each category
        Map<String, List<String>> map = new HashMap<>();
        for (String bl : order) {
            map.put(bl, new ArrayList<>());
        }

        // regex for valid code
        String codeRegex = "^[A-Za-z0-9_]+$";

        for (int i = 0; i < code.length; i++) {
            if (!isActive[i]) continue;
            if (code[i] == null || code[i].isEmpty()) continue;
            if (!code[i].matches(codeRegex)) continue;
            if (!map.containsKey(businessLine[i])) continue;

            map.get(businessLine[i]).add(code[i]);
        }

        // build result in required order
        List<String> result = new ArrayList<>();
        for (String bl : order) {
            List<String> codes = map.get(bl);
            Collections.sort(codes);
            result.addAll(codes);
        }

        return result;
    }

}

//Complexity:
// time - O(n log n)
// space - O(n)


//You are given three arrays of length n that describe the properties of n coupons: code, businessLine, and isActive.
// The ith coupon has:
//code[i]: a string representing the coupon identifier.
//businessLine[i]: a string denoting the business category of the coupon.
//isActive[i]: a boolean indicating whether the coupon is currently active.
//A coupon is considered valid if all of the following conditions hold:
//code[i] is non-empty and consists only of alphanumeric characters (a-z, A-Z, 0-9) and underscores (_).
//businessLine[i] is one of the following four categories: "electronics", "grocery", "pharmacy", "restaurant".
//isActive[i] is true.
//Return an array of the codes of all valid coupons, sorted first by their businessLine in the order: "electronics",
// "grocery", "pharmacy", "restaurant", and then by code in lexicographical (ascending) order within each category.

//Example 1:
//Input: code = ["SAVE20","","PHARMA5","SAVE@20"], businessLine = ["restaurant","grocery","pharmacy","restaurant"],
// isActive = [true,true,true,true]
//Output: ["PHARMA5","SAVE20"]
//Explanation:
//First coupon is valid.
//Second coupon has empty code (invalid).
//Third coupon is valid.
//Fourth coupon has special character @ (invalid).

//Example 2:
//Input: code = ["GROCERY15","ELECTRONICS_50","DISCOUNT10"], businessLine = ["grocery","electronics","invalid"],
// isActive = [false,true,true]
//Output: ["ELECTRONICS_50"]
//Explanation:
//First coupon is inactive (invalid).
//Second coupon is valid.
//Third coupon has invalid business line (invalid).

//Constraints:
//n == code.length == businessLine.length == isActive.length
//1 <= n <= 100
//0 <= code[i].length, businessLine[i].length <= 100
//code[i] and businessLine[i] consist of printable ASCII characters.
//isActive[i] is either true or false.

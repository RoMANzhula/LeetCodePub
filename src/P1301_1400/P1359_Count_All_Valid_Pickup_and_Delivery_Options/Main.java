package P1301_1400.P1359_Count_All_Valid_Pickup_and_Delivery_Options;

public class Main {

    public int countOrders(int n) {
        long MODULO = 1000000007; //helps avoid overflow of the long data type
        long result = 1; //for answer (in the beginning we have one way to place the first order)

        for (int i = 2; i <= n; i++) { //i - is number of current order
            //calculate the number of ways to insert the i-th order
            long insertWays = (2L * (i - 1) + 1) * i;

            //multiply the result by the number of ways to insert the i-th order
            result = (result * insertWays) % MODULO; //(% Modulo - to prevent overcrowding)
        }

        return (int) result;
    }

    public static void main(String[] args) {
        Main solution = new Main();
        int n1 = 1;
        System.out.println(solution.countOrders(n1));  // Output: 1

        int n2 = 2;
        System.out.println(solution.countOrders(n2));  // Output: 6

        int n3 = 3;
        System.out.println(solution.countOrders(n3));  // Output: 90
    }
}

//Given n orders, each order consist in pickup and delivery services.
//Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
//Since the answer may be too large, return it modulo 10^9 + 7.

//Example 1:
//Input: n = 1
//Output: 1
//Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.

//Example 2:
//Input: n = 2
//Output: 6
//Explanation: All possible orders:
//(P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
//This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.

//Example 3:
//Input: n = 3
//Output: 90

//Constraints:
//1 <= n <= 500

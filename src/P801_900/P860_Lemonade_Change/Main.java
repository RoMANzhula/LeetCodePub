package P801_900.P860_Lemonade_Change;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int[] bills1 = {5, 5, 5, 10, 20};
        int[] bills2 = {5, 5, 10, 10, 20};

        System.out.println(solution.lemonadeChange(bills1)); // Output: true
        System.out.println(solution.lemonadeChange(bills2)); // Output: false
    }

//    public boolean lemonadeChange(int[] bills) {
//        int fiveDollar = 0;
//        int tenDollar = 0;
//
//        for (int bill : bills) {
//            if (bill == 5) {
//                fiveDollar++;  // Collect the $5 bill.
//            } else if (bill == 10) {
//                if (fiveDollar == 0) {
//                    return false;  // Can't give change.
//                }
//                fiveDollar--;  // Give change with one $5 bill.
//                tenDollar++;   // Collect the $10 bill.
//            } else { // bill == 20
//                if (tenDollar > 0 && fiveDollar > 0) {
//                    tenDollar--;  // Give change with one $10 bill.
//                    fiveDollar--; // Give change with one $5 bill.
//                } else if (fiveDollar >= 3) {
//                    fiveDollar -= 3;  // Give change with three $5 bills.
//                } else {
//                    return false;  // Can't give change.
//                }
//            }
//        }
//
//        return true;  // Successfully gave change to every customer.
//    }

    //faster solution
    public boolean lemonadeChange(int[] bills) {
        int fiveDollar = 0;
        int tenDollar = 0;

        for (int bill : bills) {
            switch (bill) {
                case 5:
                    fiveDollar++;
                    break;
                case 10:
                    if (fiveDollar == 0) {
                        return false;
                    }
                    fiveDollar--;
                    tenDollar++;
                    break;
                case 20:
                    if (tenDollar > 0 && fiveDollar > 0) {
                        tenDollar--;
                        fiveDollar--;
                    } else if (fiveDollar >= 3) {
                        fiveDollar -= 3;
                    } else {
                        return false;
                    }
                    break;
                default:
                    return false; // In case of an unexpected bill value.
            }
        }

        return true;
    }
}

//Key Points:
//Early Exit: The function returns false as soon as it detects that correct change can't be provided. This prevents
// further unnecessary iterations through the list of bills.
//Switch-Case Structure: This structure is more readable and might be slightly faster because the JVM
// optimizes switch-case statements efficiently.
//Input Validation: The switch-case structure also includes a default case to handle any unexpected bill values,
// although according to the problem constraints, this shouldn’t happen. This is more for robustness.


//At a lemonade stand, each lemonade costs $5. Customers are standing in a queue to buy from you and order one at
// a time (in the order specified by bills). Each customer will only buy one lemonade and pay with
// either a $5, $10, or $20 bill. You must provide the correct change to each customer so that the net
// transaction is that the customer pays $5.
//Note that you do not have any change in hand at first.
//Given an integer array bills where bills[i] is the bill the ith customer pays, return true if you can provide
// every customer with the correct change, or false otherwise.
//
//Example 1:
//Input: bills = [5,5,5,10,20]
//Output: true
//Explanation:
//From the first 3 customers, we collect three $5 bills in order.
//From the fourth customer, we collect a $10 bill and give back a $5.
//From the fifth customer, we give a $10 bill and a $5 bill.
//Since all customers got correct change, we output true.

//Example 2:
//Input: bills = [5,5,10,10,20]
//Output: false
//Explanation:
//From the first two customers in order, we collect two $5 bills.
//For the next two customers in order, we collect a $10 bill and give back a $5 bill.
//For the last customer, we can not give the change of $15 back because we only have two $10 bills.
//Since not every customer received the correct change, the answer is false.
//
//Constraints:
//1 <= bills.length <= 105
//bills[i] is either 5, 10, or 20.

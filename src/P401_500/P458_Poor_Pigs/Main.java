package P401_500.P458_Poor_Pigs;

public class Main {

    public static void main(String[] args) {
        System.out.println(poorPigs(4, 15, 15)); //output 2
    }

    public static int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int tests = minutesToTest / minutesToDie + 1; //number of tests you can perform

        int pigs = 0;
        int product = 1;

        while (product < buckets) {
            product *= tests;
            pigs++;
        }

        return pigs;
    }
}

//There are buckets buckets of liquid, where exactly one of the buckets is poisonous. To figure out which one is
// poisonous, you feed some number of (poor) pigs the liquid to see whether they will die or not. Unfortunately, you
// only have minutesToTest minutes to determine which bucket is poisonous.

//You can feed the pigs according to these steps:
//Choose some live pigs to feed.
//For each pig, choose which buckets to feed it. The pig will consume all the chosen buckets simultaneously and will
// take no time. Each pig can feed from any number of buckets, and each bucket can be fed from by any number of pigs.
//Wait for minutesToDie minutes. You may not feed any other pigs during this time.
//After minutesToDie minutes have passed, any pigs that have been fed the poisonous bucket will die, and all
// others will survive.
//Repeat this process until you run out of time.
//Given buckets, minutesToDie, and minutesToTest, return the minimum number of pigs needed to figure out which bucket
// is poisonous within the allotted time.

//Example 1:
//Input: buckets = 4, minutesToDie = 15, minutesToTest = 15
//Output: 2
//Explanation: We can determine the poisonous bucket as follows:
//At time 0, feed the first pig buckets 1 and 2, and feed the second pig buckets 2 and 3.
//At time 15, there are 4 possible outcomes:
//- If only the first pig dies, then bucket 1 must be poisonous.
//- If only the second pig dies, then bucket 3 must be poisonous.
//- If both pigs die, then bucket 2 must be poisonous.
//- If neither pig dies, then bucket 4 must be poisonous.

//Example 2:
//Input: buckets = 4, minutesToDie = 15, minutesToTest = 30
//Output: 2
//Explanation: We can determine the poisonous bucket as follows:
//At time 0, feed the first pig bucket 1, and feed the second pig bucket 2.
//At time 15, there are 2 possible outcomes:
//- If either pig dies, then the poisonous bucket is the one it was fed.
//- If neither pig dies, then feed the first pig bucket 3, and feed the second pig bucket 4.
//At time 30, one of the two pigs must die, and the poisonous bucket is the one it was fed.

//Constraints:
//1 <= buckets <= 1000
//1 <= minutesToDie <= minutesToTest <= 100

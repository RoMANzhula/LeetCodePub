package P2301_2400.P2391_Minimum_Amount_of_Time_to_Collect_Garbage;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] garbage1 = {"G", "P", "GP", "GG"};
        int[] travel1 = {2, 4, 3};
        System.out.println(minTimeToCollectGarbage(garbage1, travel1)); // Output: 21

        String[] garbage2 = {"MMM", "PGM", "GP"};
        int[] travel2 = {3, 10};
        System.out.println(minTimeToCollectGarbage(garbage2, travel2)); // Output: 37
    }

    public static int minTimeToCollectGarbage(String[] garbage, int[] travel) {
        int n = garbage.length;
        int[] prefixTravel = new int[n];

        for (int i = 1; i < n; i++) {
            prefixTravel[i] = prefixTravel[i - 1] + travel[i - 1];
        }

        Map<Character, Integer> garbageCount = new HashMap<>();
        Map<Character, Integer> garbageLast = new HashMap<>();

        for (int i = 0; i < garbage.length; i++) {
            String str = garbage[i];

            for (int j = 0; j < str.length(); j++) {
                char type = str.charAt(j);

                garbageCount.put(type, garbageCount.getOrDefault(type, 0) + 1);
                garbageLast.put(type, i);
            }
        }

        return prefixTravel[garbageLast.getOrDefault('P', 0)] +
                prefixTravel[garbageLast.getOrDefault('M', 0)] +
                prefixTravel[garbageLast.getOrDefault('G', 0)] +
                garbageCount.getOrDefault('M', 0) +
                garbageCount.getOrDefault('P', 0) +
                garbageCount.getOrDefault('G', 0);
    }

    //more speed
    //    public int garbageCollection(String[] garbage, int[] travel) {
    //    int n = garbage.length;
    //    int[] prefixTravel = new int[n];
    //
    //    for (int i = 1; i < n; i++) {
    //        prefixTravel[i] = prefixTravel[i - 1] + travel[i - 1];
    //    }
    //
    //    int mCount = 0, pCount = 0, gCount = 0;
    //    int mLast = 0, pLast = 0, gLast = 0;
    //
    //    for (int i = 0; i < garbage.length; i++) {
    //        String str = garbage[i];
    //
    //        for (int j = 0; j < str.length(); j++) {
    //            char type = str.charAt(j);
    //
    //            switch (type) {
    //                case 'M':
    //                    mCount++;
    //                    mLast = i;
    //                    break;
    //                case 'P':
    //                    pCount++;
    //                    pLast = i;
    //                    break;
    //                case 'G':
    //                    gCount++;
    //                    gLast = i;
    //                    break;
    //            }
    //        }
    //    }
    //
    //    int totalTime = prefixTravel[pLast] + prefixTravel[mLast] + prefixTravel[gLast] +
    //            mCount + pCount + gCount;
    //
    //    return totalTime;
    //    }

}

//You are given a 0-indexed array of strings garbage where garbage[i] represents the assortment of garbage at the
// ith house. garbage[i] consists only of the characters 'M', 'P' and 'G' representing one unit of metal, paper
// and glass garbage respectively. Picking up one unit of any type of garbage takes 1 minute.
//You are also given a 0-indexed integer array travel where travel[i] is the number of minutes needed to go from
// house i to house i + 1.
//There are three garbage trucks in the city, each responsible for picking up one type of garbage. Each garbage
// truck starts at house 0 and must visit each house in order; however, they do not need to visit every house.
//Only one garbage truck may be used at any given moment. While one truck is driving or picking up garbage, the
// other two trucks cannot do anything.
//Return the minimum number of minutes needed to pick up all the garbage.

//Example 1:
//Input: garbage = ["G","P","GP","GG"], travel = [2,4,3]
//Output: 21
//Explanation:
//The paper garbage truck:
//1. Travels from house 0 to house 1
//2. Collects the paper garbage at house 1
//3. Travels from house 1 to house 2
//4. Collects the paper garbage at house 2
//Altogether, it takes 8 minutes to pick up all the paper garbage.
//The glass garbage truck:
//1. Collects the glass garbage at house 0
//2. Travels from house 0 to house 1
//3. Travels from house 1 to house 2
//4. Collects the glass garbage at house 2
//5. Travels from house 2 to house 3
//6. Collects the glass garbage at house 3
//Altogether, it takes 13 minutes to pick up all the glass garbage.
//Since there is no metal garbage, we do not need to consider the metal garbage truck.
//Therefore, it takes a total of 8 + 13 = 21 minutes to collect all the garbage.

//Example 2:
//Input: garbage = ["MMM","PGM","GP"], travel = [3,10]
//Output: 37
//Explanation:
//The metal garbage truck takes 7 minutes to pick up all the metal garbage.
//The paper garbage truck takes 15 minutes to pick up all the paper garbage.
//The glass garbage truck takes 15 minutes to pick up all the glass garbage.
//It takes a total of 7 + 15 + 15 = 37 minutes to collect all the garbage.

//Constraints:
//2 <= garbage.length <= 105
//garbage[i] consists of only the letters 'M', 'P', and 'G'.
//1 <= garbage[i].length <= 10
//travel.length == garbage.length - 1
//1 <= travel[i] <= 100

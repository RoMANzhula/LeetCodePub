package P401_500.P432_All_O_one_Data_Structure;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        solution.inc("hello");
        solution.inc("hello");
        System.out.println(solution.getMaxKey()); // Output: "hello"
        System.out.println(solution.getMinKey()); // Output: "hello"
        solution.inc("leet");
        System.out.println(solution.getMaxKey()); // Output: "hello"
        System.out.println(solution.getMinKey()); // Output: "leet"
    }


    private Map<String, Integer> countMap; // Stores the count of each key
    private TreeMap<Integer, LinkedHashSet<String>> frequencyMap; // Stores sets of keys for each count
    private int minCount;
    private int maxCount;

    public Main() {
        countMap = new HashMap<>();
        frequencyMap = new TreeMap<>();
        minCount = Integer.MAX_VALUE;
        maxCount = 0;
    }

    public void inc(String key) {
        int count = countMap.getOrDefault(key, 0);
        // Remove the key from its current count's set
        if (count > 0) {
            frequencyMap.get(count).remove(key);
            if (frequencyMap.get(count).isEmpty()) {
                frequencyMap.remove(count);
                if (count == minCount) {
                    minCount = count + 1;
                }
            }
        }
        // Increment the count and update maps
        countMap.put(key, count + 1);
        frequencyMap.computeIfAbsent(count + 1, k -> new LinkedHashSet<>()).add(key);

        // Update max and min count
        if (count + 1 > maxCount) {
            maxCount = count + 1;
        }
        if (minCount == Integer.MAX_VALUE || count + 1 < minCount) {
            minCount = count + 1;
        }
    }

    public void dec(String key) {
        int count = countMap.get(key);
        // Remove the key from its current count's set
        frequencyMap.get(count).remove(key);
        if (frequencyMap.get(count).isEmpty()) {
            frequencyMap.remove(count);
            if (count == minCount) {
                minCount = count - 1;
            }
        }
        if (count == maxCount && frequencyMap.get(count).isEmpty()) {
            maxCount--;
        }
        // If count becomes zero, remove the key
        if (count == 1) {
            countMap.remove(key);
        } else {
            countMap.put(key, count - 1);
            frequencyMap.computeIfAbsent(count - 1, k -> new LinkedHashSet<>()).add(key);
        }
        // Update the minimum count
        if (countMap.isEmpty()) {
            minCount = Integer.MAX_VALUE;
            maxCount = 0;
        } else if (frequencyMap.containsKey(minCount) && frequencyMap.get(minCount).isEmpty()) {
            minCount = frequencyMap.firstKey();
        }
    }

    public String getMaxKey() {
        if (countMap.isEmpty()) return "";
        return frequencyMap.get(maxCount).iterator().next();
    }

    public String getMinKey() {
        if (countMap.isEmpty()) return "";
        return frequencyMap.get(minCount).iterator().next();
    }

}



//Design a data structure to store the strings' count with the ability to return the strings with minimum and
// maximum counts.
//Implement the AllOne class:
//AllOne() Initializes the object of the data structure.
//inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure,
// insert it with count 1.
//dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement,
// remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
//getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
//getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
//Note that each function must run in O(1) average time complexity.
//
//Example 1:
//Input
//["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
//[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//Output
//[null, null, null, "hello", "hello", null, "hello", "leet"]

//Explanation
//AllOne allOne = new AllOne();
//allOne.inc("hello");
//allOne.inc("hello");
//allOne.getMaxKey(); // return "hello"
//allOne.getMinKey(); // return "hello"
//allOne.inc("leet");
//allOne.getMaxKey(); // return "hello"
//allOne.getMinKey(); // return "leet"
//
//Constraints:
//1 <= key.length <= 10
//key consists of lowercase English letters.
//It is guaranteed that for each call to dec, key is existing in the data structure.
//At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.
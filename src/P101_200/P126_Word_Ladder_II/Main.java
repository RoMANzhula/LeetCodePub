package P101_200.P126_Word_Ladder_II;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        List<List<String>> result = solution.findLadders(beginWord, endWord, wordList);

        for (List<String> path : result) {
            System.out.println(path);
        }
        //Output:
        //[hit, hot, dot, dog, cog]
        //[hit, hot, lot, log, cog]
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) return result;

        Map<String, List<String>> parents = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        level.put(beginWord, 0);

        int wordLen = beginWord.length();
        int minLevel = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            String word = queue.poll();
            int curLevel = level.get(word);

            if (curLevel >= minLevel) break;

            char[] arr = word.toCharArray();

            for (int i = 0; i < wordLen; i++) {
                char original = arr[i];

                for (char c = 'a'; c <= 'z'; c++) {
                    arr[i] = c;
                    String next = new String(arr);

                    if (!dict.contains(next)) continue;

                    if (!level.containsKey(next)) {
                        level.put(next, curLevel + 1);
                        queue.offer(next);
                    }

                    if (level.get(next) == curLevel + 1) {
                        parents.computeIfAbsent(next, k -> new ArrayList<>()).add(word);
                    }

                    if (next.equals(endWord)) {
                        minLevel = curLevel + 1;
                    }
                }

                arr[i] = original;
            }
        }

        if (!parents.containsKey(endWord)) return result;

        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, beginWord, parents, path, result);

        return result;
    }

    private void dfs(String word, String beginWord, Map<String, List<String>> parents,
                     List<String> path, List<List<String>> result) {

        if (word.equals(beginWord)) {
            List<String> valid = new ArrayList<>(path);
            Collections.reverse(valid);
            result.add(valid);
            return;
        }

        if (!parents.containsKey(word)) return;

        for (String parent : parents.get(word)) {
            path.add(parent);
            dfs(parent, beginWord, parents, path, result);
            path.remove(path.size() - 1);
        }
    }

}

//Complexity:
// time - O(N * L * 26 + P * K)
// space - O(N^2 + P * K)
//N = number of words
//L = word length
//P = number of shortest paths
//K = length of each path

//A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words
// beginWord -> s1 -> s2 -> ... -> sk such that:
//Every adjacent pair of words differs by a single letter.
//Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
//sk == endWord
//Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation
// sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be
// returned as a list of the words [beginWord, s1, s2, ..., sk].

//Example 1:
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
//Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
//Explanation: There are 2 shortest transformation sequences:
//"hit" -> "hot" -> "dot" -> "dog" -> "cog"
//"hit" -> "hot" -> "lot" -> "log" -> "cog"

//Example 2:
//Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
//Output: []
//Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

//Constraints:
//1 <= beginWord.length <= 5
//endWord.length == beginWord.length
//1 <= wordList.length <= 500
//wordList[i].length == beginWord.length
//beginWord, endWord, and wordList[i] consist of lowercase English letters.
//beginWord != endWord
//All the words in wordList are unique.
//The sum of all shortest transformation sequences does not exceed 105.

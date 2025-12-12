package P3401_3500.P3433_Count_Mentions_Per_User;


import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        List<List<String>> events = new ArrayList<>();
        events.add(Arrays.asList("MESSAGE","10","id1 id0"));
        events.add(Arrays.asList("OFFLINE","11","0"));
        events.add(Arrays.asList("MESSAGE","71","HERE"));
        System.out.println(Arrays.toString(solution.countMentions(2, events))); // [2,2]

        List<List<String>> events2 = new ArrayList<>();
        events.add(Arrays.asList("MESSAGE", "2", "HERE"));
        events.add(Arrays.asList("OFFLINE", "2", "1"));
        events.add(Arrays.asList("OFFLINE", "1", "0"));
        events.add(Arrays.asList("MESSAGE", "61", "HERE"));

        System.out.println(Arrays.toString(solution.countMentions(3, events2))); // [1, 0, 2]
    }

    static class OfflineUser {
        int returnTimestamp;
        int userId;

        OfflineUser(int returnTimestamp, int userId) {
            this.returnTimestamp = returnTimestamp;
            this.userId = userId;
        }
    }

    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int[] ans = new int[numberOfUsers];
        boolean[] online = new boolean[numberOfUsers];
        Arrays.fill(online, true);

        // min-heap by return timestamp
        PriorityQueue<OfflineUser> pq = new PriorityQueue<>(
                Comparator.comparingInt(u -> u.returnTimestamp)
        );

        int allMentionsCount = 0;

        // sort events by (timestamp ASC, eventType DESC)
        events.sort((a, b) -> {
            int tsA = Integer.parseInt(a.get(1));
            int tsB = Integer.parseInt(b.get(1));
            if (tsA != tsB) return Integer.compare(tsA, tsB);

            // eventType: "MESSAGE" or "OFFLINE"
            // Compare first char: 'M'=77, 'O'=79 -> we want O before M
            char tA = a.get(0).charAt(0);
            char tB = b.get(0).charAt(0);
            return Character.compare(tB, tA); // descending
        });

        // process events
        for (List<String> event : events) {
            String type = event.get(0);
            int timestamp = Integer.parseInt(event.get(1));

            // bbring users back online
            while (!pq.isEmpty() && pq.peek().returnTimestamp <= timestamp) {
                OfflineUser u = pq.poll();
                online[u.userId] = true;
            }

            if (type.equals("MESSAGE")) {
                String msg = event.get(2);

                if (msg.equals("ALL")) {
                    allMentionsCount++;
                } else if (msg.equals("HERE")) {
                    for (int i = 0; i < numberOfUsers; i++) {
                        if (online[i]) ans[i]++;
                    }
                } else {
                    // explicit id mentions
                    for (int userId : getUserIds(msg)) {
                        ans[userId]++;
                    }
                }

            } else { // offline
                int userId = Integer.parseInt(event.get(2));
                online[userId] = false;
                pq.add(new OfflineUser(timestamp + 60, userId));
            }
        }

        // add "ALL" mentions to all users
        for (int i = 0; i < numberOfUsers; i++) {
            ans[i] += allMentionsCount;
        }

        return ans;
    }

    private List<Integer> getUserIds(String s) {
        List<Integer> ids = new ArrayList<>();
        String[] parts = s.split(" ");
        for (String p : parts) {
            ids.add(Integer.parseInt(p.substring(2))); // remove "id"
        }
        return ids;
    }

}

//Complexity:
// time - O(E log E + U log U + E × (U + K))
// space - O(U + K)


//You are given an integer numberOfUsers representing the total number of users and an array events of size n x 3.
//Each events[i] can be either of the following two types:
//Message Event: ["MESSAGE", "timestampi", "mentions_stringi"]
//This event indicates that a set of users was mentioned in a message at timestampi.
//The mentions_stringi string can contain one of the following tokens:
//id<number>: where <number> is an integer in range [0,numberOfUsers - 1]. There can be multiple ids separated by a
// single whitespace and may contain duplicates. This can mention even the offline users.
//ALL: mentions all users.
//HERE: mentions all online users.
//Offline Event: ["OFFLINE", "timestampi", "idi"]
//This event indicates that the user idi had become offline at timestampi for 60 time units. The user will
// automatically be online again at time timestampi + 60.
//Return an array mentions where mentions[i] represents the number of mentions the user with id i has across all
// MESSAGE events.
//
//All users are initially online, and if a user goes offline or comes back online, their status change is processed
// before handling any message event that occurs at the same timestamp.
//Note that a user can be mentioned multiple times in a single message event, and each mention should be counted
// separately.

//Example 1:
//Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","71","HERE"]]
//Output: [2,2]
//Explanation:
//Initially, all users are online.
//At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]
//At timestamp 11, id0 goes offline.
//At timestamp 71, id0 comes back online and "HERE" is mentioned. mentions = [2,2]

//Example 2:
//Input: numberOfUsers = 2, events = [["MESSAGE","10","id1 id0"],["OFFLINE","11","0"],["MESSAGE","12","ALL"]]
//Output: [2,2]
//Explanation:
//Initially, all users are online.
//At timestamp 10, id1 and id0 are mentioned. mentions = [1,1]
//At timestamp 11, id0 goes offline.
//At timestamp 12, "ALL" is mentioned. This includes offline users, so both id0 and id1 are mentioned. mentions = [2,2]

//Example 3:
//Input: numberOfUsers = 2, events = [["OFFLINE","10","0"],["MESSAGE","12","HERE"]]
//Output: [0,1]
//Explanation:
//Initially, all users are online.
//At timestamp 10, id0 goes offline.
//At timestamp 12, "HERE" is mentioned. Because id0 is still offline, they will not be mentioned. mentions = [0,1]

//Constraints:
//1 <= numberOfUsers <= 100
//1 <= events.length <= 100
//events[i].length == 3
//events[i][0] will be one of MESSAGE or OFFLINE.
//1 <= int(events[i][1]) <= 105
//The number of id<number> mentions in any "MESSAGE" event is between 1 and 100.
//0 <= <number> <= numberOfUsers - 1
//It is guaranteed that the user id referenced in the OFFLINE event is online at the time the event occurs.

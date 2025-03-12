package P301_400.P332_Reconstruct_Itinerary;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main solution = new Main();

        List<List<String>> tickets1 = Arrays.asList(
                Arrays.asList("MUC", "LHR"),
                Arrays.asList("JFK", "MUC"),
                Arrays.asList("SFO", "SJC"),
                Arrays.asList("LHR", "SFO")
        );

        System.out.println(solution.findItinerary(tickets1));  // Output: [JFK, MUC, LHR, SFO, SJC]

        List<List<String>> tickets2 = Arrays.asList(
                Arrays.asList("JFK", "SFO"),
                Arrays.asList("JFK", "ATL"),
                Arrays.asList("SFO", "ATL"),
                Arrays.asList("ATL", "JFK"),
                Arrays.asList("ATL", "SFO")
        );

        System.out.println(solution.findItinerary(tickets2));  // Output: [JFK, ATL, JFK, SFO, ATL, SFO]
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, List<String>> graph = new HashMap<>(); //key-airport, value-flights to other airports

        //build a graph from the tickets
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);
            String dest = ticket.get(1);
            graph.putIfAbsent(src, new ArrayList<>());
            graph.get(src).add(dest);
        }

        //sort the destinations in lexical order
        for (List<String> destinations : graph.values()) {
            destinations.sort(String::compareTo);
        }

        List<String> itinerary = new ArrayList<>();

        dfs("JFK", graph, itinerary);

        //after recursive method we need reverse our airports (for correct view)
        Collections.reverse(itinerary);

        return itinerary; //bingo
    }

    private void dfs(String node, Map<String, List<String>> graph, List<String> itinerary) { //we implement a recursive
        // dfs function that uses depth-first search to determine the route

        List<String> destinations = graph.get(node);
        if (destinations != null) {
            while (!destinations.isEmpty()) {
                String nextDest = destinations.remove(0);
                dfs(nextDest, graph, itinerary);
            }
        }
        itinerary.add(node); //next airport add to first position in list
    }

}


//You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the
// arrival airports of one flight. Reconstruct the itinerary in order and return it.
//All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there
// are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read
// as a single string.
//For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
//You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

//Example 1:
//Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
//Output: ["JFK","MUC","LHR","SFO","SJC"]

//Example 2:
//Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
//Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
//Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in
// lexical order.

//Constraints:
//1 <= tickets.length <= 300
//tickets[i].length == 2
//fromi.length == 3
//toi.length == 3
//fromi and toi consist of uppercase English letters.
//fromi != toi

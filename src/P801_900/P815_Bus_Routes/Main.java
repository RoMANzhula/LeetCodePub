package P801_900.P815_Bus_Routes;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main busRoutes = new Main();

        int[][] routes1 = {{1, 2, 7}, {3, 6, 7}};
        System.out.println(busRoutes.numBusesToDestination(routes1, 1, 6)); // Output: 2

        int[][] routes2 = {{7, 12}, {4, 5, 15}, {6}, {15, 19}, {9, 12, 13}};
        System.out.println(busRoutes.numBusesToDestination(routes2, 15, 12)); // Output: -1
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0; //no need to take any buses if source is the same as target
        }

        Map<Integer, List<Integer>> stopToRoutes = new HashMap<>();

        //build a mapping from each bus stop to the list of bus routes that pass through it
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToRoutes.computeIfAbsent(stop, k -> new ArrayList<>()).add(i);
            }
        }

        Set<Integer> visitedStops = new HashSet<>();
        Set<Integer> visitedRoutes = new HashSet<>();

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        visitedStops.add(source);

        int numBuses = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();

                //check all bus routes passing through the current bus stop
                for (int route : stopToRoutes.getOrDefault(currentStop, new ArrayList<>())) {
                    if (visitedRoutes.contains(route)) {
                        continue;
                    }

                    visitedRoutes.add(route);

                    //check all bus stops on the current route
                    for (int nextStop : routes[route]) {
                        if (nextStop == target) {
                            return numBuses + 1;
                        }

                        if (!visitedStops.contains(nextStop)) {
                            visitedStops.add(nextStop);
                            queue.offer(nextStop);
                        }
                    }
                }
            }

            numBuses++;
        }

        return -1; //no valid route found
    }
}

// Solution approach:
// Graph construction:
// Each bus stop will be a node in the graph.
// If there is a bus that connects two stops, there will be an edge between them.
// Using BFS to find the shortest path:
// Start BFS from the source stop.
// Each level of BFS represents the number of buses taken.
// Add to the queue all stops that can be reached by taking one bus from the source.
// Continue BFS, considering all buses that can be taken from the current level.
// Stop conditions:
// If we reach the target stop, return the number of buses taken.
// If the queue is empty and we haven't reached the target, it means there is no possible route, so return -1.
// This solution uses graph construction and BFS to efficiently determine the minimum number of buses needed to
// reach the target bus stop.



//You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.
//For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the
// sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
//You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus
// stop target. You can travel between bus stops by buses only.
//Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.

//Example 1
//Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
//Output: 2
//Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

//Example 2:
//Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
//Output: -1

//Constraints:
//1 <= routes.length <= 500.
//1 <= routes[i].length <= 105
//All the values of routes[i] are unique.
//sum(routes[i].length) <= 105
//0 <= routes[i][j] < 106
//0 <= source, target < 106

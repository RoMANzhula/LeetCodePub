package P3501_3600.P3508_Implement_Router;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Main router = new Main(3);
        System.out.println(router.addPacket(1, 4, 90));  // true
        System.out.println(router.addPacket(2, 5, 90));  // true
        System.out.println(router.addPacket(1, 4, 90));  // false (duplicate)
        System.out.println(router.addPacket(3, 5, 95));  // true
        System.out.println(router.addPacket(4, 5, 105)); // true (evicts oldest)
        System.out.println(router.forwardPacket());      // [2, 5, 90]
        System.out.println(router.addPacket(5, 2, 110)); // true
        System.out.println(router.getCount(5, 100, 110)); // 1

        System.out.println("-----");

        // Example Case 2 (bigger memory)
        Main router2 = new Main(5);
        System.out.println(router2.addPacket(1, 1, 5));   // true
        System.out.println(router2.addPacket(2, 1, 6));   // true
        System.out.println(router2.getCount(1, 3, 6));    // 2
        System.out.println(router2.addPacket(2, 1, 8));   // true
        System.out.println(router2.getCount(1, 7, 8));    // 1
        System.out.println(router2.getCount(1, 1, 8));    // 3
        System.out.println(router2.addPacket(1, 2, 10));  // true
        System.out.println(router2.addPacket(1, 2, 15));  // true
        System.out.println(router2.getCount(2, 6, 15));   // 2

    }

    private final int memoryLimit;
    private final TreeSet<Packet> uniquePackets;
    private final Queue<Packet> packetQueue;
    private final Map<Integer, List<Integer>> destinationTimestamps;
    private final Map<Integer, Integer> processedPacketIndex;

    public Main(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        this.uniquePackets = new TreeSet<>();
        this.packetQueue = new LinkedList<>();
        this.destinationTimestamps = new HashMap<>();
        this.processedPacketIndex = new HashMap<>();
    }

    public boolean addPacket(int source, int destination, int timestamp) {
        Packet packet = new Packet(source, destination, timestamp);

        if (uniquePackets.contains(packet)) {
            return false;
        }

        if (packetQueue.size() == memoryLimit) {
            forwardPacket();
        }

        packetQueue.offer(packet);
        uniquePackets.add(packet);
        destinationTimestamps.computeIfAbsent(destination, k -> new ArrayList<>()).add(timestamp);

        return true;
    }

    public List<Integer> forwardPacket() {
        if (packetQueue.isEmpty()) {
            return Collections.emptyList();
        }

        Packet nextPacket = packetQueue.poll();
        uniquePackets.remove(nextPacket);
        processedPacketIndex.put(
                nextPacket.destination,
                processedPacketIndex.getOrDefault(nextPacket.destination, 0) + 1)
        ;

        return Arrays.asList(nextPacket.source, nextPacket.destination, nextPacket.timestamp);
    }

    public int getCount(int destination, int startTime, int endTime) {
        if (!destinationTimestamps.containsKey(destination)) {
            return 0;
        }

        List<Integer> timestamps = destinationTimestamps.get(destination);
        int startIndex = processedPacketIndex.getOrDefault(destination, 0);

        // Binary search equivalents in Java
        int lowerBound = lowerBound(timestamps, startIndex, timestamps.size(), startTime);
        int upperBound = upperBound(timestamps, startIndex, timestamps.size(), endTime);

        return upperBound - lowerBound;
    }

    private int lowerBound(List<Integer> list, int from, int to, int value) {
        int low = from, high = to;

        while (low < high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) < value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private int upperBound(List<Integer> list, int from, int to, int value) {
        int low = from, high = to;

        while (low < high) {
            int mid = (low + high) >>> 1;
            if (list.get(mid) <= value) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }
}

class Packet implements Comparable<Packet> {
    int source;
    int destination;
    int timestamp;

    Packet(int source, int destination, int timestamp) {
        this.source = source;
        this.destination = destination;
        this.timestamp = timestamp;
    }

    @Override
    public int compareTo(Packet other) {
        if (this.source != other.source) {
            return Integer.compare(this.source, other.source);
        }

        if (this.destination != other.destination) {
            return Integer.compare(this.destination, other.destination);
        }

        return Integer.compare(this.timestamp, other.timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Packet)) return false;

        Packet other = (Packet) obj;
        return this.source == other.source &&
                this.destination == other.destination &&
                this.timestamp == other.timestamp
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination, timestamp);
    }
}

//Complexity:
// time - O(1)
// space - O(memoryLimit + totalPackets)


//Design a data structure that can efficiently manage data packets in a network router. Each data packet
// consists of the following attributes:
//source: A unique identifier for the machine that generated the packet.
//destination: A unique identifier for the target machine.
//timestamp: The time at which the packet arrived at the router.
//Implement the Router class:
//Router(int memoryLimit): Initializes the Router object with a fixed memory limit.
//memoryLimit is the maximum number of packets the router can store at any given time.
//If adding a new packet would exceed this limit, the oldest packet must be removed to free up space.
//bool addPacket(int source, int destination, int timestamp): Adds a packet with the given attributes to the router.
//
//A packet is considered a duplicate if another packet with the same source, destination, and timestamp already
// exists in the router.
//Return true if the packet is successfully added (i.e., it is not a duplicate); otherwise return false.
//int[] forwardPacket(): Forwards the next packet in FIFO (First In First Out) order.
//Remove the packet from storage.
//Return the packet as an array [source, destination, timestamp].
//If there are no packets to forward, return an empty array.
//int getCount(int destination, int startTime, int endTime):
//Returns the number of packets currently stored in the router (i.e., not yet forwarded) that have the specified
// destination and have timestamps in the inclusive range [startTime, endTime].
//Note that queries for addPacket will be made in increasing order of timestamp.

//Example 1:
//Input:
//["Router", "addPacket", "addPacket", "addPacket", "addPacket", "addPacket", "forwardPacket", "addPacket", "getCount"]
//[[3], [1, 4, 90], [2, 5, 90], [1, 4, 90], [3, 5, 95], [4, 5, 105], [], [5, 2, 110], [5, 100, 110]]
//Output:
//[null, true, true, false, true, true, [2, 5, 90], true, 1]
//Explanation
//Router router = new Router(3); // Initialize Router with memoryLimit of 3.
//router.addPacket(1, 4, 90); // Packet is added. Return True.
//router.addPacket(2, 5, 90); // Packet is added. Return True.
//router.addPacket(1, 4, 90); // This is a duplicate packet. Return False.
//router.addPacket(3, 5, 95); // Packet is added. Return True
//router.addPacket(4, 5, 105); // Packet is added, [1, 4, 90] is removed as number of packets exceeds
// memoryLimit. Return True.
//router.forwardPacket(); // Return [2, 5, 90] and remove it from router.
//router.addPacket(5, 2, 110); // Packet is added. Return True.
//router.getCount(5, 100, 110); // The only packet with destination 5 and timestamp in the inclusive
// range [100, 110] is [4, 5, 105]. Return 1.

//Example 2:
//Input:
//["Router", "addPacket", "forwardPacket", "forwardPacket"]
//[[2], [7, 4, 90], [], []]
//Output:
//[null, true, [7, 4, 90], []]
//Explanation
//Router router = new Router(2); // Initialize Router with memoryLimit of 2.
//router.addPacket(7, 4, 90); // Return True.
//router.forwardPacket(); // Return [7, 4, 90].
//router.forwardPacket(); // There are no packets left, return [].

//Constraints:
//2 <= memoryLimit <= 105
//1 <= source, destination <= 2 * 105
//1 <= timestamp <= 109
//1 <= startTime <= endTime <= 109
//At most 105 calls will be made to addPacket, forwardPacket, and getCount methods altogether.
//queries for addPacket will be made in increasing order of timestamp.

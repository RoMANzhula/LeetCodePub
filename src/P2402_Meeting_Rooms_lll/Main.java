package P2402_Meeting_Rooms_lll;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 2;
        int[][] meetings1 = {{0,10},{1,5},{2,7},{3,4}};
        System.out.println(solution.mostBooked(n1, meetings1)); // Output: 0

        int n2 = 3;
        int[][] meetings2 = {{1,20},{2,10},{3,5},{4,9},{6,8}};
        System.out.println(solution.mostBooked(n2, meetings2)); // Output: 1
    }

    public int mostBooked(int n, int [][] meetings) {
        int[] count = new int[n];
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
        Queue<Meeting> occupied = new PriorityQueue<>((a, b) -> a.endTime == b.endTime ?
                Integer.compare(a.roomId, b.roomId) : Long.compare(a.endTime, b.endTime));
        Queue<Integer> availableRoomIds = new PriorityQueue<>();
        for (int i = 0; i < n; ++i)
            availableRoomIds.offer(i);

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            while (!occupied.isEmpty() && occupied.peek().endTime <= start)
                availableRoomIds.offer(occupied.poll().roomId);
            if (availableRoomIds.isEmpty()) {
                Meeting meetingInCycle = occupied.poll();
                ++count[meetingInCycle.roomId];
                occupied.offer(new Meeting(meetingInCycle.endTime + (end - start), meetingInCycle.roomId));
            } else {
                int roomId = availableRoomIds.poll();
                ++count[roomId];
                occupied.offer(new Meeting(end, roomId));
            }
        }

        int maxIndex = 0;
        for (int i = 0; i < n; ++i)
            if (count[i] > count[maxIndex])
                maxIndex = i;
        return maxIndex;
    }

    class Meeting {
        long endTime;
        int roomId;

        public Meeting(long endTime, int roomId) {
            this.endTime = endTime;
            this.roomId = roomId;
        }
    }
}

//У цій задачі ми маємо набір зустрічей (meetings), які мають початок та кінець відповідних інтервалів часу. Нам
// потрібно розмістити ці зустрічі в приміщеннях (кімнатах) відповідно до вказаних умов:
//Кожна зустріч має відбутися в незайнятій кімнаті з найменшим номером.
//Якщо всі кімнати зайняті, зустріч відкладається до тих пір, поки яка-небудь кімната не буде вільною.
//При вільності кімнати, зустріч з найбільш раннім початком повинна бути першою, що відбудеться.
//
//Спочатку ми сортуємо всі зустрічі за їх початковим часом.
//Ми використовуємо дві черги: одну для зберігання інформації про зайняті кімнати (occupiedRooms), а іншу для
// зберігання номерів доступних кімнат (availableRoomIds). Ми також використовуємо масив count для підрахунку
// кількості зустрічей в кожній кімнаті.
//Ми ітеруємося по відсортованому масиві зустрічей. Для кожної зустрічі ми шукаємо доступну кімнату, використовуючи
// доступні кімнати або звільнені кімнати після закінчення попередніх зустрічей.
//Якщо немає доступних кімнат, ми відкладаємо зустріч, яка має найбільший кінець серед усіх зустрічей, які
// вже відбулися.
//Якщо є доступні кімнати, ми вибираємо найменший номер кімнати і додаємо зустріч до цієї кімнати.
//На кожній ітерації ми також перевіряємо, чи зустрічі в кожній кімнаті були найчастіше. Для цього ми
// підраховуємо кількість зустрічей в кожній кімнаті і зберігаємо індекс кімнати з найбільшою кількістю зустрічей.

//You are given an integer n. There are n rooms numbered from 0 to n - 1.
//You are given a 2D integer array meetings where meetings[i] = [starti, endi] means that a meeting will be
// held during the half-closed time interval [starti, endi). All the values of starti are unique.
//Meetings are allocated to rooms in the following manner:
//Each meeting will take place in the unused room with the lowest number.
//If there are no available rooms, the meeting will be delayed until a room becomes free. The delayed
// meeting should have the same duration as the original meeting.
//When a room becomes unused, meetings that have an earlier original start time should be given the room.
//Return the number of the room that held the most meetings. If there are multiple rooms, return the room
// with the lowest number.
//A half-closed interval [a, b) is the interval between a and b including a and not including b.
//
//Example 1:
//Input: n = 2, meetings = [[0,10],[1,5],[2,7],[3,4]]
//Output: 0
//Explanation:
//- At time 0, both rooms are not being used. The first meeting starts in room 0.
//- At time 1, only room 1 is not being used. The second meeting starts in room 1.
//- At time 2, both rooms are being used. The third meeting is delayed.
//- At time 3, both rooms are being used. The fourth meeting is delayed.
//- At time 5, the meeting in room 1 finishes. The third meeting starts in room 1 for the time period [5,10).
//- At time 10, the meetings in both rooms finish. The fourth meeting starts in room 0 for the time period [10,11).
//Both rooms 0 and 1 held 2 meetings, so we return 0.

//Example 2:
//Input: n = 3, meetings = [[1,20],[2,10],[3,5],[4,9],[6,8]]
//Output: 1
//Explanation:
//- At time 1, all three rooms are not being used. The first meeting starts in room 0.
//- At time 2, rooms 1 and 2 are not being used. The second meeting starts in room 1.
//- At time 3, only room 2 is not being used. The third meeting starts in room 2.
//- At time 4, all three rooms are being used. The fourth meeting is delayed.
//- At time 5, the meeting in room 2 finishes. The fourth meeting starts in room 2 for the time period [5,10).
//- At time 6, all three rooms are being used. The fifth meeting is delayed.
//- At time 10, the meetings in rooms 1 and 2 finish. The fifth meeting starts in room 1 for the time period [10,12).
//Room 0 held 1 meeting while rooms 1 and 2 each held 2 meetings, so we return 1.
//
//Constraints:
//1 <= n <= 100
//1 <= meetings.length <= 105
//meetings[i].length == 2
//0 <= starti < endi <= 5 * 105
//All the values of starti are unique.
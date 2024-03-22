package P901_1000.P997_Find_the_Town_Judge;

public class Main {
    public static void main(String[] args) {
        Main solution = new Main();

        int n1 = 2;
        int n2 = 3;
        int n3 = 3;

        int[][] trust1 = {{1, 2}};
        int[][] trust2 = {{1, 3}, {2, 3}};
        int[][] trust3 = {{1, 3}, {2, 3}, {3, 1}};

        System.out.println(solution.findJudge(n1, trust1)); // output 2
        System.out.println(solution.findJudge(n2, trust2)); // output 3
        System.out.println(solution.findJudge(n3, trust3)); // output -1

    }

    public int findJudge(int n, int[][] trust) {
        int[] trustScores = new int[n + 1]; // index 0 is unused, trustscores[i] stores how many people trust person i
        boolean[] isTrusted = new boolean[n + 1]; // whether person i is trusted by everyone

        for (int[] pair : trust) {
            int a = pair[0];
            int b = pair[1];
            trustScores[a]--;
            trustScores[b]++;
            isTrusted[a] = true;
        }

        for (int i = 1; i <= n; i++) {
            if (trustScores[i] == n - 1 && !isTrusted[i]) {
                return i;
            }
        }

        return -1;
    }
}

//Ми маємо місто, в якому проживають люди, пронумеровані від 1 до n. Існує чутка, що одна з цих людей насправді
// є секретним суддею міста. Якщо такий суддя існує, то він відповідає двом умовам:
//Суддя не довіряє нікому.
//Кожен (крім судді) довіряє судді.
//Існує саме одна людина, яка задовольняє обидві умови.
//Задача полягає у тому, щоб знайти мітку цього судді, якщо він існує та може бути ідентифікований, і
// повернути -1 у протилежному випадку.
//Отже, нам потрібно перевірити, чи існує людина, яка не довіряє нікому і якій довіряють всі інші люди. Для
// цього ми можемо використати масиви для зберігання кількості людей, які довіряють кожній особі,
// і позначити тих, кому довіряють всі, та перевірити, чи відповідає хтось умовам.
//Це досягається за допомогою наступних кроків:
//Створюємо масиви для зберігання кількості довіри та для відстеження, хто довіряється.
//Проходимося по кожній парі в trust і оновлюємо кількість довіри та позначаємо, кому довіряється.
//Перевіряємо, чи існує людина, якій довіряють всі інші і яка не довіряє нікому.
//Відповідно до цього плану, ми здійснюємо обхід двічі, один раз для підрахунку кількості довірених людей та
// інший раз для перевірки умови 3.

//In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the
// town judge.
//If the town judge exists, then:
//The town judge trusts nobody.
//Everybody (except for the town judge) trusts the town judge.
//There is exactly one person that satisfies properties 1 and 2.
//You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the
// person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship
// does not exist.
//Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

//Example 1:
//Input: n = 2, trust = [[1,2]]
//Output: 2

//Example 2:
//Input: n = 3, trust = [[1,3],[2,3]]
//Output: 3

//Example 3:
//Input: n = 3, trust = [[1,3],[2,3],[3,1]]
//Output: -1
//
//Constraints:
//1 <= n <= 1000
//0 <= trust.length <= 104
//trust[i].length == 2
//All the pairs of trust are unique.
//ai != bi
//1 <= ai, bi <= n

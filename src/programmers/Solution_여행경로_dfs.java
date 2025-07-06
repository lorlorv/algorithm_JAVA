package programmers;

import java.util.Arrays;
import java.util.Comparator;

/*
    DFS
 */


class Solution_여행경로_dfs {
    static boolean[] visited;
    static String[] tempList;


//    public static class Ticket {
//        public String departure;
//        public static String arrival;
//        public List<String> root;
//
//        Ticket(String departure, String arrival, List<String> root) {
//            this.departure = departure;
//            this.arrival = arrival;
//            this.root = root;
//        }
//
//        String getDeparture() {
//            return this.departure;
//        }
//
//        static String getArrival() {
//            return this.arrival;
//        }
//    }

    public static void main(String[] args) {
        String[] answer = solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}});
        for (String a : answer) {
            System.out.print(a + " ");
        }
    }

    public static String[] solution(String[][] tickets) {
        String[] answer = {};
        visited = new boolean[tickets.length];
        tempList = new String[tickets.length + 1];

        Arrays.sort(tickets, Comparator.comparing(t -> t[1]));

        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                visited[i] = true;
                tempList[0] = tickets[i][0];
                tempList[1] = tickets[i][1];
                if (dfs(tickets, 2, tickets[i][1]))
                    break;


            }
        }

        answer = tempList;

        return answer;
    }

    public static boolean dfs(String[][] tickets, int depth, String arrival) {

        if (depth == tickets.length + 1) {
//            for (String s : tempList) {
//                System.out.print(s + ", ");
//            }
//            System.out.println("dfs");
            return true;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(arrival)) {
//                visited[i] = true;
                tempList[depth] = tickets[i][1];
                if (dfs(tickets, depth + 1, tickets[i][1])) {
                    return true;
                }
                visited[i] = false;
            }

        }

        return false;
    }
}

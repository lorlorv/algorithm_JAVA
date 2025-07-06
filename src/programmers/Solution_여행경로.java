package programmers;

import java.util.*;

class Ticket0 {
    public String departure;
    public String arrival;
    public List<String> root;

    Ticket0(String departure, String arrival, List<String> root) {
        this.departure = departure;
        this.arrival = arrival;
        this.root = root;
    }

    String getDeparture() {
        return this.departure;
    }

    String getArrival() {
        return this.arrival;
    }
}

class Solution_여행경로 {
    static boolean[] visited;
    static Queue<Ticket0> queue;

    public static void main(String[] args) {
        solution(new String[][]{{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}});
    }

    public static String[] solution(String[][] tickets) {
        String[] answer = {};
        visited = new boolean[tickets.length];
        queue = new LinkedList<>();


        List<Ticket0> temp = new ArrayList<>();
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals("ICN")) {
                visited[i] = true;
                temp.add(new Ticket0(tickets[i][0], tickets[i][1], new ArrayList<>(Arrays.asList(tickets[i][0], tickets[i][1]))));
            }
        }

        if (!temp.isEmpty()) {
            temp.sort(Comparator.comparing(Ticket0::getArrival));
        }

        for (Ticket0 tempItem : temp) {
            System.out.println(tempItem.departure + ", " + tempItem.arrival);
            queue.add(tempItem);
        }

        List<String> tempAnswer = bfs(tickets);

        for (String a : tempAnswer) {
            System.out.print(a + " ");
        }

        return answer;
    }

    public static List<String> bfs(String[][] tickets) {
        Ticket0 current = null;
        while (!queue.isEmpty()) {
            current = queue.poll();

            String depart = current.departure;
            String arr = current.arrival;
            List<String> root = current.root;

            for (String r : root) {
                System.out.println("current root : " + r);
            }
            System.out.println();

            List<Ticket0> temp = new ArrayList<>();
            Ticket0 targetTicket0 = null;


            for (int i = 0; i < tickets.length; i++) {
                if (visited[i]) continue;

                String targetDepart = tickets[i][0];
                String targetArr = tickets[i][1];
                List<String> targetRoot = new ArrayList<>(root);
                targetRoot.add(targetArr);


                for (String r : targetRoot) {
                    System.out.println("target root : " + r);
                }
                System.out.println();


                targetTicket0 = new Ticket0(targetDepart, targetArr, targetRoot);


                // current 목적지 == target 출발지
                if (targetDepart.equals(arr)) {
                    visited[i] = true;
                    System.out.println("target Depart : " + targetDepart + " target Arr : " + targetArr);

                    temp.add(targetTicket0);

                }
            }
            temp.sort(Comparator.comparing(Ticket0::getDeparture));
            queue.addAll(temp);


        }

        return current.root;

    }
}

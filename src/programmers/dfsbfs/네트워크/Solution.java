package programmers.dfsbfs.네트워크;

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 3;
        int[][] computers = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.solution(n, computers));
    }

    static int[] visited;
    static Queue<Integer> queue = new LinkedList<>();

    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new int[n];

        // 네트워크 == bfs를 시작한 횟수
        // 컴퓨터를 돌면서 visited 체크
        for (int i = 0; i < n; i++) {
            if (visited[i] == 0) {
                answer++;
                queue.add(i);
                bfs(i, computers);
            }
        }

        return answer;
    }

    public void bfs(int start, int[][] computers) {
        visited[start] = 1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // start부터 연결되어있으면 add queue
            for (int i = 0; i < computers[0].length; i++) {
                if (visited[i] == 0 && computers[current][i] == 1) {
                    visited[i] = 1;
                    queue.add(i);
                }
            }
        }
    }
}
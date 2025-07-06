package programmers;

import java.util.LinkedList;
import java.util.Queue;

class Solution_bfs {
    public static void main(String[] args) {
        int answer = solution(new int[][]{{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}});
        System.out.println(answer);
    }

    static final int[] MX = {0, 0, 1, -1};
    static final int[] MY = {1, -1, 0, 0};

    static Queue<int[]> queue;

    public static int solution(int[][] maps) {
        int answer = 0;

        queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1});
        answer = bfs(maps);
        return answer;
    }

    public static int bfs(int[][] maps) {
        while (!queue.isEmpty()){
            // 뽑기
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++){
                int x = current[0] + MX[i];
                int y = current[1] + MY[i];
                int count = current[2];

                if (x < 0 || y < 0 || x >= maps[0].length || y >= maps.length || maps[x][y] == 0 ) continue;

                // 간다
                maps[x][y] = 0;
                queue.add(new int[]{x, y, count + 1});

                if (x == maps[0].length - 1 && y == maps.length - 1) {
                    return count + 1;
                }
            }

        }

        return -1;


    }

}
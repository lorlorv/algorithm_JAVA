package programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution_bfs2 {
    public static void main(String[] args) {
        int answer = solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        System.out.println(answer);
    }

    static final int[] MX = {0, 0, 1, -1};
    static final int[] MY = {1, -1, 0, 0};

    static Queue<int[]> queue;

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        queue = new LinkedList<>();

        int count = 1;
        queue.add(new int[]{0, 0, count});
        int[] result = bfs(computers);
        System.out.println(result[0] + ", " + result[1] + ", " + result[2]);

        List<Object> temp = new ArrayList<>();
        String[] r = (String[]) temp.stream().map(String::valueOf).toArray();

        for(int i = result[0] ;  i < computers.length; i++) {
            for (int j = result[1]; j < computers[0].length; j++){
                if (computers[i][j] == 1) {
                    queue.add(new int[]{result[0], result[1], count + 1});
                    result = bfs(computers);
//                    System.out.println(result[0] + ", " + result[1] + ", " + result[2]);
//                    break;
                }
            }

        }
        return result[2];
    }

    public static int[] bfs(int[][] computers) {
        int[] current = new int[3];
        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int movedX = current[0] + MX[i];
                int movedY = current[1] + MY[i];
                int count = current[2];

                if (movedX < 0 || movedY < 0 || movedX >= computers.length || movedY >= computers[0].length || computers[movedX][movedY] == 0)
                    continue;

                // 간다
                computers[movedX][movedY] = 0;
                queue.add(new int[]{movedX, movedY, count});

            }

        }
        System.out.println(current[0] + ", " + current[1] + ", " + current[2]);

        return current;
    }

}
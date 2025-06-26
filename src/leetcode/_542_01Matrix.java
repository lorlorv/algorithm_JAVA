package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
 * Output: [[0,0,0],[0,1,0],[1,2,1]]
 */
public class _542_01Matrix {
    static int[] MX = {1, -1, 0, 0};
    static int[] MY = {0, 0, 1, -1};
    static boolean[][] visited;

    public int[][] updateMatrix(int[][] mat) {

        Queue<int[]> queue = new LinkedList<>();


        visited = new boolean[mat.length][mat[0].length];

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 0) {
                    visited[i][j] = true;
                    queue.add(new int[]{i, j});
                }

            }
        }


        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            // System.out.println("current | x : " + current[0] + " y : " + current[1]);


            for (int i = 0; i < 4; i++) {
                int mx = current[0] + MX[i];
                int my = current[1] + MY[i];

                if (mx < 0 || my < 0 || mx >= mat.length || my >= mat[0].length || visited[mx][my])
                    continue;


                visited[mx][my] = true;
                queue.add(new int[]{mx, my});
                mat[mx][my] = mat[current[0]][current[1]] + 1;
                // System.out.println("add | x : " + mx + " y : " + my);

            }
        }

        return mat;

    }

}

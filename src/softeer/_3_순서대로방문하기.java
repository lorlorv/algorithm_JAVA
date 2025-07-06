package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class P {
    int x;
    int y;

    P(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class _3_순서대로방문하기 {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static List<P> ps = new ArrayList<>();

    static final int[] MX = {1, -1, 0, 0};
    static final int[] MY = {0, 0, 1, -1};

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        map = new int[n][n];
        visited = new boolean[n][n];


        for (int i = 0; i < n; i++) {
            String[] inputMap = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(inputMap[j]);
            }
        }

        int start = 0;
        int end = 0;
        for (int i = 0; i < m; i++) {
            String[] inputTurn = br.readLine().split(" ");

            if (i == 0) {
                start = Integer.parseInt(inputTurn[0]) - 1;
                end = Integer.parseInt(inputTurn[1]) - 1;
            } else {

                int x = Integer.parseInt(inputTurn[0]) - 1;
                int y = Integer.parseInt(inputTurn[1]) - 1;
                ps.add(new P(x, y));
            }

        }

//        count++;
        visited[start][end] = true;
        dfs(start, end, 0);


        System.out.println(count);

    }

    static void dfs(int x, int y, int turn) {
        // 1. 체크인
        visited[x][y] = true;

        // 2. 목적지인가?
        if (x == ps.get(turn).x && y == ps.get(turn).y) { // 방문해야하는 지점에 도착했다면
            turn++;
            if (turn == ps.size()) {
                count++;
                visited[x][y] = false;
                return;
            }
        }

        for (int i = 0; i < 4; i++) {// 3. 연결된 곳을 순회하기
            int movedX = x + MX[i];
            int movedY = y + MY[i];

            // 4. 더 갈 수 있는가?
            if (movedX < 0 || movedX >= n || movedY < 0 || movedY >= n || map[movedX][movedY] == 1 || visited[movedX][movedY])
                continue;

            dfs(movedX, movedY, turn); // 5. 더 간다 !

        }

        // 6. 체크아웃
        visited[x][y] = false;

    }
}

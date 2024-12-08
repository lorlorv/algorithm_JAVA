package acmicpc;//  탈출

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class _3055_탈출_v2 {

    static int row;
    static int col;
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;
    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0, 0, -1, 1};

    public static class Point {
        int x;
        int y;
        int level;
        char type;

        public Point(int x, int y, int level, char type) {
            this.x = x;
            this.y = y;
            this.level = level;
            this.type = type;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 0. 첫째 줄 입력받아서 반복문 돌릴 몇 행 몇 열 알아오기
        String input = br.readLine();
        String[] inputArray = input.split(" ");
        row = Integer.parseInt(inputArray[0]);
        col = Integer.parseInt(inputArray[1]);

        // 1. 반복문 돌면서 2차원 배열에 넣기
        map = new char[row][col]; // 실제 문제
        dp = new int[row][col]; // 방문 여부

        queue = new LinkedList<>();
        Point startPoint = null;
        for (int i = 0; i < row; i++) {
            String temp = br.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = temp.charAt(j);

                if (map[i][j] == '*') {
                    queue.add(new Point(i, j, 0, '*'));
                }

                if (map[i][j] == 'S') {
                    startPoint = new Point(i, j, 0, 'S');
                }
            }
        }
        queue.add(startPoint); // 마지막에 넣기 (왜???) -> 물의 위치가 1개라는 보장이 없음

        int result = bfs();

        System.out.println(result > 0 ? result : "KAKTUS");


    }

    static int bfs() {
        while (!queue.isEmpty()) { // 더 이상 갈 곳이 없을 때까지
            Point p = queue.poll();

            for (int i = 0; i < 4; i++) { // p : (0,2) -> (-1,2), (1,2), (0,1), (0,3)
                int mx = p.x + MX[i]; // -1, 1, 0, 0
                int my = p.y + MY[i]; //  2, 2, 1, 3

                if (mx < 0 || my < 0 || mx >= row || my >= col || map[mx][my] == '*' || map[mx][my] == 'X') continue;

                // (*) 물 -> 인접한 비어있는 칸
                if (p.type == '*' && (map[mx][my] == '.' || map[mx][my] == 'S')) {
                    map[mx][my] = '*';
                    queue.add(new Point(mx, my, 0, '*'));
                }

                // (S) 고슴도치 -> 하나만
                if (p.type == 'S') {
                    if (map[mx][my] == 'D'){
                        return p.level + 1;
                    }

                    if (map[mx][my] == '.'){
                        map[mx][my] = 'S';
                        queue.add(new Point(mx, my, p.level + 1, 'S'));
                    }

                }
            }


        }

        return -1;
    }
}
package DAY01.BFS.p3055;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    //                       좌  우 위  아래
    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0, 0, -1, 1};

    static int R, C;
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;
    static boolean foundAnswer;

    static class Point{ //queue에 넣을 객체
        int x;
        int y;
        char type;

        public Point(int x, int y, char type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY01/BFS/p3055/input.txt"));
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        dp = new int[R][C];  //map이랑 크기가 같아야함
        queue = new LinkedList<>(); //queue는 자바에서 linkedList를 사용

        Point st = null;
        for (int r = 0; r < R; r++) {
            String line = sc.next();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if(map[r][c] =='*'){
                    queue.add(new Point(r, c, '*'));
                }else if(map[r][c] == 'S'){ //바로 넣으면 안됨!
                    st = new Point(r, c, 'S'); //st에 저장
                }
            }
        }

        queue.add(st); //마지막에 고슴도치 위치가 들어가게 됨

        //Q [*, S]

        while(!queue.isEmpty()){
            //1. 큐에서 꺼내옴 -> *, S, ., D (돌은 방문 조차 못하기 때문에 큐에 못들어감)
            //2. 목적지인가? -> D
            //3. 연결된 곳을 순회 -> 좌우위아래

            //  4. 갈 수 있는가? (공통) : 지도 범위
            //  4. 갈 수 있는가? (고슴도치) : '.' or D, 방문하지 않은 곳
            //  4. 갈 수 있는가? (공통) : '.'

            //      5. 체크인 (고슴도치) : dp[][] = 이동거리
            //      5. 체크인 (물) : map[][] = *
            //      6. 큐에 넣음

            Point p = queue.poll(); //1. 제일 앞에 있는 애가 나옴
            if(p.type == 'D') { //2
                System.out.print(dp[p.y][p.x]);
                foundAnswer = true;
                break;
            }

            for (int i = 0; i < 4; i++) { //3
                int ty = p.y + MY[i];
                int tx = p.x + MX[i];

                if(0 <= ty && ty < R && 0 <= tx && tx < C){//4. 공통
                    if(p.type == 'S' || p.type == '.'){ //4. 고슴도치
                        if((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0){
                            dp[ty][tx] = dp[p.y][p.x] + 1; //5
                            queue.add(new Point(ty, tx, map[ty][tx])); //6

                        }
                    }else if(p.type == '*'){ //4. 물
                        if(map[ty][tx] == '.' || map[ty][tx] == 'S'){
                            //5
                            map[ty][tx] = '*';
                            //6
                            queue.add(new Point(ty, tx, '*'));
                        }
                    }
                }
            }
        }

        if(!foundAnswer)
            System.out.print("KAKTUS");

    }
}
package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
반례

4 2
1 1 1 1
2 200 1 1
2 8 1 1
1 8 4 1
4 1
4 3
=> 226


3 1
1 100 1
1 1 1
1 100 1
2 2
=> 201
 */

class Friend {
    int x;
    int y;


    Friend(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

public class _3_함께하는효도 {
    static int mapSize;
    static int friendNum;
    static int[][] map;
    static boolean[][] visited;
    static Friend[] friends;

    static int[] MX = {1, -1, 0, 0}; // 사방면으로 움직이기 위한 위치
    static int[] MY = {0, 0, 1, -1}; // 사방면으로 움직이기 위한 위치
    static int sum = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        String[] basicInput = br.readLine().split(" ");
        mapSize = Integer.parseInt(basicInput[0]);
        friendNum = Integer.parseInt(basicInput[1]);

        map = new int[mapSize][mapSize];
        visited = new boolean[mapSize][mapSize];
        friends = new Friend[friendNum];
        // sumArray = new int[friendNum][4];

        for (int i = 0; i < mapSize; i++) {
            String[] fruitsInput = br.readLine().split(" ");
            for (int j = 0; j < mapSize; j++) {
                map[i][j] = Integer.parseInt(fruitsInput[j]);
            }
        }

        for (int i = 0; i < friendNum; i++) {
            String[] friendInput = br.readLine().split(" ");

            friends[i] = new Friend(Integer.parseInt(friendInput[0]) - 1, Integer.parseInt(friendInput[1]) - 1);
            // 배열 인덱스 체계랑 맞춤
        }

//        for (int i = 0; i < 4; i++) {
        dfs(friends[0].x, friends[0].y, 0, 0);
//        }

        System.out.println(max);


    }

    static void dfs(int x, int y, int turn, int doneFriendNum) {
        visited[x][y] = true; // 1. 체크인 하기

        if (turn == 0) {
            sum += map[x][y];
        }


//        System.out.println("turn : " + turn + "index : " + doneFriendNum + " x : " + x + " Y : " + y + " sum : " + sum);


        if (turn == 3) { // 2. 목적지인가? -> 다음 dfs 진행
            doneFriendNum++;
            if (doneFriendNum < friendNum) { // 끝까지 내려가기
                Friend nextFriend = friends[doneFriendNum];
                dfs(nextFriend.x, nextFriend.y, 0, doneFriendNum);

            } else {
                max = Math.max(sum, max);
//                System.out.println();
            }
        } else { // 3. 연결된 곳을 순회하기
            for (int i = 0; i < 4; i++) {
                // 4. 더 갈 수 있는가 ?
                int movedX = x + MX[i];
                int movedY = y + MY[i];

                if (movedX < 0 || movedX >= mapSize || movedY < 0 || movedY >= mapSize || visited[movedX][movedY]) {  // 내가 이미 먹은건 괜찮음
                    continue;
                }

                // 5. 더 간다 !
                int fruits = map[movedX][movedY];
//                System.out.println(fruits);
                sum += fruits;
//                System.out.println(sum);

                if (sum >= 1000) {
                    sum = 1000;
                }


                dfs(movedX, movedY, turn + 1, doneFriendNum);
            }

        }

        // 6. 체크아웃
        visited[x][y] = false;
        sum -= map[x][y];
    }
}



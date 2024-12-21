package acmicpc.algobasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
칸 사이의 거리 : |r1-r2| + |c1-c2|

치킨집 M개를 구했을 때 집 마다 치킨집까지의 거리가 가장 최소인 값들의 합
 */
public class _15686_치킨배달 {
    static int N;
    static int M;
    static List<Store> stores = new ArrayList<>();
    static List<Home> homes = new ArrayList<>();
    static int selectedCount = 0;
    static boolean[] selectedStore;

    static int result = Integer.MAX_VALUE;

    static class Store {
        int x;
        int y;

        Store(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Home {
        int x;
        int y;

        Home(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);


        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                int type = Integer.parseInt(st.nextToken());
                if (type == 1) { // home
                    homes.add(new Home(i, j));
                } else if (type == 2) { // store
                    stores.add(new Store(i, j));
                }
            }
        }
        selectedStore = new boolean[stores.size()];

        for (int i = 0; i < stores.size(); i++) {
            dfs(i);
        }

        System.out.println(result);

    }

    static void dfs(int index) {

        // (1) 체크인
        selectedCount++;
        selectedStore[index] = true;

        // (2) 목적지인가?
        if (selectedCount == M) {
//            System.out.println(distance());

            result = Math.min(result, distance());
        }

        // 연결된 곳 순회
        else {
            for (int i = index + 1; i < stores.size(); i++) { //조합
                dfs(i);
            }
        }

        selectedCount--;
        selectedStore[index] = false;
    }

    // 해당 스토어에 대한 모든 집들과의 거리값 중에서 가장 작은 값?
    static int distance() {
        int currentDistance;

        int sum = 0;
        for (Home h : homes) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < stores.size(); i++) {
                if (selectedStore[i]) {
                    int disX = h.x - stores.get(i).x;
                    if (disX < 0) {
                        disX = disX * -1;
                    }

                    int disY = h.y - stores.get(i).y;
                    if (disY < 0) {
                        disY = disY * -1;
                    }
                    currentDistance = disX + disY;
//                    System.out.println(h.x + ", " + h.y + " : " + currentDistance);


                    min = Math.min(min, currentDistance);

                }
            }
//            System.out.println(h.x + ", " + h.y + " : " + min);
            sum += min;
        }

        return sum;
    }
}

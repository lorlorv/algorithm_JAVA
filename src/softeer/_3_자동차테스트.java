package softeer;

import java.io.*;
import java.util.*;

public class _3_자동차테스트{
    static int carNum;
    static int testNum;
    static int[] fuels;
    static boolean[] visited;
    static int[] result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        carNum = Integer.parseInt(input[0]);
        System.out.println(carNum);
        testNum = Integer.parseInt(input[1]);
        System.out.println(testNum);

        fuels = new int[carNum];
        visited = new boolean[carNum];
        result = new int[carNum];

//        fuels = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] fuelsInput = br.readLine().split(" ");
        for (int i = 0; i < carNum; i++){
            fuels[i] = Integer.parseInt(fuelsInput[i]);
        }


        for (int i = 0; i < testNum; i++){
            int targetMidValue = Integer.parseInt(br.readLine());
            dfs(0, 0, targetMidValue, i);
            System.out.println(result[i]);

        }

    }

    static void dfs(int index, int count, int target, int order){
        // 1. 체크인 하기
        visited[index] = true;
        count++;

        if (count == 3){ // 2. 목적지인가?

            List<Integer> tempArray = new ArrayList<>();
            for(int i = 0; i < visited.length; i++){
                if (visited[i]) {
                    tempArray.add(fuels[i]);
                }
            }
            Collections.sort(tempArray);
            if (target == tempArray.get(1)){
                result[order] = result[order] + 1;
            }


        } else { // 3. 연결된 곳을 순회하기
            for (int i = index + 1; i < fuels.length; i++){
                // 4. 더 갈 수 있는가?
                if(!visited[i]) {
                    dfs (i, count, target, order); // 5. 더 간다!
                }
            }

        }

        // 6. 체크아웃
        visited[index] = false;
        count--;

    }
}


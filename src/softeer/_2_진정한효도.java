package softeer;

import java.io.*;
import java.util.*;

public class _2_진정한효도 {
    static int[][] map = new int[3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0 ;i < 3; i++){
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < 3; j++){
                map[i][j] = Integer.parseInt(row[i]);
            }
        }

        // 1. 0,0 에서 오른쪽으로 검사, 아래로 검사
        // 2. 0,1 에서 아래로 검사
        // 3. 0,2 에서 아래로 검사

        // 4. 1,0 에서 오른쪽으로 검사
        // 5. 2,0 에서 오른쪽으로 검사

        int result = 0;
        for (int i = 0 ;i < 3; i++){
            for (int j = 0; j < 3; j++){
                int sum = 0;
                if (i == 0){
                    int[] count = new int[3];
                    // 아래로 검사 (과반수로 맞추기)
                    int first = map[i][j];
                    count[first-1]++;
                    int second = map[i+1][j];
                    count[second-1]++;
                    int third = map[i+2][j];
                    count[third-1]++;

                    int max = Math.max(count[0], count[1]);
                    max = Math.max(max, count[2]);


                    sum += Math.abs(max - first);
                    sum += Math.abs(max - second);
                    sum += Math.abs(max - third);

                    result = Math.min(result, sum);

                }

                if(j == 0) { // 오른쪽 검사
                    int[] count = new int[3];
                    int first = map[i][j];
                    count[j]++;

                    int second = map[i][j+1];
                    count[j+1]++;

                    int third = map[i][j+2];
                    count[j+2]++;

                    Arrays.sort(count);
                    int max = count[2];


                    sum += Math.abs(max - first);
                    sum += Math.abs(max - second);
                    sum += Math.abs(max - third);

                    result = Math.min(result, sum);
                }

            }
        }

        System.out.println(result);

    }
}

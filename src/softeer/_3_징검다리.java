package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class _3_징검다리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int stoneNum = Integer.parseInt(br.readLine());


        String[] stoneInput = br.readLine().split(" ");
        int[] stoneArray = new int[stoneNum];

        int[] dp = new int[stoneNum]; // 징검다리로 건너온 횟수 저장
        for (int i = 0; i < stoneNum; i++) {
            stoneArray[i] = Integer.parseInt(stoneInput[i]);
            dp[i] = 1;
        }

        for (int i = 0; i < stoneNum; i++) {
            for (int j = 0; j < i; j++) {
                if (stoneArray[i] > stoneArray[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        Arrays.sort(dp);

        System.out.println(dp[dp.length - 1]);
    }
}



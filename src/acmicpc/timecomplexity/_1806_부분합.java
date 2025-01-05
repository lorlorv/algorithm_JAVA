package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

    합이 S 이상이 되는 것 중, 가장 짧은 것의 길이

    * 반례
    10 11
    1 1 1 1 1 1 1 1 1 1

    => 합을 만드는 것이 불가능하다면 0을 출력하기

 */
public class _1806_부분합 {
    static int N;
    static long S;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        S = Long.parseLong(input[1]);

        arr = new int[N];

        String[] inputNums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(inputNums[i]);
        }

        int L = 0, R = 0;
        long sum = arr[L];
        int tempResult;
        int result = Integer.MAX_VALUE;

        while (true) {
            if (sum >= S) {
                tempResult = (R - L + 1);
                result = Math.min(tempResult, result);
                sum -= arr[L];
                L++;
            } else {
                R++;
                if (R == N) {
                    break;
                }
                sum += arr[R];
            }
        }

        if (result == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }

    }
}

package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
    - 평균을 먼저 구해본 다음에 이분 탐색으로 살펴나가면 되지 않을까

    * 반례 1
    2 1
    2 2

    => answer : 1 (적어도 1미터 이상의 나무를 가져가야하기 때문, result값을 기록해놓기)

    * 반례 2
    12 2,000,000,000
    1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000 1000000000

    => answer : 833333333 (입력값이 int의 max값 (2,147,483,647) 을 넘어갈 수 있음)
 */

public class _2805_나무자르기 {
    static int N; // 나무의 수
    static int M; // 집으로 가져가려고 하는 나무 M미터
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        arr = new int[N];

        int inputMax = 0;
        String[] trees = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(trees[i]);
            inputMax = Math.max(arr[i], inputMax);
        }

        long min = 0, max = inputMax, mid, result = 0;
        long sum;

        while (true) {
            mid = (min + max) / 2;
            sum = cutTrees(mid);

            if (sum == M) {
                result = mid;
                break;
            } else if (sum > M) {
                result = mid;
                min = mid + 1;
            } else { // sum < M
                max = mid - 1;
            }

            if (min > max) { // 종료 조건
                break;
            }

        }

        System.out.println(result);
    }

    static long cutTrees(long n) {
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > n) {
                sum += arr[i] - n;
            }

        }

        return sum;
    }
}

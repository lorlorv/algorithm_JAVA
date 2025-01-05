package acmicpc.timecomplexity;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    v1 : 최악의 경우 시간 복잡도 O(N2)
    => v2 : 투포인터 알고리즘 사용하기 O(N)

    ** 반례
    3 46
    2 23 23

    -> H와 L모두 마지막까지 왔을 때 break
 */

public class _2003_수들의합2_v3 {

    static int N;
    static int M;
    static int[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        array = new int[N];

        String[] nums = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(nums[i]);
        }

        int L = 0, R = 0, sum = array[0], count = 0;

        while (true) {
            if (sum == M) {
                count++;
                sum -= array[L];
                L++;
            } else if (sum > M) {
                sum -= array[L];
                L++;
            } else{ // sum < M
                R++;
                if (R == N) {
                    break;
                }
                sum += array[R];
            }


        }

        System.out.println(count);
    }
}

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

public class _2003_수들의합2_v2 {

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

        int L = 0;
        int R = 0;
        int sum = 0;
        int count = 0;

        while (R < N) {
            sum = 0;
            if (L == R) {
                sum = array[L];
            } else {
                for (int i = L; i <= R; i++) {
                    sum += array[i];
                }
            }

            if (sum == M) {
                count++;
                L++;
            } else if (sum > M) {
                L++;
            } else {
                R++;
            }
        }

        System.out.println(count);
    }
}

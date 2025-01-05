package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    동적계획법 - 메모이제이션 사용하기
    : 이미 계산해놓은 것은 재활용한다
 */
public class _2748_피보나치수2_v2 {
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        arr = new long[N + 1];
        // 초기화
        for (int i = 0; i < N + 1; i++) {
            arr[i] = -1;
        }

        arr[0] = 0;
        arr[1] = 1;


        System.out.println(Fibo(N));

    }

    static long Fibo(int n) {
        if (arr[n] == -1) {
            arr[n] = Fibo(n - 1) + Fibo(n - 2);
        }

        return arr[n];
    }
}

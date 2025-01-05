package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2748_피보나치수2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long sum = 0;
        long[] arr = new long[N + 1];
        arr[0] = 0;
        arr[1] = 1;
        long fibo = 0;
        for (int i = 0; i <= N; i++) {
            if (i == 0) {
                fibo = arr[0];
            } else if (i == 1) {
                fibo = arr[1];
            } else {
                arr[i] = arr[i - 1] + arr[i - 2];
                fibo = arr[i];

            }

        }

        System.out.println(fibo);

    }
}

package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
투 포인터로 풀어야하니까, 배열 2개로 만들기 (p2143과 유사)
n * n * n * n 의 경우의 수 => count : long 타입

=> ❌ (ArrayIndexOutOfBounds)

https://www.acmicpc.net/board/view/141649
반례 :
6
-45 0 0 -16
-41 -27 0 0
-36 0 -37 0
-36 0 -75 -46
0 -38 -10 0
-32 -54 -6 0


 */

public class _7453_합이0인네정수 {
    static int n;
    static int[] A;
    static int[] B;
    static int[] C;
    static int[] D;

    static int[] AB;
    static int[] CD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        A = new int[n];
        B = new int[n];
        C = new int[n];
        D = new int[n];

        AB = new int[n * n];
        CD = new int[n * n];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            A[i] = Integer.parseInt(input[0]);
            B[i] = Integer.parseInt(input[1]);
            C[i] = Integer.parseInt(input[2]);
            D[i] = Integer.parseInt(input[3]);
        }

        int sum;
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum = A[i] + B[j];
                AB[index] = sum;
                index++;
            }
        }

        index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum = C[i] + D[j];
                CD[index] = sum;
                index++;
            }
        }

        Arrays.sort(AB);
        Arrays.sort(CD);

        int start = 0, end = CD.length - 1;
        int resultSum;
        long count = 0;

        while (true) {
            resultSum = AB[start] + CD[end];

            if (resultSum == 0) {

                // 옆에 똑같은 수 있는지 확인
                long countAB = 1, countCD = 1;
                while (start + 1 < AB.length && (AB[start + 1] == AB[start])) {
                    countAB++;
                    start++;
                }

                while (end - 1 >= 0 && (CD[end - 1] == CD[end])) {
                    countCD++;
                    end--;
                }

                count += countAB * countCD;

                start++;
                end--;

            } else if (resultSum < 0) {
                start++;
            } else {
                end--;
            }

            if (start > AB.length -1  || end < 0) {
                break;
            }

        }

        System.out.println(count);

    }

}

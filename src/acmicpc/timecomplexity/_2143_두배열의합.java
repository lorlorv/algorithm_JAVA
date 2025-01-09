package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
    1. A의 모든 부 배열을 찾는다 => 합까지 한 번에 계산하기
        1 3 1 2
        {1}, {1, 3}, {1, 3, 1}, {1, 3, 1, 2},
        {3}, {3, 1}, {3, 1, 2},
        {1}, {1, 2},
        {2}

        1, 4, 5, 7,
        3, 4, 6,
        1, 3,
        2

    2. B의 모든 부 배열을 찾는다
        1 3 2
        {1}, {1, 3}, {1, 3, 2},
        {3}, {3, 2},
        {2}

        1, 4, 6,
        3, 5,
        2

    3. A의 부 배열을 돌면서 T를 만족시키는 B의 부 배열들을 넣어본다

 */
public class _2143_두배열의합 {
    static int T, A, B;
    static long[] arrA, arrB;
    static List<Long> subA  = new ArrayList<>(), subB = new ArrayList<>(); // 부분합 (몇 개 일지 모르니까 List로 선언)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        A = Integer.parseInt(br.readLine());
        arrA = new long[A];
        String[] inputA = br.readLine().split(" ");
        for (int i = 0; i < A; i++) {
            arrA[i] = Integer.parseInt(inputA[i]);
        }

        B = Integer.parseInt(br.readLine());
        arrB = new long[B];
        String[] inputB = br.readLine().split(" ");
        for (int i = 0; i < B; i++) {
            arrB[i] = Integer.parseInt(inputB[i]);
        }

        for (int i = 0; i < arrA.length; i++) {
            long sum = arrA[i];
            subA.add(sum);
            for (int j = i + 1; j < arrA.length; j++) {
                sum += arrA[j];
                subA.add(sum);
            }
        }

        for (int i = 0; i < arrB.length; i++) {
            long sum = arrB[i];
            subB.add(sum);
            for (int j = i + 1; j < arrB.length; j++) {
                sum += arrB[j];
                subB.add(sum);
            }
        }

        int indexA = 0, indexB = 0;
        long count = 0;
        long sum = subA.get(indexA) + subB.get(indexB);

        while (true) {
            if (sum == T) {
                count++;

                if (indexB == 0) {
                    indexA++;
                }
            }

            if (indexB == subB.size() - 1) {
                indexA++;
                indexB = 0;
            } else {
                indexB++;
            }

            sum = subA.get(indexA) + subB.get(indexB);

            if ((indexA == subA.size() - 1) && (indexB == subB.size() - 1)) {
                break;
            }
        }



        System.out.println(count);


    }

}

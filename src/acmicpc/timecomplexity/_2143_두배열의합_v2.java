package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
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

    => 시간초과, 두 배열을 정렬한 다음 인덱스 조절해가며 조건 만족 확인 (투 포인터)

 */
public class _2143_두배열의합_v2 {
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

        Collections.sort(subA);
        Collections.sort(subB);

        int indexA = 0, indexB = subB.size() - 1;
        long count = 0;
        long sum = 0;

        while (indexA < subA.size() && indexB > -1) {
            sum = subA.get(indexA) + subB.get(indexB);

            if (sum == T) {
                // 옆에 중복 숫자있으면 같이 처리하기
                long countA = 1, countB = 1;
                while (indexA + 1 < subA.size() && subA.get(indexA + 1).equals(subA.get(indexA))) {
                    countA++;
                    indexA++;
                }
                while (indexB - 1 >= 0 && subB.get(indexB - 1).equals(subB.get(indexB))) {
                    countB++;
                    indexB--;
                }
                count += countA * countB;
                indexA++;
                indexB--;

            } else if (sum < T) {
                indexA++;
            } else{
                indexB--;
            }

        }

        System.out.println(count);
    }

}

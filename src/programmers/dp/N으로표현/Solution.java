package programmers.dp.N으로표현;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int N = 5;
        int number = 12;
        System.out.println(solution.solution(N, number));
    }

    public int solution(int N, int number) {
        int answer = 0;

        /*
         DP는 Bottom-up or Top-down
         - 처음에 들어올 수 있는 것은 왼쪽 괄호 혹은 숫자
         - 12를 만들 수 있는 경우의 수 중에서 5로만 만들 수 있는 것
         1. 1 + 11 (o)
         2. 1 + 2 + 9 (X)
         3. 1 + 2 + 3 + 6 (x)
         4. 2 + 10 (X)
         ...
         5. 60 / 5 (O)
         6. 5 + 5 + 1 + 1 (O)

         - 5를 하나씩 늘려나가보기
         1. 5 (x)
         2. 55 (x), 5+5 (x)
         3. 555(x), 5+5+5(x) ...
         4. 5555 (x), 555 + 5 (x), 55 / 5 + 1

         -> 5 * 1111, 5 * 111, 5 * 11, 5 * 1
         */

        // 1. 개수별로 가능한 숫자 넣어놓기
        List<Set<Integer>> dp = new ArrayList<>();

        // 초기화 => 사칙연산까지 한 결과를 리스트에 담기 -> 안에 있는지 봐야하니까 Set 사용
        /*
         List 0 : 0
         List 1 : 5
         List 2 : 55, (List 1 + List 1)
         List 3 : 555,
         List[2] + List[1],
         List[1] + List[2],

         List 4 : 5555,
         List[3] + List[1],
         List[2] + List[2],
         List[1] + List[3],

         */

        // 총 8개의 리스트 (ex. 55555555 보다 크면 -1)
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<Integer>());
        }

        dp.get(1).add(N);

        // 1개 넣었을 때 결과 확인 -> 1 반환
        if (dp.get(1).contains(number)) {
            return 1;
        }

        // 사칙연산하면서 가능한 경우의 수의 결과값을 리스트마다 넣기
        for (int i = 2; i <= 8; i++) {
            //1. i 만큼 연속된 수 넣기
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < i; j++) {
                sb.append(1);
            }
            dp.get(i).add(N * Integer.parseInt(sb.toString()));


            // 2. 0 ~ i - 1까지 돌기
            for (int k = 1; k < i; k++) {
                Set<Integer> left = dp.get(k);
                Set<Integer> right = dp.get(i - k);

                for (int l : left) {
                    for (int r : right) {
                        dp.get(i).add(l + r);
                        dp.get(i).add(l - r);
                        dp.get(i).add(l * r);
                        if (l != 0 && r != 0)
                            dp.get(i).add(l / r);
                    }
                }

            }

            if (dp.get(i).contains(number)) {
                // for (int a : dp.get(i)){
                // System.out.println (N + " 이 " + i + "개" + " : " + a);
                // }
                return i;
            }

        }


        return -1;
    }
}
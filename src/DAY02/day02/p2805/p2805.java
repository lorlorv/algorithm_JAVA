package DAY02.day02.p2805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2805 {
    static long[] trees;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/DAY02/day02/p2805/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken()); //나무의 수
        int M = Integer.parseInt(st.nextToken()); //집에 가져갈 나무의 길이

        trees = new long[N];

        line = br.readLine();
        st = new StringTokenizer(line);

        long max = 0;
        for (int i = 0; i < N; i++) {
            trees[i] = Long.parseLong(st.nextToken());
            max = Math.max(trees[i], max);
        }

        long s = 0, e = max, m, sum = 0, result = 0;

        //1. sum > M : 정답 후보 -> s = m + 1
        //2. sum == M : 정답 -> break
        //3. sum < M : e = m - 1

        // * sum을 어떻게 구할 것인가 -> tree의 원래 값을 가지고 있어야함 -> 시간초과!!! -> **함수로 따로 빼기**  *
        while(true){ // s < e도 가능 but while을 돌기전인지 후에 검증하는지 확인필요
            m = (s + e) / 2;
            sum = calc(m);

            if(sum > M){
                result = m;
                s = m + 1; //m을 기록하지 않았다면 s = m;
            }else if (sum == M){
                result = m;
                break;
            }else{
                e = m - 1;
            }

            if(s > e){ //종료조건
                break;
            }
        }

        System.out.print(result);

    }

    static long calc(long val){
        long result  = 0;
        for (int i = 0; i < trees.length; i++) {
            long tree = trees[i];
            if(tree > val){
                result += tree - val;
            }
        }
        return result;
    }

}

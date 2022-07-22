package DAY05.P1256;
//파스칼 삼각형 : 사전

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1256 {
    static int N, M, K;
    static int[][] dp = new int[201][201];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY05/p1256/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        query(N, M, K);

        if(K > combination(N + M, M)) {
            System.out.println("-1");
        }else {
            System.out.println(sb);
        }

    }
    public static void query(int n, int m, int k){
        //단어 끝에 도달
        if(n + m == 0){
            return;
        }
        //z만 남은 경우 -> z로 다채우기
        else if(n == 0){
            sb.append('z');
            query(n, m - 1, k);
        }
        //a만 남은 경우 -> a로 다채우기
        else if(m == 0){
            sb.append('a');
            query(n - 1, m, k);
        }
        //a, z 둘다 남은 경우 a를 고를 때 조합 수를 보고 판단 (z 기준)
        else{
            int upCount = combination(n + m - 1, m);
            if(upCount >= k){
                sb.append('a');
                query(n - 1, m, k);
            }else{
                sb.append('z');
                query(n, m - 1, k - upCount);
            }
        }
    }

    public static int combination(int n, int r){
        if(n == r || r ==0 ){
            return 1;
        }else if(dp[n][r] != 0){
            return dp[n][r]; //알고있을 때
        } else{ //모를 때 -> 재귀
            return dp[n][r] = Math.min((int)1e9, combination(n - 1, r - 1) + combination(n - 1, r)); //1e9 == 10억, k 넣어도됨
        }
    }
}

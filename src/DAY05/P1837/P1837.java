package DAY05.P1837;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P1837 {
    static int MAX = 1000000;
    static char[] P;
    static int K;
    static boolean[] isNotPrime;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY05/p1837/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = st.nextToken().toCharArray();
        K = Integer.parseInt(st.nextToken());

        isNotPrime = new boolean[MAX + 1];
        for (int i = 2; i < MAX; i++) {
            if(isNotPrime[i] == false) { //소수임!
                primes.add(i);
                for (int j = i * 2; j < MAX + 1; j += i) {
                    isNotPrime[j] = true;
                }
            }

        }

        for (int prime : primes) {
            if (prime >= K) {
                break;
            }
            if(checkIsBad(prime)){
                System.out.println("BAD " + prime);
                return;
            }
        }
        System.out.println("GOOD");

    }
    static boolean checkIsBad(int x){
        int ret = 0;

        //나누기
        for (int i = 0; i < P.length; i++) {
            ret = (ret * 10 + (P[i] - '0')) % x;
        }
        if(ret == 0){
            return true;
        }else{
            return false;
        }
    }
}

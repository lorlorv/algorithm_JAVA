package DAY04.Euclidean.P14476;
//최대 공약수 하나 빼기 : 누적 gcd

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P14476 {
    static int N;
    static int[] nums;

    static int[] LR;
    static int[] RL;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY04/Euclidean/P14476/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        LR = new int[N];
        RL = new int[N];

        LR[0] = nums[0];
        for (int i = 1; i < N; i++) {
            LR[i] = calGcd(LR[i-1], nums[i]);
        }

        RL[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0 ; i--) {
            RL[i] = calGcd(RL[i + 1], nums[i]);
        }

        int max = 0;
        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            int gcd = 0;
            //0
            if(i == 0){
                gcd = RL[1];
            }
            //N - 1;
            else if(i == N - 1){
                gcd = LR[N - 2];
            }
            //중간
            else{
                gcd = calGcd(LR[i - 1], RL[i + 1]);
            }

            if(nums[i] % gcd != 0 && gcd > max){//약수가 아니어야하며 gcd 최대값
                max = gcd;
                maxIndex = i;
            }

        }

        if(max == 0){
            System.out.println("-1");
        }else {
            System.out.print(max + " " + nums[maxIndex]);
        }


    }

    //** gcd(a, b) == gcd(b, r) == gcd(b, a % b), stop when a % b == 0 **
    static int calGcd(int a, int b){
        while(b != 0){
            int r = a % b;

            a = b;
            b = r;
        }
        return a;
    }
}

package DAY02.day02.p2003;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p2003 {
    static int N;
    static int M;
    static int[] nums;
    static int result;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY02/day02/p2003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];
        line = br.readLine();
        st = new StringTokenizer(line);
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int H = 0, L = 0, sum = nums[0];
        //* 사이 값들 어떻게 더 빠르게 더할까?*

//        while(H < N) {
//            sum = 0;
//            if(L == H) {
//                sum = nums[L];
//            }else{
//                for (int i = L; i <= H; i++) {
//                    sum += nums[i];
//                }
//            }
//
//            if(sum < M){
//                H++;
//            }else if(sum == M){
//                result++;
//                L++;
//            }else{
//                L++;
//            }
//        }

        // * 강사님 풀이 * 조건에 따라 sum값을 변경해줌
        while(true){
            //sum == M -> 답 L++
            if(sum == M){
                result++;
                sum -= nums[L++];
            }

            //sum < M -> L++
            else if(sum > M){
                sum -= nums[L++];
            }

            //sum < M -> H++
            else{
                sum += nums[++H];
            }

            if(H == N){ // -> N + 1로 한칸의 여유를 두고 0으로 둠
                break;
            }
        }

        System.out.print(result);
        
    }
}

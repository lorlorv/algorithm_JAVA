package DAY02.day02.p1806;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1806 {
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("src/DAY02/day02/p1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] nums = new int[N + 1];

        line = br.readLine();
        st = new StringTokenizer(line);
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //1. sum == S -> result++, L++
        //2. sum < S -> H++
        //3. sum > S -> L++

        int L = 0, H = 0, sum = nums[0], result = Integer.MAX_VALUE;
        while(true){
            if(sum >= S) { //1
                int temp = H - L + 1;
                result = Math.min(temp, result);
                sum -= nums[L++];
            }else{ //2
                sum += nums[++H];
            }

            if(H == N){
                break;
            }
        }

        if(result == Integer.MAX_VALUE){
            result = 0;
        }
        System.out.print(result);

    }
}

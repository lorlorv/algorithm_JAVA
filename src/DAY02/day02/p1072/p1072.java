package DAY02.day02.p1072;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class p1072 {
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY02/day02/p1072/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        long z = 100 * Y / X;

        if(z >= 99){
            System.out.println(-1); //승률 변경 불가
        }else{
            long start = 0;
            long end = X; //최댓값으로 줘도 되지만, 효율을 위해 X

            while(start < end){ //뒤바뀌면 멈추기
                long mid = (start + end) / 2;
                long newRate = (100 * (Y + mid) / (X + mid));

                //승률이 그대로인 경우
                if(newRate == z){
                    start = mid + 1;
                }
                //승률이 변한 경우
                else{
                    end = mid; //mid도 정답 후보군
                }
            }

            System.out.println(end);
        }

    }
}

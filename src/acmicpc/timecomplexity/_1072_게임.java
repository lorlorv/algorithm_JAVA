package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*

    하나씩 올려보는 거 말고, 더 효율적인 방법이 없을까
    MAX를 뭘로 지정해야하지? -> 1,000,000,000


 */
public class _1072_게임 {
    static int X, Y, Z;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        X = Integer.parseInt(input[0]);
        Y = Integer.parseInt(input[1]);
        Z = calculate(0);

        int l = 1, h = 1000000000;
        int mid;
        int result = 0;

        // 크면 mid --
        // 작으면 mid ++

        while (l <= h) {
            mid = (l + h) / 2;
            int percent = calculate(mid);

            if (percent > Z) { // 같으면 안됨
                result = mid;
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (result == 0) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }

    }

    static int calculate(int num) {
        int x = X + num;
        int y = Y + num;
        return (int) ((long) y * 100 / x); // long 사용 시 나눗셈 결과에서 정수부분만 남기 때문에 100을 먼저 곱해 정밀도 유지
    }
}

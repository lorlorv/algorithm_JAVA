package acmicpc.timecomplexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _2003_수들의합2 {

    static int N;
    static int M;
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);
        array = new int[N];

        String[] nums = br.readLine().split(" ");
        for (int i = 0; i < N; i++){
            array[i] = Integer.parseInt(nums[i]);
        }

        int count = 0;
        for (int i = 0; i < array.length; i++) {
            int sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];

                if (sum == M) {
                    count++;
                    break;
                } else if (sum > M) {
                    break;
                }
            }
        }

        System.out.println(count);
    }
}

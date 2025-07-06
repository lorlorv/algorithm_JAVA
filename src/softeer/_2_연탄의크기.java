package softeer;

import java.io.*;
import java.util.*;

public class _2_연탄의크기 {
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];


        String[] input = br.readLine().split(" ");
        for (int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(input[i]);
        }


        int max = 0;
        for (int i = 2; i <= 100; i++){
            int count = 0;
            for (int j = 0; j < arr.length; j++){
                int temp = arr[j] % i;
                if (temp == 0)
                    count++;
            }

            max = Math.max(count, max);
        }

        System.out.println(max);

    }
}

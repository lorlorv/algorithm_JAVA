package acmicpc.algobasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    최대가 되려면
    1. 많이 나오는 알파벳이 큰 숫자
    2. 큰 자릿수에 있는 알파벳이 큰 숫자

    Map 사용하지 않는 방법
    --> 배열 인덱스를 알파벳으로 설정
 */

public class _1339_단어수학_v2 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[26]; // alpha

        for (int i = 0; i < N; i++) {
           String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                arr[str.charAt(j) - 'A'] += (int) Math.pow(10, str.length() - j - 1);
            }
        }

        Arrays.sort(arr);

        int sum = 0;
        int num = 9;
        int index = 25; // alpha 끝
        while (index > 0) {
            if (arr[index] != 0) {
                sum += arr[index] * num;
            }

            num--;
            index--;
        }

        System.out.println(sum);

    }

}

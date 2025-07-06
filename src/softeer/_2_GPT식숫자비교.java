package softeer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

class Point {
    int x;
    int y;

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class _2_GPT식숫자비교 {


    // x < y
    // 어떻게 동적으로 정렬할까 ?
    // x 기준으로 우선 정렬, y 기준으로 다음 정렬

    static int N;
    static Point[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> st = new Stack<>();

        N = Integer.parseInt(br.readLine());
//        System.out.println(N);

        arr = new Point[N];


        for (int i = 0; i < N; i++) {
            String[] inputString = br.readLine().split("\\.");
//            System.out.println(Arrays.toString(inputString));
//            System.out.println(Integer.parseInt(inputString[0]));
            int x, y;
            x = Integer.parseInt(inputString[0]);
            if (inputString.length == 1) {
                y = 0;
            } else {
                y = Integer.parseInt(inputString[1]);
            }
            arr[i] = new Point(x, y);
        }

        Arrays.sort(arr, Comparator.comparingInt(Point::getX).thenComparingInt(Point::getY));

        for (int i = 0; i < N; i++) {
            if (arr[i].getY() == 0) {
                System.out.println(arr[i].getX());
            } else {
                System.out.println(arr[i].getX() + "." + arr[i].getY());
            }
        }

    }
}


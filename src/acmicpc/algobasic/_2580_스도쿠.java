package acmicpc.algobasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*  고려해야할 부분
    스도쿠 판을 규칙대로 채울 수 없는 경우의 수는 주어지지 않는다.
    스도쿠 판을 채우는 방법이 여럿인 경우는 그 중 하나만을 출력한다.


0 3 5 4 6 9 2 7 8
7 8 2 1 0 5 6 0 9
0 6 0 2 7 8 1 3 5
3 2 1 0 4 6 8 9 7
8 0 4 9 1 3 5 0 6
5 9 6 8 2 0 4 1 3
9 1 7 6 5 2 0 8 0
6 0 3 7 0 1 9 5 2
2 5 8 3 9 4 7 6 0


0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0

    1부터 하나씩 넣어보고 ( 중복 허용 ), 검증은 3가지 방법으로 실행 !
    1. 같은 행에 중복이 존재하는가?
    2. 같은 열에 중복이 존재하는가?
    3. 같은 칸에 중복이 존재하는가?


    * 아무 숫자도 넣을 수 없을 경우 돌아가서 다른 숫자를 넣어야함

 */
public class _2580_스도쿠 {
    /*
        여러 메소드에서도 사용할 수 있기 때문에 static 변수로 선언하여 접근 용이성을 높임,
        동시에 접근될 일이 없기 때문에 static으로 선언
     */
    static int[][] input = new int[9][9]; // 입력값 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // main에서만 사용하기 때문에 static 선언 X
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine(), " "); //TODO : 계속 new 로 새로운 객체를 생성해도 되는지 확인 필요
            for (int j = 0; j < 9; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);

    }

    // 백트래킹
    static void sudoku(int row, int col) {
        // col 값이 스도쿠를 넘어갔다면 다음 행으로 이동
        if (col == 9) {
            sudoku(row + 1, 0);
            return; // TODO : 꼭 해줘야 하는 이유? -> 전으로 돌아왔을 때 다음 행들 실행하지 않게끔 처리 == 다시 호출한 곳으로 이동
            // return 없으면 col이 9가 된 이후로 다음 행들을 실행하기 때문에 ArrayIndexOutOfBoundsException !
        }
        // (0) 다 채워진 경우 출력
        if (row == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(input[i][j] + " ");
                }
                System.out.println();
            }

            System.exit(0);
        }

        // (1) input[row][col] == 0 일 경우 숫자 넣어보고 검증하기
        if (input[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                // (2) 검증 성공 후 값 넣기
                if (isAvailableNumber(row, col, i)) {
                    input[row][col] = i;
                    sudoku(row, col + 1);
                }
            }
            // (1~9)까지 어떤 숫자도 가능하지 않을 때
            // 다시 돌아가서 다른 숫자를 넣어야함
            input[row][col] = 0; // 그 전 재귀로 가기 전에 현재 값 0으로 만들기 -> 또 방문해야하기 때문
            return;
        }

        // 이동
        sudoku(row, col + 1);
    }

    static boolean isAvailableNumber(int row, int col, int value) {
        // 1. 같은 행
        for (int i = 0; i < 9; i++) {
            if (input[row][i] == value) {
                return false;
            }
        }

        // 2. 같은 열
        for (int i = 0; i < 9; i++) {
            if (input[i][col] == value) {
                return false;
            }
        }

        // 3. 같은 칸
        // 같은 칸의 첫번째 : 0 or 3 or 6이 나오게끔 -> 나머지 날리고 3곱해주기
        // 0, 1, 2 -> 0
        // 3, 4, 5 -> 1
        // 6, 7, 8 -> 2

        int r = row / 3 * 3;
        int c = col / 3 * 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (input[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }


}

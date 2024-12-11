package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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

    1부터 하나씩 넣어보고 ( 중복 허용 ), 검증은 행 하나씩 정렬 후 1부터 9까지 모두 잘 있는지 확인하기 !

    --> (X) 실패 ! (X)
    1. 0이 최대 81개일 경우 9 ^ 81  --> 시간 복잡도 최악
    2. 한 행 안에서만 검사하기 때문에 정확하게 맞는 답이라고 할 수 없음
     
 */
public class _2580_스도쿠 {
    /*
        여러 메소드에서도 사용할 수 있기 때문에 static 변수로 선언하여 접근 용이성을 높임,
        동시에 접근될 일이 없기 때문에 static으로 선언
     */
    static int[][] sudoku = new int[9][9]; // 입력값 저장
    static int[][] tempSudoku = new int[9][9];
    //    static boolean[][] visited = new boolean[9][9];
//    static List<Point> pointList = new ArrayList<>();
    static int[] answerArray;
    static int selectedNum;

//    static class Point { // 비어있는 칸만 모아서 DFS 돌리기
//        int x;
//        int y;
//        int answer;
//
//        Point(int x, int y, int answer) {
//            this.x = x;
//            this.y = y;
//            this.answer = answer;
//        }
//    } // 굳이 좌표를 몰라도 될 거 같음 -> 그냥 배열로 가자 !

    public static void main(String[] args) throws IOException {
        // (1) 입력값 저장, 0일 경우 체크해야 할 'pointList' ADD
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // main에서만 사용하기 때문에 static 선언 X
        StringTokenizer st;

        int num = 0; // int형 배열 초기화를 위해 입력을 받으면서 0의 개수세기
        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine(), " "); //TODO : 계속 new 로 새로운 객체를 생성해도 되는지 확인 필요
            for (int j = 0; j < 9; j++) {
                sudoku[i][j] = Integer.parseInt(st.nextToken());
                if (sudoku[i][j] == 0) {
                    num++;
                }
            }
        }

        answerArray = new int[num];

        // (2) 체크해야 할 배열에만 1부터 하나씩 모두 넣어보기
        for (int i = 0; i < answerArray.length; i++) {
            dfs(0);
        }

    }

    // 백트래킹
    static void dfs(int count) {
        // 1. 체크인하기 (visited 필요없음, 중복 허용)

        // 2. 목적지인가? (규칙에 맞는지 확인하기)
        if (count == answerArray.length) {
            for (int k : answerArray) {
                System.out.print(k + " ");
            }
            System.out.println();
            // 2-1. 전체를 확인하기 위해서 0에 배열 순서대로 숫자 넣고 체크하기
            fillTempArray();
            if (checkAnswer()) {
                System.out.println("RESULT");
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        System.out.print(tempSudoku[i][j] + " ");
                    }
                    System.out.println();
                }
            }

//            selectedNum = 0;
            return;
        } else { // 3. 연결된 곳을 순회
            for (int k = 0; k < 9; k++) {
                answerArray[count]= k+ 1;
//                selectedNum++;
                dfs(count+1);
            }

        }

//        selectedNum--;
    }

    // 2-1. 배열에 넣어보기 (원본 배열에 영향가지 않도록 temp 배열로 깊은 복사 후 체크해보기)
    static void fillTempArray() {
        tempSudoku = sudoku.clone();

        int arrayIndex = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tempSudoku[i][j] == 0) {
                    tempSudoku[i][j] = answerArray[arrayIndex];
                    arrayIndex++;
                }
            }
        }

//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                System.out.print(tempSudoku[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        System.out.println();

    }

    // 2-2. 한 행씩 정렬해본 후 -> 1 2 3 4 5 6 7 8 9 가 모두 중복없이 존재하는 지 확인 -> True
    static boolean checkAnswer() {

        boolean isAllMatch = true;
        for (int i = 0; i < 9; i++) {
            int[] checkArray = new int[9];
            for (int j = 0; j < 9; j++) {
                checkArray[i] = tempSudoku[i][j];
            }

            Arrays.sort(checkArray);

            // 1 2 3 4 5 6 7 8 9 가 모두 중복없이 존재하는 지 확인
            if (!checkAvailable(checkArray)) {
                isAllMatch = false;
                break;
            }

        }

        return isAllMatch;
    }

    static boolean checkAvailable(int[] array) {
        boolean isMatch = true;
        for (int i = 0; i < 9; i++) {
            if (array[i] != i + 1) {
                isMatch = false;
                break;
            }
        }

        return isMatch;
    }


}

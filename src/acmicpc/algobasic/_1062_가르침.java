package acmicpc.algobasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    (X) -> 연속된 글자만 가지고 검사하게 됨
 */
public class _1062_가르침 {
    static int N; // 존재하는 단어 개수 <= 50
    static int K; // 가르친 글자 개수 <= 26
    static final String PRE = "anta";
    static final String SUF = "tica";
    static int[] learned = new int[26];
    static int[] visited = new int[26];
    static String[] wordList;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // K개를 가르쳤을 때 몇 개 단어 읽을 수 있니?
        wordList = new String[N];

        for (int i = 0; i < N; i++) {
            wordList[i] = br.readLine();

        }

        // 진짜 배울 수 있는 글자의 개수 : K - 5
        K = K - 5;
        if (K < 0) {
            System.out.println("0");
            return;
        }

        // 미리 배워두기, 아스키 코드 a == 97
        learned['a' - 97] = 1;
        learned['n' - 97] = 1;
        learned['t' - 97] = 1;
        learned['i' - 97] = 1;
        learned['c' - 97] = 1;

        // 배운 글자 개수
        int learnedNum = 0;
        // 1. 단어들을 돌면서 방문 체크하기
        int result; //읽을 수 있는 단어의 개수
        for (int i = 0; i < N; i++) {

            char[] alphaArray = wordList[i].toCharArray(); // a n t a r c t i c a

            for (char c : alphaArray) { // visited 제외하기
                if (learned[c - 97] == 0 && visited[c - 97] == 0) { // 배우기

                    learnedNum++;
                    if (learnedNum <= K) {
                        visited[c - 97] = 1; //배우기
                    }
                }

                // 다 배우고 나서 체크하기
                if (learnedNum == K) {
                    learnedNum = 0;
                    result = checkWord();

                    if (max < result) {
                        max = result;
                    }
                }
            }

        }

        System.out.println(max);
    }


    static int checkWord() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            char[] alphaArray = wordList[i].toCharArray();

            boolean allLearned = true;
            for (char c : alphaArray) {
                if (learned[c - 97] == 0 && visited[c - 97] == 0) {
                    allLearned = false;
                    break;
                }
            }

            if (allLearned) {
                count++;
            }
        }

        visited = new int[26]; // 배운 글자에 대해서 방문 초기화
        return count;
    }


}

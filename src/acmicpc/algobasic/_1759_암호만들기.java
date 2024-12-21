package acmicpc.algobasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 반례 :
 * 3 7
 * a e i m o u v
 *
 * output
 * amv
 * emv
 * imv
 *
 * answer
 * amv
 * emv
 * imv
 * mov
 * muv
 *
 */

public class _1759_암호만들기 {

    static int L; // 암호 -> L개로 이루어진 단어
    static int C; // C개의 문자 종류
    static boolean[] visited;
    static char[] word;
    static int selectedNum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        word = new char[C];
        visited = new boolean[C];


        String temp = br.readLine().replaceAll(" ", "");
        word = temp.toCharArray();

        Arrays.sort(word);


        for (int i = 0; i < C; i++) {
            dfs(i);
        }
    }

    static void dfs(int index) {
        // 1. 체크인
        visited[index] = true;
        selectedNum++;

        // 2. 목적지인가?
        int ja = 0;
        int mo = 0;
        if (selectedNum == L) {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) {
                    char c = word[i];
                    // TODO : 최소 한 개의 모음과 최소 두 개의 자음
                    if (c == 'a' || c == 'i' || c == 'o' || c == 'u' || c == 'e') {
                        mo++;
                    } else {
                        ja++;
                    }

                    result.append(word[i]);
                }
            }

            if (mo >= 1 && ja >= 2) {
                System.out.println(result);
            }



        } else { // 3. 연결된 곳을 순회
            for (int i = index + 1; i < C; i++) {
                // 4. 갈 수 있는가?
                if (!visited[i]) {
                    // 5. 더 간다
                    dfs(i);
                }
            }

        }

        //6. 체크아웃
        visited[index] = false;
        selectedNum--;
    }
}

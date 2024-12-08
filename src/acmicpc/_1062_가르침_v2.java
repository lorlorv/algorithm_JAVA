package acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _1062_가르침_v2 {
    static int N; // 존재하는 단어 개수 <= 50
    static int K; // 가르친 글자 개수 <= 26
    static boolean[] visited;
    static int selectedNum;
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
            wordList[i] = br.readLine().replaceAll("[antic]", "");
//            System.out.println(wordList[i]);
        }

        // 미리 배워두기, 아스키 코드 a == 97
        visited = new boolean[26];
        visited['a' - 97] = true;
        visited['n' - 97] = true;
        visited['t' - 97] = true;
        visited['i' - 97] = true;
        visited['c' - 97] = true;

        // 진짜 배울 수 있는 글자의 개수 : K - 5
        if (K < 5) {
            max = 0;
        } else if (K == 5) {
            max = checkWord();
        } else {
            selectedNum = 5; // 이미 5개 배웠음
            for (int i = 0; i < 26; i++) {
                if (!visited[i]) {
                    dfs(i);
                }
            }

        }

        System.out.println(max);
    }

    static void dfs(int index) {
        // (1) 체크인하기 : visited[i] == true
        visited[index] = true;
        selectedNum++;

        // (2) 목적지인가?
        if (selectedNum == K) {
            max = Math.max(max, checkWord());
        } else {
            // (3) 연결된 곳을 순회
            for (int i = index + 1; i < 26; i++) {
                // (4) 갈 수 있는가?
                if (!visited[i]) {
                    // (5) 간다!
                    dfs(i);
                }
            }
        }

        // (6) 체크아웃
        visited[index] = false;
        selectedNum--;
    }


    static int checkWord() {
        int count = 0;
        for (int i = 0; i < N; i++) {
//            char[] alphaArray = wordList[i].toCharArray(); //*** 메모리 이슈 : 배열 만들지 말고, 인덱스로 바로 접근하기 !!

            boolean allLearned = true;
            String word = wordList[i];
            for (int j = 0; j < word.length(); j++){
                if (!visited[word.charAt(j) -97]) {
                    allLearned = false;
                    break;
                }
            }

            if (allLearned) {
                count++;
            }
        }

        return count;
    }


}

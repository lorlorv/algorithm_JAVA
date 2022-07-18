package DAY01.DFS.p1062;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static int N, K;
    static String[] words;
    static boolean[] visited;
    static int selectedCount; //depth count
    static int max;


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY01/DFS/p1062/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = sc.next().replaceAll("[antic]", "");
        }

        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        if(K < 5){
            max = 0;
        }else if(K == 5){
            max = countWords();
        }else{
            selectedCount = 5;
            for (int i = 0; i < 26; i++) {
                if(visited[i] == false)
                    Dfs(i);
            }
        }

        System.out.print(max);

    }

    static void Dfs(int index) {
        //1. 체크인 : visited[index] = true
        //2. 목적지 설정 : selectedCount == K
        //3. 연결된 곳 순회 : index + 1 ~ 25
        //  4. 갈 수 있는 곳인지? : if(visited[index] = false)
        //      5. 간다 : Dfs()
        //6. 체크아웃 : visited[index] = false

        //1
        visited[index] = true;
        selectedCount++;

        //2
        if(selectedCount == K){
            max = Math.max(max, countWords());
        }else{
            for (int i = index + 1; i < 26; i++) { //3
                if(visited[i] == false){ //4
                    Dfs(i); //5
                }
            }
        }

        visited[index] = false; //6
        selectedCount--;

    }

    static int countWords(){
        int count = 0;

        for (int n = 0; n < N; n++) {
            String word = words[n];
            boolean isPossible = true;

            for (int i = 0; i < word.length(); i++) {
                if(visited[word.charAt(i) - 'a'] == false){
                    isPossible = false;
                    break;
                }
            }
            if(isPossible)
                count++;
        }

        return count;
    }

}


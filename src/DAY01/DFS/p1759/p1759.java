package DAY01.DFS.p1759;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class p1759 {
    static int L, C;
    static char[] data;
    static List<String> result;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY01/DFS/p1759/input.txt"));
        Scanner sc = new Scanner(System.in);

        L = sc.nextInt();
        C = sc.nextInt();

        data = new char[C];
        result = new LinkedList<>();

        for (int i = 0; i < C; i++) {
            data[i] = sc.next().charAt(0);
        }

        //정렬
        Arrays.sort(data);

        Dfs(0, 0, 0, -1, ""); //Dummy에서 시작

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }

    static void Dfs(int length, int ja, int mo, int current, String pwd) {
        //1. 체크인 : 생략 가능 (오름차순으로 정렬되어있기 때문 -> visited[] 필요 없음)
        //2. 목적지 설정 : length == L -> ja 개수 2, mo 개수 1인지 확인 후 암호 가능 판별
        //3. 연결된 곳 순환 : current + 1 ~ C
        //  4. 갈 수 있는지 확인 : 생략 가능 (오름차순으로 정렬되어있기 때문)
        //      5. 간다 : ja, mo case
        //6. 체크아웃 : 생략 가능 (오름차순으로 정렬되어있기 때문 -> visited[] 필요 없음)

        if(length == L){
            if(ja >= 2 && mo >= 1){
                result.add(pwd);
            }
        }else{
            for (int i = current + 1; i < C; i++) {
                if(data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u'){
                    Dfs(length + 1, ja, mo + 1, i, pwd + data[i]);
                }
                else{
                    Dfs(length + 1, ja + 1, mo, i, pwd + data[i]);
                }
            }
        }
    }

}


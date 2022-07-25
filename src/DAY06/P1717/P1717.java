package DAY06.P1717;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class P1717 {
    static int n, m;
    static int[] parent;
    
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY06/p1717/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if (type == 0) {//합집합
                int aRoot = find(Integer.parseInt(st.nextToken()));
                int bRoot = find(Integer.parseInt(st.nextToken()));
                parent[aRoot] = bRoot;
            } else { //find
                int a = find(Integer.parseInt(st.nextToken()));
                int b = find(Integer.parseInt(st.nextToken()));
                if(a == b){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }

    }
    static int find(int a){
        if(parent[a] == a){// root
            return a;
        }else{
            return parent[a] = find(parent[a]);
        }
    }
}


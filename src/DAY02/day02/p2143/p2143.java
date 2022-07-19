package DAY02.day02.p2143;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class p2143 {
    static long T;
    static int n;
    static int m;
    static long[] arrN;
    static long[] arrM;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY02/day02/p2143/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        n = Integer.parseInt(br.readLine());
        arrN = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arrN[i] = Integer.parseInt(st.nextToken());
        }

        m = Integer.parseInt(br.readLine());
        arrM = new long[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arrM[i] = Integer.parseInt(st.nextToken());
        }

        //1. 모든 부분배열 구하기
        List<Long> subA = new ArrayList<>();
        List<Long> subB = new ArrayList<>();

        //1-1. subA 구하기
        for (int i = 0; i < n; i++) {
            long sum = arrN[i];
            subA.add(sum);
            for (int j = i + 1; j < n; j++) {
                sum += arrN[j];
                subA.add(sum);
            }
        }

        //1-2. subB 구하기
        for (int i = 0; i < m; i++) {
            long sum = arrM[i];
            subB.add(sum);
            for (int j = i + 1; j < m; j++) {
                sum += arrM[j];
                subB.add(sum);
            }
        }

        //2. 정렬
        Collections.sort(subA); //오름차순
        Collections.sort(subB, Comparator.reverseOrder()); //내림차순

        //3. 투포인터

        long result = 0;
        int ptA = 0;
        int ptB = 0;
        while(true){
            long currentA = subA.get(ptA); //1, current B 는 6
            long target = T - currentA; //5-1 = 4 B에서 찾고 싶은게 4임
            //currentB == target -> subA, subB 같은 수 개수 체크 -> 답구하기, ptA+ ptB+
            if(subB.get(ptB) == target){
                //정답
                long countA = 0;
                long countB = 0;

                //동률 확인
                while(ptA < subA.size() && subA.get(ptA) == currentA) { //자기 자신
                    countA++;
                    ptA++;//같은게 옆에 더 있는지 보기
                }

                while(ptB < subB.size() && subB.get(ptB) == target) {
                    countB++;
                    ptB++;//같은게 옆에 더 있는지 보기
                }

                result += countA * countB;

            }
            //currentB > target -> ptB++
            else if (subB.get(ptB) > target){
                ptB++;
            }
            //currentB < target -> ptA++
            else {
                ptA++;
            }

            //탈출 조건 ptA와 ptB가 범위를 벗어날 때
            if(ptA == subA.size() || ptB == subB.size()){
                break;
            }
        }

        System.out.print(result);
    }
}

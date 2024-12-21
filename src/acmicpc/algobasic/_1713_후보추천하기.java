package acmicpc.algobasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    반례 :
2
6
2 1 1 2 3 3

answer : 1 3
output : 2 3


--> 이미 들어있는 사진은 turn 업데이트 X
 */

public class _1713_후보추천하기 {

    static int N; // 사진틀의 개수
    static int totalRecoNum; // 총 추천 횟수
    static Map<Integer, Info> studentMap = new HashMap<>();

    static class Info {
        int recoNum;
        int turn;

        Info(int recoNum, int turn) {
            this.recoNum = recoNum;
            this.turn = turn;
        }

        void setRecoNum(int num) {
            this.recoNum = num;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        totalRecoNum = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < totalRecoNum; i++) {
            int studentNum = Integer.parseInt(st.nextToken());
            // 후보가 이미 있다면 횟수만 증가시키기
            if (studentMap.containsKey(studentNum)) {
                int existRecoNum = studentMap.get(studentNum).recoNum;

                studentMap.get(studentNum).setRecoNum(existRecoNum + 1);
            } else { // 없다면 사진 틀에 넣기
                if (studentMap.size() < N) { // 사진틀이 비어있음
                    studentMap.put(studentNum, new Info(1, i));
                } else { // 사진틀이 비어있지 않으면 현재까지 추천 받은 횟수가 가장 적은 학생의 사진을 삭제
                    int leastStudentNum = findLeastRecoStudentNum();

                    studentMap.remove(leastStudentNum);
                    studentMap.put(studentNum, new Info(1, i));
                }

            }

//            System.out.println("Turn : " + i + " List : " + studentMap.keySet());
        }

        List<Integer> result = new ArrayList<>(studentMap.keySet());
        Collections.sort(result);

        for (int studentNum : result) {
            System.out.print(studentNum + " ");
        }
    }

    static int findLeastRecoStudentNum() {
        List<Map.Entry<Integer, Info>> entryList = new LinkedList<>(studentMap.entrySet());
        entryList.sort(Comparator.comparingInt((Map.Entry<Integer, Info> entry) -> entry.getValue().recoNum)
                .thenComparingInt(entry -> entry.getValue().turn)); // 컴파일러가 람다 표현식에서 사용되는 변수의 타입을 추론하지 못함 -> 타입 명시적으로 작성 필요

        return entryList.get(0).getKey();

    }
}

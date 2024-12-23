package acmicpc.algobasic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    최대가 되려면
    1. 많이 나오는 알파벳이 큰 숫자
    2. 큰 자릿수에 있는 알파벳이 큰 숫자

    (첫번째 시도)
    각각의 string을 비교하여 가장 긴 string의 맨 앞의 값을 9로 치환
    그 다음 값을 8 . . . .

    1. 가장 긴 string이 뭔지 찾는다.

    2
    GCF -> char[]
    ACDEB -> char[]

    --> (X)

    (두번째 시도)
    자릿수만큼 가중치를 부여해서 정렬 후 큰 값부터 9부터 대입
    
    --> (O)
 */

public class _1339_단어수학 {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        List<String> input = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            input.add(br.readLine());
        }

        Map<Character, Integer> weightMap = new HashMap<>();
        // 가중치를 부여하기
        for (String s : input) {
            for (int j = 0; j < s.length(); j++) { // 0 1 2
                char alpha = s.charAt(j);
                // 자릿수
                int weight;
                int currentLength = s.length() - j - 1;
                if (currentLength == 0) {
                    weight = 1;
                } else {
                    weight = (int) Math.pow(10, currentLength);
                }

                if (weightMap.containsKey(alpha)) {
                    weightMap.compute(alpha, (k, tempWeight) -> tempWeight + weight);
                } else {
                    weightMap.put(alpha, weight);
                }
            }
        }

//        for (Map.Entry<Character, Integer> weight : weightMap.entrySet()) {
//            System.out.println(weight.getKey()  + " : " + weight.getValue());
//        }

        // 가중치대로 정렬하기
        List<Map.Entry<Character, Integer>> mapList = new ArrayList<>(weightMap.entrySet());

        mapList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue())); // 값 기준으로 내림차순 정렬


        Queue<Integer> q = new LinkedList<>();

        for (int i = 9; i >= 0; i--) {
            q.add(i);
        }

        for (Map.Entry<Character, Integer> weight : mapList) {
//            System.out.println(weight.getKey()  + " : " + weight.getValue());

            if (q.isEmpty()) {
                weight.setValue(0);
            } else {
                weight.setValue(weight.getValue() * q.poll());

            }
        }

        int sum = 0;
        for (Map.Entry<Character, Integer> weight : mapList) {
//            System.out.println(weight.getKey() + " : " + weight.getValue());
            sum += weight.getValue();
        }

        System.out.println(sum);

    }

}

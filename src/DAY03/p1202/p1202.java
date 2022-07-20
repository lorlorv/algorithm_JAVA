package DAY03.p1202;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class p1202 {
    static PriorityQueue<Jewerly> pq;
    static Jewerly[] jewerlys;
    static int[] bags;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY03/p1202/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringTokenizer st = new StringTokenizer(line);

        int N = Integer.parseInt(st.nextToken()); //무게
        int K = Integer.parseInt(st.nextToken()); //가방 개수

        jewerlys = new Jewerly[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewerlys[i] = new Jewerly(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }

        //Bag 정렬
        Arrays.sort(bags);

        //Jewerly 무게 기준으로 정렬
        Arrays.sort(jewerlys, Comparator.comparingInt(Jewerly::getWeight));

        //보석 가격이 높은 값 기준 힙
        pq = new PriorityQueue<>(Comparator.comparingInt(Jewerly::getValue).reversed());

        int jIndex = 0;
        long result = 0;
        //1. 남은 가방 중 제일 작은 가방을 선택 <- 정렬
        for (int i = 0; i < bags.length; i++) {
            //2. 선택된 가방에 넣을 수 있는 남은 보석 중 가장 비싼 보석을 선택 <- 힙을 사용
            while(jIndex < N && jewerlys[jIndex].weight <= bags[i]){
                pq.add(jewerlys[jIndex++]);
            }
            if(!pq.isEmpty()){ //내가 선택한 가방에 넣을 수 있는 보석이 존재한다!
                                // 가방에 넣을 수 있는 보석이 없을 수도 있음 poll했을 때 null방지
                result += pq.poll().value; //제일 비싼 보석
            }
        }

        System.out.println(result);

    }
}
class Jewerly{
    int weight;
    int value;

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public Jewerly(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

}

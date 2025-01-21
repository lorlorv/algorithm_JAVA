package acmicpc.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 가방에는 최대 한 개의 보석만 넣을 수 있다 !
 */


class Item {
    int weight;
    int price;

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    Item(int w, int p) {
        this.weight = w;
        this.price = p;
    }
}

public class _1202_보석도둑 {
    static int N; // 보석 개수
    static int K; // 가방 개수
    static int C; // 가방에 담을 수 있는 최대 무게
    static Item[] arr;
    static int[] bags;
    static PriorityQueue<Item> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new Item[N];
        bags = new int[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int w = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            arr[i] = new Item(w, p);
        }
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            bags[i] = Integer.parseInt(st.nextToken());
        }

//        C = Integer.parseInt(br.readLine());

        // 1. 보석 무게를 기준으로 정렬하기
        Arrays.sort(bags);
        // [2, 10]

        // 1, 2 -> 164
        // 1, 5 -> 88
        // 2, 1 -> 164
        // 2, 5 -> 122

        Arrays.sort(arr, Comparator.comparingInt(Item::getWeight));
        // [(1,65), (2,99), (5,23)]

        pq = new PriorityQueue<>(Comparator.comparingInt(Item::getPrice).reversed());

        // 2. 어떻게 가방에 넣지? 가방의 의미란?
        // 가장 작은 가방부터 작은 무게부터 담아보기 -> 그 중 가장 비싼 보석 선택
        // -> 그 다음 가방에는 어차피 미리 담아둔 보석도 들어갈 수 있음 -> 그 중 가장 비싼 보석 선택

        long sum = 0;
        int index = 0;
        for (int bag : bags) {

            while (index < N && (bag >= arr[index].getWeight())) { // 가방에 넣을 수 있는거 담기
                pq.add(arr[index++]);
            }

            if (!pq.isEmpty()) {
                sum += pq.poll().getPrice();
            }
        }

        System.out.println(sum);

    }
}

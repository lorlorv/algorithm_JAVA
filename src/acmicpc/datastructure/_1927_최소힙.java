package acmicpc.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
    최소 힙(Min Heap)
    부모 키값이 자식 노드 키값보다 작은 힙
    최소 힙이란 부모 노드의 값은 항상 자식 노드보다 작거나 같을 때를 의미합니다.
    즉 , 루트 노드 = 트리의 최솟값입니다.
 */
public class _1927_최소힙 {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());

            if (x == 0) {
                if (minHeap.isEmpty()) {
                    System.out.println("0");
                } else {
                    System.out.println(minHeap.poll());
                }
            } else {
                minHeap.add(x);
            }
        }

    }
}

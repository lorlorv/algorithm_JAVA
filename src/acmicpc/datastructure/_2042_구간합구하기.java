package acmicpc.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    Java의 정수 타입 범위
	1.	byte: -128 ~ 127
	2.	short: -32,768 ~ 32,767
	3.	int: -2³¹ ~ 2³¹-1 (-2,147,483,648 ~ 2,147,483,647)
	4.	long: -2⁶³ ~ 2⁶³-1 (-9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807)


	❌ 배열, 누적합 시간 복잡도 O(NM)
	=> 세그먼트 트리  O(log N)

       1. 리프 노드: 배열의 그 수 자체
          리프 노드가 아닌 노드: 왼쪽 자식과 오른쪽 자식의 합을 저장

       2. Full Binary Tree의 형태
          - 리프 노드가 N개인 Full Binary Tree에는 리프 노드가 아닌 노드가 N-1개
          - 필요한 노드의 수는 2N - 1
            ㄴ 총 노드의 수 = 리프노드 + 내부노드

	N(1 ≤ N ≤ 1,000,000)
	M(1 ≤ M ≤ 10,000)
	K(1 ≤ K ≤ 10,000)

    a (1 ≤ a ≤ 2)
    b (1 ≤ b ≤ 1,000,000)
    c (b ≤ c ≤ N)

 */


public class _2042_구간합구하기 {
    static int N, M, K;
    static long[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] numInput = br.readLine().split(" ");

        N = Integer.parseInt(numInput[0]);
        M = Integer.parseInt(numInput[1]);
        K = Integer.parseInt(numInput[2]);

        arr = new long[N];

//        int h = (int)Math.ceil(Math.log(N) / Math.log(2));
//        int tree_size = (1 << (h+1));

        /*
            tree_size가 왜 2 * N이면 안될까?

            리프 노드의 개수가 N일 때 이진트리의 높이는 log2(N)
            => h = Math.log(N) / Math.log(2)
                = (int)Math.ceil(Math.log(N)/Math.log(2);
            전체 노드 수
            =>  2^(h+1)−1
                = (int)Math.pow(2,h+1);

            * 대충 4 * N로 하면 충분한 배열 크기

         */
//        tree = new long[2 * N]; // 1부터 시작
        tree = new long[4 * N]; // 1부터 시작
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        init(arr, tree, 1, 0, N - 1);


        List<Long> result = new ArrayList<>();
        for (int i = 0; i < M + K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1) { // 수 바꾸기
                long c = Long.parseLong(st.nextToken());
                update(arr, tree, 1, 0, N - 1, b - 1, c);
            } else {
                int c = Integer.parseInt(st.nextToken());
                long sum = query(tree, 1, 0, N - 1, b - 1, c - 1);
                result.add(sum);
            }

        }

        for (long item : result) {
            System.out.println(item);
        }
    }

    // a: 배열 A
    // tree: 세그먼트 트리
    // node: 노드 번호
    // node에 저장되어 있는 합의 범위가 start - end
    static void init(long[] a, long[] tree, int node, int start, int end) {
        if (start == end) { // 리프 노드인 경우
            tree[node] = a[start];
        } else {
            // 구간 분할
            init(a, tree, node * 2, start, (start + end) / 2); // end : 처음 ~ 중간값
            init(a, tree, node * 2 + 1, (start + end) / 2 + 1, end); // start : 중간값 + 1 ~ 끝
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    static long query(long[] tree, int node, int start, int end, int left, int right) {
        // 1. [left, right] 와 [start, end] 가 겹치지 않는 경우
        // [start,end] 뒤에 [left,right]가 있는 경우 | [start,end] 앞에 [left,right]가 있는 경우
        if (left > end || right < start) {
            return 0;
        }
        // 2. [left,right]가 [start,end]를 완전히 포함하는 경우
        if (left <= start && end <= right) {
            return tree[node];
        }
        // 3. [start,end]가 [left,right]를 완전히 포함하는 경우
        // 4. [left,right]와 [start,end]가 겹쳐져 있는 경우 (1, 2, 3 제외한 나머지 경우)
        long lsum = query(tree, node * 2, start, (start + end) / 2, left, right);
        long rsum = query(tree, node * 2 + 1, (start + end) / 2 + 1, end, left, right);
        return lsum + rsum;
    }


    /*
        수 변경하기
     */
    static void update(long[] a, long[] tree, int node, int start, int end, int index, long val) {
        if (index < start || index > end) {
            return;
        }
        if (start == end) { // 리프노드를 찾으면 그 노드의 합을 변경
            a[index] = val;
            tree[node] = val;
            return;
        }
        update(a, tree, node * 2, start, (start + end) / 2, index, val);
        update(a, tree, node * 2 + 1, (start + end) / 2 + 1, end, index, val);
        tree[node] = tree[node * 2] + tree[node * 2 + 1]; // 각 노드의 합을 다시 구하기
    }

}



package DAY07.P1753;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class P1753 {
    static int V, E, K;
    static List<Node>[] nodes;
    static PriorityQueue<Node> pq;
    static int[] distance;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/DAY07/p1753/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        nodes = new ArrayList[V + 1];
        distance = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            distance[i] = Integer.MAX_VALUE;
            nodes[i] = new ArrayList<>();
        }
        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[u].add(new Node(v, w));
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(Node::getWeight));
        dijkstra(K);

        for (int i = 1; i <= V; i++) {
            if(distance[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            }else{
                System.out.println(distance[i]);
            }
        }

    }
    static void dijkstra(int start){
        boolean[] visited = new boolean[V + 1];

        pq.add(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node minNode = pq.poll(); //가장 최소값 꺼내기
            
            if(visited[minNode.index]){
                continue;
            }
            visited[minNode.index] = true;

            for (Node node :
                    nodes[minNode.index]) {
                if(distance[node.index] > node.weight + distance[minNode.index]){
                    distance[node.index] = node.weight + distance[minNode.index];
                    pq.add(new Node(node.index, distance[node.index]));
                }
            }
        }
    }
}
class Node{
    int index;
    int weight;

    public Node(int to, int weight) {
        this.index = to;
        this.weight = weight;
    }

    public int getIndex() {
        return index;
    }

    public int getWeight() {
        return weight;
    }
}

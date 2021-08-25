package boj_1753;

import java.util.*;
import java.io.*;
public class BOJ_1753 {	 
	static ArrayList<Node> [] list;
	static int distance[];	
	static final int INF = 100000000;
	static int V, E, K;
	
	static class Node implements Comparable<Node>{
		int weight;
		int index; 
		
		public Node(int index, int weight) {
			this.weight = weight;
			this.index = index;
		}
	
		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.weight, node.weight);
		}		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
		String[] s1 = br.readLine().split(" ");
	    V = Integer.parseInt(s1[0]); //정점의 개수
	    E = Integer.parseInt(s1[1]); //간선의 개수
	    K = Integer.parseInt(br.readLine()); //시작점
	     
	    distance = new int[V + 1];		
		list = new ArrayList[V + 1];
		
		Arrays.fill(distance, INF);
		for (int i = 1; i <= V; i++) {
			list[i] = new ArrayList<>();
		}
   
	    for(int i = 0; i < E; i++) {
	    	String[] s2 = br.readLine().split(" ");
	        int start = Integer.parseInt(s2[0]);
	        int end = Integer.parseInt(s2[1]);
	        int weight = Integer.parseInt(s2[2]);	    
	        list[start].add(new Node(end, weight));
	    }
	     
	    dijkstra(K);
	    	   
		for(int i = 1; i < distance.length; i++) {
			if(distance[i] == INF)
				bw.write("INF");
			else
				bw.write(Integer.toString(distance[i]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	    
	    	     
	}
	public static void dijkstra(int v) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean check[] = new boolean[V + 1];

		pq.add(new Node(v, 0));
		distance[v] = 0;
			
		while(!pq.isEmpty()) {		
			//노드 최소값 꺼내기 
			Node node = pq.poll(); //가장 최소값 
			int index = node.index;
			
			if(check[index])
				continue;
			check[index] = true;
			
			for(Node next : list[index]) {
				if(distance[next.index] > distance[index] + next.weight) {
					distance[next.index] = distance[index] + next.weight;
					pq.offer(new Node(next.index, distance[next.index]));
				}
			}
		}			
	}	
}

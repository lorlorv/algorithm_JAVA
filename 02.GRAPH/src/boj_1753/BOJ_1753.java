package boj_1753;

import java.util.*;
import java.io.*;
public class BOJ_1753 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
		 StringTokenizer st = new StringTokenizer(br.readLine());
	     int v = Integer.parseInt(st.nextToken());
	     int e = Integer.parseInt(st.nextToken());
	     int k = Integer.parseInt(br.readLine());
	     
	     graph_1753 g = new graph_1753(v);
	     for(int i = 0; i < e; i++) {
	    	 st = new StringTokenizer(br.readLine());
	         int start = Integer.parseInt(st.nextToken());
	         int end = Integer.parseInt(st.nextToken());
	         int weight = Integer.parseInt(st.nextToken());
	    	 g.input(start, end, weight);
	     }
	     
	     g.dijkstra_1753(k);
	    	     
	}

}

class graph_1753{
	private int n; //������ �� 
	private int maps[][]; //������ ����ġ ����
	
	class Node implements Comparable<Node>{
		private int weight;
		private int index; 
		
		public Node(int weight, int index) {
			this.weight = weight;
			this.index = index;
		}
	
		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.weight, node.weight);
		}
		
	}
	
	//������
	public graph_1753(int n) { //�ʱ�ȭ ����
		this.n = n;
		maps = new int[n + 1][n + 1];
		
		//������� �ʱ�ȭ
		for(int i=1; i<=n; ++i){
			for(int j=1; j<=n; ++j){
				maps[i][j] = Integer.MAX_VALUE; 
			} 			
		}		
	}
	
	public void input(int i, int j, int w) {
		maps[i][j] = w;
	}
	
		
	public void dijkstra_1753(int v) throws IOException {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int distance[] = new int[n + 1];
		boolean check[] = new boolean[n + 1];
		
		//distance �ʱ�ȭ
		for(int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		//�켱���� ť�� start ������ index�� ����
		pq.add(new Node(v, 1));
		distance[v] = 0; //�������̴ϱ� distance�� 0
		check[v] = true;
		
		//������ distance ����
		for(int i = 1; i <= n; i++) {
			if(!check[i] && maps[v][i] != Integer.MAX_VALUE) {
				distance[i] = maps[v][i];
				pq.add(new Node(distance[i], i)); //�켱���� ť�� weight �� ������ ����ġ, i�� ������ index
			}
		}
		
		while(!pq.isEmpty()) {
			int index;
			
			//��� �ּҰ� ������ 
			Node node = pq.poll(); //���� �ּҰ� 
			index = node.index;
			
			check[index] = true;
			//distance ����
			for(int i = 1; i <= n; i++) {
				if(!check[i] && maps[index][i] != Integer.MAX_VALUE) {
					if(distance[i] > distance[index] + maps[index][i]) { //���� ��带 ���İ��� ���� �� ª����
						distance[i] = distance[index] + maps[index][i];
						pq.add(new Node(distance[i], i));
					}
				}
			}			
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 1; i <= n; i++) {
			if(distance[i] == Integer.MAX_VALUE)
				bw.write("INF");
			else
				bw.write(Integer.toString(distance[i]));
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
	}
}

import java.util.PriorityQueue;
public class Dijkstra_pq {

	public static void main(String[] args) {
		Graph g = new Graph(5);
        g.input(1, 2, 10);
        g.input(1, 4, 60);
        g.input(2, 4, 30);
        g.input(2, 3, 45);
        g.input(2, 5, 60);
        g.input(3, 5, 20);
        g.input(3, 4, 5);
        g.dijkstra(1);
	}
}

class graph_pq{
	private int n; //노드들의 수 
	private int maps[][]; //노드들의 가중치 저장
	
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
	
	//생성자
	public graph_pq(int n) { //초기화 역할
		this.n = n;
		maps = new int[n + 1][n + 1];
		
		//인접행렬 초기화
		for(int i=1; i<=n; ++i){
			for(int j=1; j<=n; ++j){
				maps[i][j] = Integer.MAX_VALUE; 
			} 			
		}		
	}
	
	public void input(int i, int j, int w) {
		maps[i][j] = w;
		maps[j][i] = w;
	}
	
		
	public void dijkstra_pq(int v) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int distance[] = new int[n + 1];
		boolean check[] = new boolean[n + 1];
		
		//distance 초기화
		for(int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE;
		}
		//우선순위 큐에 start 정점과 index를 삽입
		pq.add(new Node(v, 1));
		distance[v] = 0; //시작점이니까 distance는 0
		check[v] = true;
		
		//연결노드 distance 갱신
		for(int i = 1; i <= n; i++) {
			if(!check[i] && maps[v][i] != Integer.MAX_VALUE) {
				distance[i] = maps[v][i];
				pq.add(new Node(distance[i], i)); //우선순위 큐에 weight ← 정점의 가중치, i는 정점의 index
			}
		}
		
		while(!pq.isEmpty()) {
			int index;
			
			//노드 최소값 꺼내기 
			Node node = pq.poll(); //가장 최소값 
			index = node.index;
			
			check[index] = true;
			//distance 갱신
			for(int i = 1; i <= n; i++) {
				if(!check[i] && maps[index][i] != Integer.MAX_VALUE) {
					if(distance[i] > distance[index] + maps[index][i]) { //현재 노드를 거쳐가는 것이 더 짧으면
						distance[i] = distance[index] + maps[index][i];
						pq.add(new Node(distance[i], i));
					}
				}
			}			
		}
		
		for(int i = 1; i <= n; i++) {
			System.out.println(i + "번 노드까지 가는 최단 거리  : " + distance[i] + " ");
		}
		
	}
}

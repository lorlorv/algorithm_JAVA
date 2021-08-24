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
	public graph_pq(int n) { //�ʱ�ȭ ����
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
		maps[j][i] = w;
	}
	
		
	public void dijkstra_pq(int v) {
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
		
		for(int i = 1; i <= n; i++) {
			System.out.println(i + "�� ������ ���� �ִ� �Ÿ�  : " + distance[i] + " ");
		}
		
	}
}

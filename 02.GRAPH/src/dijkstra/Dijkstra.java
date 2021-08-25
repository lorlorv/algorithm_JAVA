//Dijkstra skeleton
package dijkstra;
class Graph{
	private int n; //������ �� 
	private int maps[][]; //������ ����ġ ����
	
	public Graph(int n) {
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
	
	public void dijkstra(int v) {
		boolean check[] = new boolean[n + 1];
		int distance[] = new int[n + 1];
		
		for(int i = 1; i <= n; i++) {
			distance[i] = Integer.MAX_VALUE; //distance�� �ʱ�ȭ
		}
		
		//���۳�� �ʱ�ȭ!
		distance[v] = 0;
		check[v] = true;
		
		//������ �������ְ� �湮���� ���� ���� �Ÿ� ����
		for(int i = 1; i <= n; i++) {
			if(!check[i] && maps[v][i] != Integer.MAX_VALUE) {
				distance[i] = maps[v][i];
			}
		}
		
		//��� ������ check�迭�� �� �� ���� 
		for(int i = 0; i < n - 1; i++) {
			
			//�ּ� distance ã��
			int min = Integer.MAX_VALUE;
			int index = -1;
			
			for(int j = 1; j <= n; j++) {
				if(!check[j]) {
					if(distance[j] < min) {
						min = distance[j];
						index = j;
					}
				}
			}
			
			check[index] = true;		
			//distance ����
			for(int j = 1; j <= n; j++) {
				if(!check[j] && maps[index][j] != Integer.MAX_VALUE) {
					if(distance[j] > distance[index] + maps[index][j]) {
						distance[j] = distance[index] + maps[index][j];
					}
				}
			}
			
		}
		
		for(int i = 1; i <= n; i++) {
			System.out.println(i + "�� ������ ���� �ִ� �Ÿ�  : " + distance[i] + " ");
		}
	}
}
public class Dijkstra {

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


package floyd;

import java.io.*;

public class BOJ_11404 {
	static int n, m;
	static int a, b, c;
	static int[][] maps;
	static int INF = 10000001;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		//�ʱ�ȭ
		maps = new int[n + 1][n + 1];
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				maps[i][j] = INF;
				if(i == j)
					maps[i][j] = 0;					
			}
		}
		
		//�Է�
		for(int i = 0; i < m; i++) {
			String[] temp = br.readLine().split(" ");
			a = Integer.parseInt(temp[0]);
			b = Integer.parseInt(temp[1]);
			c = Integer.parseInt(temp[2]);
			//1 4 1 �� 1 4 2�� ������ �� 1 4 1�� ���ؾ��ϱ� ������ Math.min ���
			maps[a][b] = Math.min(maps[a][b], c); 	
		}

		//floyd �Լ�
		floyd();
		
		//���
		for(int i = 1; i <= n; i++) {
			for(int j = 1; j <= n; j++) {
				if(maps[i][j] == INF) //i���� j�� �� �� ���� ��� == floyd�� �ص� ����� INF�� ���
					bw.write("0 ");
				else
					bw.write(String.valueOf(maps[i][j]) + " " );
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		
	}
	static void floyd() {
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= n; j++) {
					if(maps[i][j] > maps[i][k] + maps[k][j]) {
						maps[i][j] = maps[i][k] + maps[k][j];
					}
				}
			}
		}
				
	}	
}

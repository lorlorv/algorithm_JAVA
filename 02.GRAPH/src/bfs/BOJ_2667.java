package bfs;
import java.util.*;
import java.io.*;

public class BOJ_2667 {

	static int N;
	static int maps[][];
	static int count;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		N = Integer.parseInt(br.readLine());
		maps = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			String temp[] = br.readLine().split("");
			for(int j = 0; j < N; j++) {			
				maps[i][j] = Integer.parseInt(temp[j]);
			}
		}
			
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(maps[i][j] != 0) {
					count = 0;
					search(i, j);
					pq.add(count);
					
				}
			}
		}
		
		bw.write(String.valueOf(pq.size()));
		bw.newLine();
		while(!pq.isEmpty()) {
			bw.write(String.valueOf(pq.poll()));
			bw.newLine();
		}
			 
		bw.flush();
		bw.close();
	}
	
	static void search(int x, int y) {
		count++;
		maps[x][y] = 0;
		
		int X[] = {1, -1, 0, 0};
		int Y[] = {0, 0, 1, -1};
		
		for(int i = 0; i < 4; i++) {
			int currentX = x + X[i];
			int currentY = y + Y[i];
			
			if(0 <= currentX && currentX < N && 0<= currentY && currentY < N) { //범위 안에 들어오는 지 확인 필수 !!
				if(maps[currentX][currentY] == 1)
					search(currentX, currentY);
			}
		}
	}

}

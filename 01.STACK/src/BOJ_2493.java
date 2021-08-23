import java.util.*;
import java.io.*;

public class BOJ_2493 {
	static int N;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Integer> stack = new Stack<>();

		N = Integer.parseInt(br.readLine());

		int arr[] = new int[N];
		int result[] = new int[N];

		String temp[] = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(temp[i]);
		}

		stack.push(N - 1); 
		for(int i = N - 2; i >= 0; i--) { 
			if(stack.empty())
				stack.push(i); 
			while(!stack.empty() && (arr[stack.peek()] < arr[i]))
					 result[stack.pop()] = i + 1; 
			stack.push(i); 
		}
		
		if(!stack.empty()) { 
			while(!stack.empty())
				result[stack.pop()] = 0; 
		}
		 
		for(int r : result) 
			bw.write(String.valueOf(r) + " ");
		 
		bw.flush();
		bw.close();

	}

}

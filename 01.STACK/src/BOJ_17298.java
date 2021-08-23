import java.util.*;
import java.io.*;

public class BOJ_17298 {
static int n, a;

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Stack <Integer> stack = new Stack<>();	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		int arr[] = new int[n];		
		int result[] = new int[n];
		
		String temp[] = br.readLine().split(" ");
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(temp[i]);
		}
		
		stack.push(0);
		for(int i = 1; i < n; i++) {
			if(stack.empty()) {
				stack.push(i);
			}
			while(!stack.empty() && arr[i] > arr[stack.peek()]) {	
				result[stack.pop()] = arr[i];														
			}
			stack.push(i);
		}
		
		if(!stack.empty()) {
			while(!stack.empty()) {
				result[stack.pop()] = -1;				
			}
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int r : result) {
			bw.write(String.valueOf(r) + " ");
		}
		
		
		bw.flush();
		bw.close();
	}
	
}

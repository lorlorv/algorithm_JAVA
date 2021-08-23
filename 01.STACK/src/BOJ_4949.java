import java.util.*;
import java.io.*;
public class BOJ_4949 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input;
		
		while(true) {
			input = br.readLine();
			if(input.equals("."))
				break;
		
			if(check(input))
				bw.write("yes");
			else
				bw.write("no");
			bw.newLine();

		}
		bw.flush();
		bw.close();
	
	}
	static boolean check(String s) {
		Stack<Character> stack = new Stack<>();
		for(int i = 0; i < s.length(); i++) {
			char c= s.charAt(i);
			
			switch(c) {
			case '(': case'[':
				stack.push(c);
				break;
			case ')': case ']':
				if(stack.empty()) {
					return false;
				}
				else {
					char check = stack.pop();
					if(check == '(' && c == ']' || check == '[' && c == ')') {
						return false;
					}
				}
				break;
			}			
		}
		
		if(!stack.empty())
			return false;
		return true;
	}
	

}

import java.util.*;
import java.io.*;
public class BOJ_1918 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Stack<Character> stack = new Stack<>();
		
		String input = br.readLine();
		
		for(int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
			
			switch(ch) {
			case '+': case'-': case '*' : case '/':
				while(!stack.empty() && operator(stack.peek()) >= operator(ch))
					bw.write(stack.pop());
				stack.push(ch);
				break;
			case '(':
				stack.push(ch);
				break;
			case ')':
				while(!stack.peek().equals('(')) {
					bw.write(stack.pop());
				}
				stack.pop();
				break;
			default:
				bw.write(ch);
				break;
			}
		}
		
		if(!stack.empty()) {
			while(!stack.empty()) {
				bw.write(stack.pop());
			}
		}
		
		bw.flush();
		bw.close();
	}
	
	static int operator(char op) {
		switch(op) {
		case '(' : case ')':
			return 0;
		case '+' : case '-':
			return 1;
		case '*' : case '/':
			return 2;
		}
		return -1;
	}

}

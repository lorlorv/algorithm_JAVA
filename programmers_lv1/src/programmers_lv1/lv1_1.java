//x만큼 간격이 있는 n개의 숫자 
package programmers_lv1;

import java.util.Scanner;

public class lv1_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        long[] rst = solution(a,b);
        for(int i = 0; i < b; i++) {
        	System.out.print(rst[i]);
        }
	}
	public static long[] solution(long x, int n) {
		//testcase 13,14 fail
		//혹시 x가 0이라면? ->문제 없음.,,,
		//혹시 n이 0이라면? -> 문제 없음 
		//해결! -> 질문창을 보고 해결함 매개변수 int x 를 long x로 바꾼다 
	    long[] answer = {};
	    answer = new long[n];
	    for(int i = 0; i < n; i++){
	    	answer[i] = x + x * i;
	    }
	    return answer;
	}
}

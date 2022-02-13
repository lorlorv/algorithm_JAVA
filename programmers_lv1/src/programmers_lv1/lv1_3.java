//핸드폰 번호 가리기 
package programmers_lv1;

public class lv1_3 {
	public String solution(String phone_number) {
		String answer = "";
        //1. for문을 돌려서 string 크기보다 4작은 위치까지만 *로 치환하자 
		//2. string을 한글자 한글자 씩 보는 방법은?
        
        //string 크기 구하기
        int size = phone_number.length();
        //2. string 한 글자씩 string 배열에 넣기 
        String[] arr;
        arr = phone_number.split("");
        //* 치환
        for(int i = 0 ; i < size - 4; i++){
            arr[i] = "*";
        }
        answer = String.join("", arr);
	    return answer;
	}
}

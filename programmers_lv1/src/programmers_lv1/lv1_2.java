//행렬의 덧셈 
package programmers_lv1;

class lv1_2 {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = {};
        answer = new int[arr1.length][arr1[0].length];
        
        //        j j
        //i = 0 | 1 2    3 4  | 1 2  3 4 
        //i = 1 | 2 3    5 6  |
        
        for(int i = 0; i < arr1.length; i++){
            for(int j = 0; j < arr1[0].length; j++){
                answer[i][j] = arr1[i][j] + arr2[i][j];
            }
        }
        
        return answer;
    }
}
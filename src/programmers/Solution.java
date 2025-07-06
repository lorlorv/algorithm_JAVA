package programmers;

import java.util.*;

class Solution {
    public static void main(String[] args){
        int answer = solution(new int[]{1,1,1,1,1}, 3);
        System.out.println(answer);
    }

    public static int solution(int[] numbers, int target) {
        int answer = 0;

        answer += dfs(numbers, target, 0, 0);
        return answer;
    }

    public static int dfs (int[] numbers, int target, int depth, int sum) {
        if (depth == numbers.length){
            if (sum == target)
                return 1;
            else return 0;
        }

        int count = 0;
        count += dfs(numbers, target, depth + 1, sum + numbers[depth]);
        count += dfs (numbers, target, depth + 1, sum - numbers[depth]);

        return count;
    }

}
package programmers;

class Solution_중복순열 {
    static boolean[] visited;
    public static void main(String[] args){
        String answer = solution(new int[]{6, 10, 2});
        System.out.println(answer);
    }

    public static String solution(int[] numbers) {
        String answer = "";
        visited = new boolean[numbers.length];

        int[] temp = new int[numbers.length];
        long result = dfs(numbers, temp, 0, 0L);

        answer = String.valueOf(result);
        return answer;
    }

    public static long dfs (int[] numbers, int[] temp, int count, long answer) {

        if (count == numbers.length){
            // temp 배열을 숫자로 변경
            StringBuilder sb = new StringBuilder();
            for (int t : temp){
                sb.append(t);
            }

            return Long.parseLong(String.valueOf(sb));
        }

        long max = Long.MIN_VALUE;

        for (int i = 0; i < numbers.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[count] = numbers[i];
                long result = dfs(numbers, temp, count + 1, answer);
                max = Math.max(result, max);
                visited[i] = false;
            }

        }

        return max;
    }

}
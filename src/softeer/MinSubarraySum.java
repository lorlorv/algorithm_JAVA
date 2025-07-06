package softeer;

import java.util.*;

public class MinSubarraySum {
    public static int minSubarrayLen(int S, int[] nums) {
        int N = nums.length;
        int[] prefixSum = new int[N + 1]; // 누적합 배열

        // 누적합 배열 만들기
        for (int i = 1; i <= N; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int minLength = Integer.MAX_VALUE;

        // 누적합 배열에서 이진 탐색을 활용하여 최소 길이 찾기
        for (int start = 0; start < N; start++) {
            int target = prefixSum[start] + S; // 부분합이 S 이상인 구간을 찾기
            int end = Arrays.binarySearch(prefixSum, target);

            if (end < 0) {
                end = -(end + 1); // Java의 binarySearch 결과 변환
            }

            if (end <= N) { // 유효한 구간이면 최소 길이 갱신
                minLength = Math.min(minLength, end - start);
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int S = 7;

        int result = minSubarrayLen(S, nums);
        System.out.println("최소 길이의 부분 배열: " + result);
    }
}

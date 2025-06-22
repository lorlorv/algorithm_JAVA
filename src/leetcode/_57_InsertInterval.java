package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _57_InsertInterval {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));
        System.out.println(Arrays.deepToString(insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4, 8})));
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0];
        int newEnd = newInterval[1];

        List<int[]> result = new ArrayList<>();


        for (int[] interval : intervals) {
            int currentStart = interval[0];
            int currentEnd = interval[1];

            // System.out.println("currentStart : " + currentStart + " currentEnd : " + currentEnd);
            // System.out.println("newStart : " + newStart + " newEnd : " + newEnd);


            // 새로운 인터벌보다 이전의 인터벌일 경우
            // currentEnd < newStart
            if (currentEnd < newStart) {
                result.add(new int[]{currentStart, currentEnd});
            }
            // 새로운 인터벌보다 이후의 인터벌일 경우 -> 새로운 인터벌 갱신
            else if (currentStart > newEnd) {
                result.add(new int[]{newStart, newEnd});
                newStart = currentStart;
                newEnd = currentEnd;
            } else {
                newStart = Math.min(newStart, currentStart);
                newEnd = Math.max(newEnd, currentEnd);
            }
        }

        result.add(new int[]{newStart, newEnd});

        // for (int[] arr : result){
        // System.out.print (arr[0] + ", " + arr[1] + "\n");
        // }


        return result.toArray(new int[result.size()][]);
    }

}

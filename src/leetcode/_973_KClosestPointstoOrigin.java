package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class _973_KClosestPointstoOrigin {
    public int[][] kClosest(int[][] points, int k) {
        Point[] arr = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            int sqrt = ((points[i][0] * points[i][0]) + (points[i][1] * points[i][1]));
            arr[i] = new Point(i, sqrt);
        }

        Arrays.sort(arr, Comparator.comparingInt(Point::getSqrt));

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            int index = arr[i].index;

            result[i][0] = points[index][0];
            result[i][1] = points[index][1];
        }

        return result;
    }
}

class Point {
    int index;
    int sqrt;

    Point(int index, int sqrt) {
        this.index = index;
        this.sqrt = sqrt;
    }

    int getSqrt() {
        return this.sqrt;
    }

}

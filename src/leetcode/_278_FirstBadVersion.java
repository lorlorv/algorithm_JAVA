package leetcode;

/*
    이분탐색

    ❌ mid 구할 때 mid = (s + e ) / 2 => Time Limit Exceeded
    ⭕️ mid = s + (e - s) / 2

    s + e 에서 오버플로우 발생 가능성 있음
    예: s = 2_000_000_000, e = 2_000_000_000 → s + e = 4_000_000_000 (int 범위 초과)

    mid = s + (e - s) / 2
    = s + (e / 2 - s / 2)
    = (2s + e - s) / 2
    = (s + e) / 2

 */

public class _278_FirstBadVersion extends VersionControl {
    /* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

    public int firstBadVersion(int n) {
        int s = 1, e = n, mid = 0;

        while (s <= e) {
            mid = s + (e - s) / 2;

            boolean prev = isBadVersion(mid - 1);
            boolean cur = isBadVersion(mid);

            if (cur && !prev) {
                return mid;
            } else if (!cur && !prev) {
                s = mid + 1;
            } else if (cur && prev) {
                e = mid - 1;
            }
        }

        return 1;
    }

}

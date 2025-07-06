package leetcode;


import java.util.HashSet;
import java.util.Set;

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class _141_LinkedListCycle {
    // -> 시간 복잡도 O(n)
    // 방문 체크하지 않고 (HashSet 사용하지 않고) 사이클 여부 판단해보기 => Floyd's Cycle Detection
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next;

        System.out.println(hasCycle(head));
    }

    public static boolean hasCycle(ListNode head) {
        Set<ListNode> visited = new HashSet<>();


        while (head != null) {
            // System.out.println("now : " + head.val);
            if (visited.contains(head)){
                return true;
            } else {
                visited.add(head);
                head = head.next;
            }
        }

        return false;
    }

}


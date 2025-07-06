package leetcode;

import com.sun.source.tree.Tree;

/**
 * 균형 잡힌 트리는 모든 노드에서 왼쪽 서브 트리와 오른쪽 서브 트리의 높이 차이가 1 이하인 트리입니다
 */

public class _110_BalancedBinaryTree {
    public static void main(String[] args) {
        // 트리 구성 1
//        TreeNode root = new TreeNode(3);
//        root.left = new TreeNode(9);
//        root.right = new TreeNode(20);
//        root.right.left = new TreeNode(15);
//        root.right.right = new TreeNode(7);


        // 트리 구성 2
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(3);
//        root.left.left.left = new TreeNode(4);
//        root.left.left.right = new TreeNode(4);

        // 트리 구성 3
//        TreeNode root = new TreeNode(0);
//        root.left = new TreeNode(1);
//        root.right = new TreeNode(2);
//        root.left.left = new TreeNode(2);
//        root.left.right = new TreeNode(2);
//        root.left.left.left = new TreeNode(3);
//        root.left.left.left.left = new TreeNode(4);
//        root.left.left.left.right = new TreeNode(4);
//        root.right.left = new TreeNode(3);
//        root.right.right = new TreeNode(3);

        // 트리 구성 4 : ⚠ (-1) - (-1) == 0 이 되어 TRUE가 됨
//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(2);
//
//        root.left.left = new TreeNode(3);
//        root.right.right = new TreeNode(3);
//
//        root.left.left.left = new TreeNode(4);
//        root.right.right.right = new TreeNode(4);

        // 트리 구성 5 : ⚠ (-1) - (0) == 절댓값 1이 되어 TRUE => 하나라도 음수값일 경우 -1 반환
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.left.left = new TreeNode(4);
        root.left.left.left.left = new TreeNode(5);

        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root, 0) >= 0;
    }

    private static int checkHeight(TreeNode node, int depth) {
        if (node == null)
            return 0;

        int leftHeight = checkHeight(node.left, depth + 1);
        int rightHeight = checkHeight(node.right, depth + 1);

        if ((Math.abs(leftHeight - rightHeight) > 1) || leftHeight < 0 || rightHeight < 0) {
            return -1; // 언제나 -1이 되어 계산 결과값이 1 초과
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }
}

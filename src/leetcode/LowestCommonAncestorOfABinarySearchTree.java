package leetcode;

/**
 * BST (Binary Search Tree) 의 특징
 * 1. 루트 n에 대하여 왼쪽 자식의 값은 left < n, 오른쪽 자식의 값은 n < right
 *
 * [ 문제 풀이 ]
 *
 * 1. 만약 현재 노드의 값이 p, q 둘보다 크면, LCA는 왼쪽 서브트리에 있음.
 *
 * 2. 만약 현재 노드의 값이 p, q 둘보다 작으면, LCA는 오른쪽 서브트리에 있음.
 *
 * 3. 만약 현재 노드의 값이 p와 q 사이에 있다면 (혹은 값이 p나 q와 같다면), 현재 노드가 LCA임.
 */
public class LowestCommonAncestorOfABinarySearchTree {
    public static void main(String[] args) {
        // 트리 구성
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);

        // p = 2, q = 5
        TreeNode p = root.left;                     // Node 2
        TreeNode q = root.left.right.right;         // Node 5


        System.out.println(lowestCommonAncestor(root, p, q).val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p , q);
        } else if (root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p , q);
        } else {
            return root;
        }
    }

}

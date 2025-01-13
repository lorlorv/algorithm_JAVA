package acmicpc.datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    PREORDER
    left -> parent -> right

    INORDER
    parent -> left -> right

    POSTORDER
    left -> right -> parent

    A B C B D . C E F E . . F . G D . . G . .
*/
class Node {
    char parent;
    Node left;
    Node right;

    public Node (char parent){
        this.parent = parent;
        this.left = null;
        this.right = null;
    }
}
public class _1991_트리순회 {
    static int num;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        num = Integer.parseInt(br.readLine());
        tree = new Node[num];

        for (int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            
            char parent = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            
            int parentIndex = parent - 'A';
            int leftIndex = left - 'A';
            int rightIndex = right - 'A';
            

            // 배열은 특정 문자로 찾을 수 없으니 ascii code 활용해서 인덱스를 설정하자
            if (tree[parentIndex] == null) {
                tree[parentIndex] = new Node(parent);
            }

            if (left != '.') { // 왼쪽 자식 있음
                tree[leftIndex] = new Node(left);
                tree[parentIndex].left = tree[leftIndex]; // 부모랑 연결
            }

            if (right != '.') { // 오른쪽 자식 있음
                tree[rightIndex] = new Node(right);
                tree[parentIndex].right = tree[rightIndex]; // 부모랑 연결
            }
        }

        preorder(tree[0]);
        System.out.println();
        inorder(tree[0]);
        System.out.println();
        postorder(tree[0]);

    }

    static void preorder(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.parent);
        preorder(node.left);
        preorder(node.right);
    }

    static void inorder(Node node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        System.out.print(node.parent);
        inorder(node.right);
    }

    static void postorder(Node node) {
        if (node == null) {
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.parent);
    }
}

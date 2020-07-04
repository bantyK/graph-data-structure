package bfs;

import java.util.*;

//429 https://leetcode.com/problems/n-ary-tree-level-order-traversal/
public class NArrayLevelOrderTraversal {
    public static void main(String[] args) {
        NArrayLevelOrderTraversal obj = new NArrayLevelOrderTraversal();
    }

    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();
                temp.add(curr.val);

                for (Node node : curr.children) {
                    queue.offer(node);
                }
            }

            result.add(new ArrayList<>(temp));
        }

        return result;
    }

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;
}

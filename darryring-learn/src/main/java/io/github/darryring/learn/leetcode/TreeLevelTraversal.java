package io.github.darryring.learn.leetcode;

import java.util.ArrayList;
import java.util.List;

public class TreeLevelTraversal {

    /// Definition for a binary tree node.
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void next(ArrayList<List<Integer>> res, TreeNode next, int level) {
        if (next == null) {
            return;
        }
        if (level > res.size()) {
            res.add(new ArrayList<>());
        }
        res.get(level - 1).add(next.val);
        level++;
        next(res, next.left, level);
        next(res, next.right, level);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> res = new ArrayList<>();
        next(res, root, 1);
        return res;
    }

    public void output(List<List<Integer>> res) {
        res.forEach(x -> {
            System.out.print("level : ");
            x.forEach(y -> System.out.print(y + ", "));
            System.out.println();
        });
    }

    public TreeNode input() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);

        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(11);

        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        root.right.right.right = new TreeNode(6);
        root.right.right.right.left = new TreeNode(66);
        return root;
    }

    public void run() {
        TreeNode root = input();
        List<List<Integer>> res = levelOrder(root);
        output(res);
    }

    public static void main(String[] args) {
        TreeLevelTraversal treeLevelTraversal = new TreeLevelTraversal();
        treeLevelTraversal.run();
    }
}

package com.duxiaoqier.algorithm.tree.mirror;

import com.duxiaoqier.algorithm.tree.TreeNode;

public class Solution {

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode treeNode = TreeNode.reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        new Solution().Mirror(treeNode);
        treeNode.printPreOrderTree();
    }

    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        if (temp != null) {
            Mirror(temp);
        }
        if (root.right != null) {
            Mirror(root.right);
        }

        root.left = root.right;
        root.right = temp;
    }

    public void Mirror2(TreeNode root) {
        if (root == null) return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        Mirror2(root.left);
        Mirror2(root.right);
    }
}

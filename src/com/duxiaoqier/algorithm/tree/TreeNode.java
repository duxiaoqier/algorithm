package com.duxiaoqier.algorithm.tree;

import java.util.Arrays;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public void printPreOrderTree() {
        System.out.println(this.val);

        if (this.left != null) {
            this.left.printPreOrderTree();
        }
        if (this.right != null) {
            this.right.printPreOrderTree();
        }
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre) {
            return null;
        }

        TreeNode treeNode = new TreeNode(pre[startPre]);
        int index = 0;
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] != pre[startPre]) {
                index++;
            } else {
                break;
            }
        }
        treeNode.left = reConstructBinaryTree(pre, startPre + 1, startPre + index, in, startIn, startIn + index);
        treeNode.right = reConstructBinaryTree(pre, startPre + index + 1, endPre, in, startIn + index + 1, endIn);
        return treeNode;
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }

        TreeNode treeNode = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (in[i] == pre[0]) {
                treeNode.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                treeNode.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, pre.length));
            }
        }

        return treeNode;

    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode treeNode = TreeNode.reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        treeNode.printPreOrderTree();
        TreeNode treeNode1 = TreeNode.reConstructBinaryTree(pre, in);
        treeNode1.printPreOrderTree();
    }
}
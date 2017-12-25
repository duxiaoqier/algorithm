package com.duxiaoqier.algorithm.build.binary.tree;

public class Solution {

    public static void main(String[] args) {
//        int[] pre = {1,2,4,7,3,5,6,8};
        int[] pre = {1,2,3,4};
//        int[] in = {4,7,2,1,5,3,8,6};
        int[] in = {4,3,2,1};

        TreeNode treeNode = new Solution().reConstructBinaryTree(pre, in);
        printTree(treeNode);
    }

    private static void printTree(TreeNode treeNode) {
        if (treeNode == null){
            return;
        }
        System.out.println(treeNode.val);

        if (treeNode.left != null){
            printTree(treeNode.left);
        }
        if(treeNode.right != null) {
            printTree(treeNode.right);
        }
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        int length = pre.length;
        if (length == 0 || length != in.length) return null;
        if (length == 1) {
            return new TreeNode(pre[0]);
        }

        int index = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) index = i;
        }

        int[] leftPre = new int[index];
        int[] leftIn = new int[index];
        int[] rightPre = new int[length - index - 1];
        int[] rightIn = new int[length - index - 1];

        for (int i = 0; i < length; i++) {
            if (i < index) {
                leftPre[i] = pre[i + 1];
                leftIn[i] = in[i];
            }
            if (i > index) {
                rightPre[i - index - 1] = pre[i];
                rightIn[i - index - 1] = in[i];
            }
        }

        TreeNode rootNode = new TreeNode(pre[0]);
        rootNode.left = reConstructBinaryTree(leftPre, leftIn);
        rootNode.right = reConstructBinaryTree(rightPre, rightIn);
        return rootNode;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
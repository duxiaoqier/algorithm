package com.duxiaoqier.algorithm.tree.print.top.to.bottom;

import com.duxiaoqier.algorithm.tree.TreeNode;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};

        TreeNode treeNode = TreeNode.reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
        System.out.println(new Solution().PrintFromTopToBottom(treeNode));
        System.out.println(new Solution().printFromTopToBottom(treeNode));
    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        List<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);

        while (treeNodes.size() > 0){
            List<TreeNode> temp = new LinkedList<>();
            treeNodes.forEach(treenode -> {
                result.add(treenode.val);
                if (treenode.left != null){
                    temp.add(treenode.left);
                }
                if (treenode.right != null) {
                    temp.add(treenode.right);
                }
            });
            treeNodes = temp;
        }
        return result;
    }

    public ArrayList<Integer> printFromTopToBottom(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
            result.add(temp.val);
        }
        return result;
    }
}
package com.duxiaoqier.algorithm.tree.is.sub;

import com.duxiaoqier.algorithm.tree.TreeNode;


public class Solution {

    public static void main(String[] args) {
        int[] pre1 = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in1 = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode1 = TreeNode.reConstructBinaryTree(pre1, in1);

        int[] pre2 = { 4, 7};
        int[] in2 = {4, 7};
        TreeNode treeNode2 = TreeNode.reConstructBinaryTree(pre2, in2);

        System.out.println(new Solution().HasSubtree(treeNode1, treeNode2));
    }

    // 四种递归情况
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
        }

        boolean subLeftResult = true;
        boolean subRightResult = true;
        if (root2.left != null) {
            subLeftResult = HasSubtree(root1.left, root2.left);
        }
        if (root2.right != null) {
            subRightResult = HasSubtree(root1.right, root2.right);
        }
        return (subLeftResult && subRightResult) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    public boolean isSubtree(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;

        if (root1.val == root2.val) {
            return isSubtree(root1.left, root2.left) && isSubtree(root1.right, root2.right);
        }
        return false;
    }

    public boolean HasSubtree2(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;
        return isSubtree(root1, root2) || HasSubtree2(root1.left, root2) || HasSubtree2(root1.right, root2);
    }


}

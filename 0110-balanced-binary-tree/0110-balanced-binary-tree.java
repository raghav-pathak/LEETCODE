/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        return dfsHeight(root) != -1;
    }

    int dfsHeight(TreeNode root){
        if(root == null)  return 0;
    

    int lf = dfsHeight(root.left);
    if(lf == -1) return -1;

    int rh = dfsHeight(root.right);
    if(rh == -1) return -1;

    if(Math.abs(lf - rh) > 1) return -1;
    return Math.max(lf,rh) +1;
}}
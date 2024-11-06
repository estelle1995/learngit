package notes;

public class Solution {
    public static void main(String[] args) {
        
    }

    public int maxPathSum (TreeNode root) {
        // write code here
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        
        return 0;
    }

 public static class TreeNode {
  int val = 0;
   TreeNode left = null;
  TreeNode right = null;
   public TreeNode(int val) {
     this.val = val;
  }
 }

}
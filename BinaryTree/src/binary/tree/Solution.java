package binary.tree;

public class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if ((p == left && q == right) || (q == left && p == right)) {// can be optimized!
			return root;
		} else if (left != null) {
			return left;
		} else if (right != null) {
			return right;
		}

		return null;
	}
	//binary search tree is easier!!!
	
	public int maxDepth(TreeNode root) {
        if(root ==null)
            return 0;
        
        else if (root != null && root.left == null && root.right == null) {
			return 1;
		}
        return Math.max(maxDepth(root.left)+1, maxDepth(root.right)+1);
    }
	public int minDepth(TreeNode root) {
		if(root ==null)
            return 0;
		if (root.left == null && root.right!= null) {
			return minDepth(root.right)+1;
		}
		else if (root.left != null && root.right == null) {
			return minDepth(root.left)+1;
		}
		else {
			return Math.min(minDepth(root.left)+1, minDepth(root.right)+1);
		}
    }
}

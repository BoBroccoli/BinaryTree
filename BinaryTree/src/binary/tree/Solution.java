package binary.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	// binary search tree is easier!!!

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;

		else if (root != null && root.left == null && root.right == null) {
			return 1;
		}
		return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
	}

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right != null) {
			return minDepth(root.right) + 1;
		} else if (root.left != null && root.right == null) {
			return minDepth(root.left) + 1;
		} else {
			return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
		}
	}

	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
		if (Math.abs(depthOfTree(root.left) - depthOfTree(root.right)) <= 1 && isBalanced(root.left)
				&& isBalanced(root.right))
			// pass boolean to the upper function, put into if connect with &&
			return true;
		else
			return false;
	}

	public int depthOfTree(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(depthOfTree(root.left), depthOfTree(root.right)) + 1;// important!
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		LinkedList<TreeNode> track = new LinkedList<>();
		List<List<Integer>> result = new ArrayList<>();
		if (root == null)
			return result;
		track.add(root);
		while (!track.isEmpty()) {
			List<Integer> aList = new ArrayList<>();
			int queueSize = track.size();
			for (int n = 0; n < queueSize; n++) {
				// Remember!!!!! queue size changes inside loop so make sure initial var can
				// hold
				// size of it
				TreeNode node = track.pop();
				if (node.left != null) {
					TreeNode left = node.left;
					track.addLast(left);
				}
				if (node.right != null) {
					TreeNode right = node.right;
					track.addLast(right);
				}
				aList.add((Integer) node.val);
			}
			result.add(aList);
		}
		return result;
	}

	public boolean isValidBST(TreeNode root) {
		if (root == null) {
			return true;
		}
		List<Integer> list = new ArrayList<>();
		DFS(list, root);
		for (int i = 0; i < list.size() - 1; i++) {
			if (list.get(i) >= list.get(i + 1)) {
				return false;
			}
		}
		return true;
	}

	public void DFS(List<Integer> list, TreeNode root) {
		if (root == null) {
			return;
		} else {
			DFS(list, root.left);
			list.add(root.val);
			DFS(list, root.right);
		}
	}

	public TreeNode insertNode(TreeNode root, TreeNode node) {
		if (root == null)
			return node;
		if (root.val > node.val) {
			root.left = insertNode(root.left, node);

		} else if (root.val < node.val) {
			root.right = insertNode(root.right, node);
		}
		return root;
	}

	public List<Integer> searchRange(TreeNode root, int k1, int k2) {
		List<Integer> list = new ArrayList<>();
		List<Integer> returnList = new ArrayList<>();
		if (root == null)
			return list;
		list = DFS(root, list);
		for (Integer integer : list) {
			if (integer >= k1 && integer <= k2)
				returnList.add(integer);
		}
		return returnList;
	}

	public List<Integer> DFS(TreeNode root, List<Integer> list) {
		if (root == null)
			return list;
		DFS(root.left, list);
		list.add(root.val);
		DFS(root.right, list);
		return list;
	}

	// such pain!!!
	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		int result = 0;

		result = sumHelper(root, result);

		return result;
	}

	public int sumHelper(TreeNode root, int i) {
		if (root == null) {
			return 0;
		}
		i = i * 10 + root.val;
		if (root.left == null && root.right == null) {
			return i;
		}
		int left = sumHelper(root.left, i);
		int right = sumHelper(root.right, i);
		return left + right;
	}

	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null ){
			return false;
		}
		if (root.val == sum && root.left == null && root.right == null) {
			return true;
		}
		return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
	}
}

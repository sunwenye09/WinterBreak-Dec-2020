//448. Inorder Successor in BST
public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null) {
            return null;
        }
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        }
        TreeNode left = inorderSuccessor(root.left, p);
        return left == null ? root : left;
    }
}

//94. Binary Tree Inorder Traversal
class Solution {
    private List<Integer> lst = new ArrayList<>();
    public List<Integer> inorderTraversal(TreeNode root) {
        inOrder(root);
        return lst;
    }
    private void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            inOrder(root.left);
        }
        lst.add(root.val);
        if (root.right != null) {
            inOrder(root.right);
        }
    }
}

//11. Search Range in Binary Search Tree
public class Solution {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> lst = new ArrayList<>();
        inOrder(root, k1, k2, lst);
        return lst;
    }
    private void inOrder(TreeNode root, int k1, int k2, List<Integer> lst) {
        if (root == null) {
            return;
        }
        inOrder(root.left, k1, k2, lst);
        if (root.val >= k1 && root.val <= k2) {
            lst.add(root.val);
        }
        inOrder(root.right, k1, k2, lst);
    }
}

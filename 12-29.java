//236. Lowest Common Ancestor of a Binary Tree
class Solution {
    private TreeNode ancestor;
    
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        DFS(root, p, q);
        return ancestor;
    }
    private boolean DFS(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        int left = DFS(root.left, p, q) ? 1 : 0;
        int right = DFS(root.right, p, q) ? 1 : 0;
        int mid = (root == p || root == q) ? 1 : 0;
        
        if (mid + left + right >= 2) {
            ancestor = root;
        }
        return (mid + left + right > 0);
    }
}

//173. Binary Search Tree Iterator
class BSTIterator {
    private List<TreeNode> lst;
    private int index;
    public BSTIterator(TreeNode root) {
        lst = new ArrayList<>();
        index = 0;
        inOrder(root);
    }
    
    public int next() {
        return lst.get(index++).val;
    }
    
    public boolean hasNext() {
        return index < lst.size();
    }
    private void inOrder(TreeNode root) {
        if (root.left != null) {
            inOrder(root.left);
        }
        lst.add(root);
        if (root.right != null) {
            inOrder(root.right);
        }
    }
}

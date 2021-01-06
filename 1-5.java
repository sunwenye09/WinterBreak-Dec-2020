//230. Kth Smallest Element in a BST
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            k--;
            if (k == 0) {
                return root.val;
            }
            root = root.right;
        }
    }
}

//901. Closest Binary Search Tree Value II
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        List<Integer> lst = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode walk = root;
        while (walk != null || !stack.isEmpty()) {
            while (walk != null) {
                stack.push(walk);
                walk = walk.left;
            }
            walk = stack.pop();
            lst.add(walk.val);
            walk = walk.right;
        }
        
        LinkedList<Integer> ans = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            ans.add(lst.get(i));
        }
        for (int i = k; i < lst.size(); i++) {
            int head = ans.getFirst();
            int curr = lst.get(i);
            if (Math.abs(target - curr) < Math.abs(target - head)) {
                ans.removeFirst();
                ans.add(curr);
            } else {
                break;
            }
        }
        return ans;
    }
}

//578. Lowest Common Ancestor III
public class Solution {
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
     private TreeNode ancestor = null;
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        DFS(root, A, B);
        return ancestor;
    }
    private boolean DFS(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) {
            return false;
        }
        int left = DFS(root.left, A, B) ? 1 : 0;
        int right = DFS(root.right, A, B) ? 1 : 0;
        int mid = (root == A || root == B) ? 1 : 0;
        if (root == A && root == B) {
            mid = 2;
        }
        if (left + right + mid >= 2) {
            ancestor = root;
        }
        return (left + right + mid) > 0;
    }
}

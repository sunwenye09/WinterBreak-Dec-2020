//226. Invert Binary Tree
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> copyQueue = new LinkedList<>();
        TreeNode copyRoot = new TreeNode(root.val);
        Stack<TreeNode> stack = new Stack<>();
        queue.offer(root);
        copyQueue.offer(copyRoot);
        while (queue.size() != 0) {
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                if (node == null) {
                    continue;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    stack.push(node.left);
                } else {
                    //queue.offer(null);
                    stack.push(null);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    stack.push(node.right);
                } else {
                    //queue.offer(null);
                    stack.push(null);
                }
            }
            for (int j = 0; j < num; j++) {
                if (stack.size() == 0) {
                    for (int k = j; k < num; k++) {
                        copyQueue.poll();
                    }
                    break;
                }
                TreeNode copyNode = copyQueue.poll();
                if (stack.size() != 0) {
                    TreeNode leftChild = stack.pop();
                    if (leftChild != null) {
                        copyNode.left = new TreeNode(leftChild.val);
                        copyQueue.offer(copyNode.left);
                    }
                }
                if (stack.size() != 0) {
                    TreeNode rightChild = stack.pop();
                    if (rightChild != null) {
                        copyNode.right = new TreeNode(rightChild.val);
                        copyQueue.offer(copyNode.right);
                    }
                }
            }
        }
        return copyRoot;
    }
}

//98. Validate Binary Search Tree
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
    public boolean isValidBST(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        return inOrder(root, lst);
    }
    private boolean inOrder(TreeNode root, List<Integer> lst) {
        if (root.left == null && root.right == null) {
            if (lst.size() == 0 || lst.get(lst.size() - 1) < root.val) {
                lst.add(root.val);
                return true;
            } else {
                return false;
            }
        }

        if (root.left != null && !inOrder(root.left, lst)) {
            return false;
        }
        
        if (lst.size() == 0 || lst.get(lst.size() - 1) < root.val) {
            lst.add(root.val);
        } else {
            return false;
        }
        
        if (root.right != null && !inOrder(root.right, lst)) {
            return false;
        }
        return true;
    }
}

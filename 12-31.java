//701. Insert into a Binary Search Tree
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode walk = root, parent = root;
        while (walk != null) {
            parent =walk;
            if (val < walk.val) {
                walk = walk.left;
            } else {
                walk = walk.right;
            }
        }
        if (val < parent.val) {
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
        return root;
    }
}

//87. Remove Node in Binary Search Tree
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        TreeNode walk = root, parent = root;
        int child = 0; //0: left child; 1: right child;
        while (walk != null && walk.val != value) {
            parent = walk;
            if (value < walk.val) {
                walk = walk.left;
                child = 0;
            } else {
                walk = walk.right;
                child = 1;
            }
        }
        //case1: not found
        if (walk == null) {
            return root;
        }
        //corner case
        if (root.val == value && isLeaf(root)) {
            return null;
        }
        //case2: leaf node; middle node;
        if (isLeaf(walk)) {
            removeLeafNode(child, parent);
        } else {
            removeMiddleNode(walk);
        }
        
        return root;
    }
    private void removeLeafNode(int child, TreeNode parent) {
        if (child == 0) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }
    private void removeMiddleNode(TreeNode node) {
        TreeNode walk, parent = node;
        int child = 0;
        if (node.left != null) {
            walk = node.left;
            child = 0;
            while (walk.right != null) {
                parent = walk;
                walk = walk.right;
                child = 1;
            }
            node.val = walk.val;
        } else {
            walk = node.right;
            child = 1;
            while (walk.left != null) {
                parent = walk;
                walk = walk.left;
                child = 0;
            }
            node.val = walk.val;
        }
        if (isLeaf(walk)) {
            removeLeafNode(child, parent);
        } else {
            removeMiddleNode(walk);
        }
    }
    private boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }
}

//900. Closest Binary Search Tree Value
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        int max = root.val, min = root.val;
        while (root != null) {
            if (target < root.val) {
                max = root.val;
                root = root.left;
            } else {
                min = root.val;
                root = root.right;
            }
        }
        System.out.println(max);
        System.out.println(min);
        if (Math.abs(target - (float)min) <= Math.abs((float)max - target)) {
            return min;
        } else {
            return max;
        }
    }
}

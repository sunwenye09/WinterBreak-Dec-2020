//257. Binary Tree Paths
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> lst = new ArrayList<>();
        if (root == null) {
            return lst;
        }
        backtrack(root, lst, new ArrayList<String>());
        return lst;
    }
    private void backtrack(TreeNode root, List<String> lst, ArrayList<String> subList) {
        if (subList.size() == 0) {
            subList.add("" + root.val);
        } else {
            subList.add("->" + root.val);
        }
        if (root.left == null && root.right == null) {
            lst.add(toString(subList));
            return;
        }
        if (root.left != null) {
            backtrack(root.left, lst, subList);
            subList.remove(subList.size() - 1);
        }
        if (root.right != null) {
            backtrack(root.right, lst, subList);
            subList.remove(subList.size() - 1);
        }
    }
    private String toString(ArrayList<String> subList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < subList.size(); i++) {
            sb.append(subList.get(i));
        }
        return sb.toString();
    }
}

//114. Flatten Binary Tree to Linked List
class Solution {
    private TreeNode prev = null;
    public void flatten(TreeNode root) {
       if (root == null) {
           return;
       }
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

//110. Balanced Binary Tree
class Solution {
    private int heightDifference = 0;
    public boolean isBalanced(TreeNode root) {
        calculateHeight(root);
        return heightDifference <= 1;
    }
    private int calculateHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = calculateHeight(root.left);
        int rightHeight = calculateHeight(root.right);
        heightDifference = Math.max(Math.abs(leftHeight - rightHeight), heightDifference);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}

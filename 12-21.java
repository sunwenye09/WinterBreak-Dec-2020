//242. Convert Binary Tree to Linked Lists by Depth
public class Solution {
    /**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
    public List<ListNode> binaryTreeToLists(TreeNode root) {
        // Write your code here
        List<ListNode> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) {
            return result;
        }
        queue.offer(root);
        while (queue.size() != 0) {
            int n = queue.size();
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            for (int i = 0; i < n; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
                ListNode node = new ListNode(curr.val);
                tail.next = node;
                tail = node;
            }
            result.add(dummy.next);
        }
        return result;
    }
}

//200. Number of Islands
class Solution {
    public int numIslands(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    BFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    private void BFS(char[][] grid, int i, int j) {
        int n = grid.length, m = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (queue.size() != 0) {
            int[] curr = queue.poll();
            int row = curr[0], col = curr[1];
            grid[row][col] = '0';
            if (col + 1 < m && grid[row][col + 1] == '1') {
                grid[row][col + 1] = '0';
                queue.offer(new int[]{row, col + 1});
            }
            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                grid[row][col - 1] = '0';
                queue.offer(new int[]{row, col - 1});
            }
            if (row + 1 < n && grid[row + 1][col] == '1') {
                grid[row + 1][col] = '0';
                queue.offer(new int[]{row + 1, col});
            }
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                grid[row - 1][col] = '0';
                queue.offer(new int[]{row - 1, col});
            }
        }
    }
}

//892. Alien Dictionary

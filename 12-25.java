//127. Topological Sorting
/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */

public class Solution {
    /*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        HashMap<DirectedGraphNode, Integer> inDegree = new HashMap<>();
        for (int i = 0; i < graph.size(); i++) {
            DirectedGraphNode from = graph.get(i);
            if (!inDegree.containsKey(from)) {
                inDegree.put(from, 0);
            }
            for (int j = 0; j < from.neighbors.size(); j++) {
                DirectedGraphNode to = from.neighbors.get(j);
                if (inDegree.containsKey(to)) {
                    inDegree.put(to, inDegree.get(to) + 1);
                } else {
                    inDegree.put(to, 1);
                }
            }
            
        }
        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for (DirectedGraphNode node : inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }
        ArrayList<DirectedGraphNode> result = new ArrayList<>();
        while (queue.size() != 0) {
            DirectedGraphNode curr = queue.poll();
            result.add(curr);
            for (int k = 0; k < curr.neighbors.size(); k++) {
                DirectedGraphNode out = curr.neighbors.get(k);
                inDegree.put(out, inDegree.get(out) - 1);
                if (inDegree.get(out) == 0) {
                    queue.offer(out);
                }
            }
        }
        return result;
    }
}

//127. Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        Set<String> visited = new HashSet<>();
        int level = 0;
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        while (queue.size() != 0) {
            level++;
            int num = queue.size();
            for (int i = 0; i < num; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord)) {
                    return level;
                } else if (visited.contains(curr)) {
                    continue;
                }
                visited.add(curr);
                List<String> neighbors = getNeighbors(curr, dict);
                for (String word : neighbors) {
                    if (visited.contains(word)) {
                        continue;
                    }
                    queue.offer(word);
                }
            }
        }
        return 0;
    }
    private List<String> getNeighbors(String word, Set<String> dict) {
        List<String> neighbors = new ArrayList<>();
        for (int index = 0; index < word.length(); index++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(index)) {
                    continue;
                }
                String str = word.substring(0, index) + c + word.substring(index + 1, word.length());
                if (dict.contains(str)) {
                    neighbors.add(str);
                }
            }
        }
        return neighbors;
    }
}

//297. Serialize and Deserialize Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean hasNext = true;
        while (hasNext) {
            int num = queue.size();
            hasNext = false;
            for (int i = 0; i < num; i++) {
                TreeNode node = queue.poll();
                if (node != null) {
                    sb.append(node.val);
                    sb.append(",");
                } else {
                    sb.append("null,");
                    continue;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                    hasNext = true;
                } else {
                    queue.offer(null);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    hasNext = true;
                } else {
                    queue.offer(null);
                }
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("null")) {
            return null;
        }
        String[] arr = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 1;
        while (queue.size() != 0 && index < arr.length) {
            TreeNode curr = queue.poll();
            if (!arr[index].equals("null")) {
                curr.left = new TreeNode(Integer.parseInt(arr[index]));
                queue.offer(curr.left);
            }
            if (!arr[index + 1].equals("null")) {
                curr.right = new TreeNode(Integer.parseInt(arr[index + 1]));
                queue.offer(curr.right);
            }
            index += 2;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));

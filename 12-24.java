//133. Clone Graph
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        //clone nodes
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val));
        cloneNode(node, map);
        cloneEdge(map);
        return map.get(node);
    }
    private void cloneNode(Node node, HashMap<Node, Node> map) {
        if (node.neighbors.size() == 0) {
            return;
        } else {
            for (int i = 0; i < node.neighbors.size(); i++) {
                Node curr = node.neighbors.get(i);
                if (!map.containsKey(curr)) {
                    map.put(curr, new Node(curr.val));
                    cloneNode(curr, map);
                }
            }
        }
    }
    private void cloneEdge(HashMap<Node, Node> map) {
        for (Node old : map.keySet()) {
            Node copyNode = map.get(old);
            for (int j = 0; j < old.neighbors.size(); j++) {
                Node copyNeighbor = map.get(old.neighbors.get(j));
                copyNode.neighbors.add(copyNeighbor);
            }
        }
    }
}

//605. Sequence Reconstruction
public class Solution {
    /**
     * @param org: a permutation of the integers from 1 to n
     * @param seqs: a list of sequences
     * @return: true if it can be reconstructed only one or false
     */
    public boolean sequenceReconstruction(int[] org, int[][] seqs) {
        
        // building graph
        HashMap<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[org.length + 1];
        for (int i = 0; i < seqs.length; i++) {
            if(!buildGraph(seqs[i], graph, inDegree)) {
                return false;
            }
        }
        //corner cases 
        if (org.length > 0 && graph.size() == 0) {
            return false;
        } else if(org.length == 0 && graph.size() == 0) {
            return true;
        }
        
        //topological sorting
        Queue<Integer> queue = new LinkedList<>();
        for (int j = 1; j < inDegree.length; j++) {
            if (inDegree[j] == 0) {
                queue.offer(j);
            }
        }
        if (queue.size() != 1) {
            return false;
        }
        int index = 0;
        while (queue.size() != 0) {
            if (queue.size() != 1) {
                return false;
            }
            int curr = queue.poll();
            if (curr != org[index++]) {
                return false;
            }
            if (!graph.containsKey(curr)) {
                continue;
            }
            Set<Integer> fanOut = graph.get(curr);
            for (int out : fanOut) {
                inDegree[out]--;
                if (inDegree[out] == 0) {
                    queue.offer(out);
                }
            }
        }
        return true;
    }
    private boolean buildGraph(int[] edges, HashMap<Integer, Set<Integer>> graph, int[] inDegree) {
        if (edges.length == 0) {
            return true;
        }
        for (int i = 0; i + 1 < edges.length; i++) {
            int from = edges[i], to = edges[i + 1];
            if (from >= inDegree.length || to >= inDegree.length) {
                return false;
            }
            if (!graph.containsKey(from)) {
                graph.put(from, new HashSet<Integer>());
            }
            Set<Integer> fanOut = graph.get(from);
            if (!fanOut.contains(to)) {
                fanOut.add(to);
                inDegree[to]++;
            }
        }
        int last = edges[edges.length - 1];
        if (last >= inDegree.length) {
            return false;
        }
        if (!graph.containsKey(last)) {
            graph.put(last, new HashSet<Integer>());
        }
        return true;
    }
}

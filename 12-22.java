//178. Graph Valid Tree
public class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        // write your code here
        if (edges == null) {
            return true;
        }
        if (edges.length != n - 1) {
            return false;
        }
        if (edges.length == 0) {
            return true;
        }
        //building graph
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            if (!graph.containsKey(from)) {
                List<Integer> temp = new ArrayList<>();
                temp.add(to);
                graph.put(from, temp);
            } else {
                graph.get(from).add(to);
            }
            if (!graph.containsKey(to)) {
                List<Integer> temp = new ArrayList<>();
                temp.add(from);
                graph.put(to, temp);
            } else {
                graph.get(to).add(from);
            }
        }
        //DFS
        for (int i = 0; i < n; i++) {
            boolean[] visited = new boolean[n];
            if (hasCycle(graph, visited, i, -1)) {
                return false;
            }
        }
        return true;
    }
    private boolean hasCycle(HashMap<Integer, List<Integer>> graph, boolean[] visited, int i, int parent) {
        if (visited[i]) {
            return true;
        }
        visited[i] = true;
        List<Integer> fanOut = graph.get(i);
        for (int j = 0; j < fanOut.size(); j++) {
            if (fanOut.get(j) == parent) {
                continue;
            } else if (hasCycle(graph, visited, fanOut.get(j), i)) {
                return true;
            }
        }
        return false;
    }
}

//618 and 431 cannot be found

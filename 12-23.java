//207. Course Schedule
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> fanOut = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            inDegree[course]++;
            if (!fanOut.containsKey(pre)) {
                List<Integer> lst = new ArrayList<>();
                lst.add(course);
                fanOut.put(pre, lst);
            } else {
                fanOut.get(pre).add(course);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        while (queue.size() != 0) {
            int curr = queue.poll();
            count++;
            if (!fanOut.containsKey(curr)) {
                continue;
            }
            List<Integer> outList = fanOut.get(curr);
            for (int i = 0; i < outList.size(); i++) {
                int out = outList.get(i);
                inDegree[out]--;
                if (inDegree[out] == 0) {
                    queue.offer(out);
                }
            }
        }
        
        return count == numCourses;
    }
}

//210. Course Schedule II
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //initializatiion
        int[] inDegree = new int[numCourses];
        HashMap<Integer, List<Integer>> fanOut = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int pre = prerequisites[i][1];
            inDegree[course]++;
            
            if (fanOut.containsKey(pre)) {
                fanOut.get(pre).add(course);
            } else {
                List<Integer> lst = new ArrayList<>();
                lst.add(course);
                fanOut.put(pre, lst);
            }
        }
        
        //find the entry point
        Queue<Integer> queue = new LinkedList<>();
        for (int j = 0; j < inDegree.length; j++) {
            if (inDegree[j] == 0) {
                queue.offer(j);
            }
        }
        
        //topological sort
        int[] result = new int[numCourses];
        int index = 0;
        while (queue.size() != 0) {
            int curr = queue.poll();
            result[index++] = curr;
            if (!fanOut.containsKey(curr)) {
                continue;
            }
            List<Integer> outList = fanOut.get(curr);
            for (int k = 0; k < outList.size(); k++) {
                int out = outList.get(k);
                inDegree[out]--;
                if (inDegree[out] == 0) {
                    queue.offer(out);
                }
            }
        }
        if (index == numCourses) {
            return result;
        } else {
            return new int[0];
        }
    }
}

//387. First Unique Character in a String
class Solution {
    public int firstUniqChar(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), 1);
            } else {
                map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}

//973. K Closest Points to Origin
class Solution {
    class Point {
        int x, y, distance;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            distance = x * x + y * y;
        }
    }
    class SortByDistance implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return p1.distance - p2.distance;
        }
    }
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<Point> minHeap = new PriorityQueue<>(new SortByDistance());
        for (int i = 0; i < points.length; i++) {
            minHeap.offer(new Point(points[i][0], points[i][1]));
        }
        int[][] res = new int[K][2];
        for (int i = 0; i < K; i++) {
            Point p = minHeap.poll();
            res[i][0] = p.x;
            res[i][1] = p.y;
        }
        return res;
    }
}

//232. Implement Queue using Stacks
class MyQueue {

    /** Initialize your data structure here. */
    Stack<Integer> stack1, stack2;
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        stack1.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (stack2.empty()) {
            restruct();
        }
        return stack2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (stack2.empty()) {
            restruct();
        }
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.empty() && stack2.empty();
    }
    private void restruct() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
    }
}

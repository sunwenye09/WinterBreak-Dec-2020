//378. Kth Smallest Element in a Sorted Matrix
class Solution {
    class Element {
        int val, row, col;
        public Element(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }
    class SortByVal implements Comparator<Element> {
        public int compare(Element e1, Element e2) {
            return e1.val - e2.val;
        }
    }
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Element> minHeap = new PriorityQueue<>(new SortByVal());
        for (int col = 0; col < matrix[0].length; col++) {
            minHeap.offer(new Element(matrix[0][col], 0, col));
        }
        for (int i = 0; i < k - 1; i++) {
            Element e = minHeap.poll();
            if (e.row + 1 < matrix.length) {
                minHeap.offer(new Element(matrix[e.row + 1][e.col], e.row + 1, e.col));
            }   
        }
        return minHeap.poll().val;
    }
    
}

//642. Moving Average from Data Stream
public class MovingAverage {
    /*
    * @param size: An integer
    */
    Queue<Integer> queue;
    int wSize;
    double sum;
    public MovingAverage(int size) {
        // do intialization if necessary
        queue = new LinkedList<>();
        wSize = size;
        sum = 0.0;
    }

    /*
     * @param val: An integer
     * @return:  
     */
    public double next(int val) {
        // write your code here
        if (queue.size() < wSize) {
            queue.offer(val);
            sum += val;
        } else {
            queue.offer(val);
            sum = sum + val - queue.poll();
        }
        return sum / queue.size();
    }
}

//225. Implement Stack using Queues
class MyStack {

    /** Initialize your data structure here. */
    Queue<Integer> q1, q2;
    int top;
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q1.offer(x);
        top = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        while (q1.size() > 1) {
            top = q1.poll();
            q2.offer(top);
        }
        int ans = q1.poll();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        return ans;
    }
    
    /** Get the top element. */
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty() && q2.isEmpty();
    }
}

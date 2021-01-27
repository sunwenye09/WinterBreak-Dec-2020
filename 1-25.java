//544. Top k Largest Numbers
public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // write your code here
        Arrays.sort(nums);
        if (nums.length < k) {
            return null;
        } else {
            int[] ans = new int[k];
            for (int i = nums.length - 1; i >= nums.length - k; i--) {
                ans[nums.length - 1 - i] = nums[i];
            }
            return ans;
        }
    }
}

//545. Top k Largest Numbers II
public class Solution {
    /*
    * @param k: An integer
    */
    PriorityQueue<Integer> pq;
    int topN;
    public Solution(int k) {
        // do intialization if necessary
        pq = new PriorityQueue<>();
        topN = k;
    }

    /*
     * @param num: Number to be added
     * @return: nothing
     */
    public void add(int num) {
        // write your code here
        pq.add(num);
        while (pq.size() > topN) {
            pq.poll();
        }
    }

    /*
     * @return: Top k element
     */
    public List<Integer> topk() {
        // write your code here
        LinkedList<Integer> lst = new LinkedList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(pq);
        while (minHeap.size() > 0) {
            lst.addFirst(minHeap.poll());
        }
        return lst;
    }
}

//876. Middle of the Linked List
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

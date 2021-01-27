//295. Find Median from Data Stream
class MedianFinder {

    /** initialize your data structure here. */
    PriorityQueue<Integer> low, high;
    public MedianFinder() {
        low = new PriorityQueue<>(new Ascending());        //maxHeap
        high = new PriorityQueue<>(new Descending());       //minHeap
    }
    
    public void addNum(int num) {
        low.offer(num);
        high.offer(low.poll());
        while (high.size() > low.size() + 1) {
            low.offer(high.poll());
        }
    }
    
    public double findMedian() {
        if ((low.size() + high.size()) % 2 == 1) {
            return high.peek();
        } else {
            return (double)(high.peek() + low.peek()) / 2;
        }
    }
    public class Ascending implements Comparator<Integer> {
        public int compare(Integer n1, Integer n2) {
            return n1 - n2;
        }
    }
    public class Descending implements Comparator<Integer> {
        public int compare(Integer n1, Integer n2) {
            return n2 - n1;
        }
    }
}

//23. Merge k Sorted Lists
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }
    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (right == 0) {
            return lists[0];
        }
        while (left < right) {
            lists[left] = merge(lists[left], lists[right]);
            left++;
            right--;
        }
        if (left > right) {
            return mergeKLists(lists, 0, left - 1);
        } else {
            return mergeKLists(lists, 0, left);
        }
    }
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode walk = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                walk.next = head1;
                head1 = head1.next;
            } else {
                walk.next = head2;
                head2 = head2.next;
            }
            walk = walk.next;
        }
        if (head1 != null) {
            walk.next = head1;
        } else {
            walk.next = head2;
        }
        return dummy.next;
    }
}

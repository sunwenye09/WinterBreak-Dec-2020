//264. Ugly Number II
class Solution {
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int p2 = 0, p3 = 0, p5 = 0;
        for (int i = 1; i < n; i++) {
            ugly[i] = Math.min(ugly[p2] * 2, Math.min(ugly[p3] * 3, ugly[p5] * 5));
            if (ugly[i] == ugly[p2] * 2) {
                p2++;
            } 
            if (ugly[i] == ugly[p3] * 3) {
                p3++;
            } 
            if (ugly[i] == ugly[p5] * 5) {
                p5++;
            }
        }
        return ugly[n - 1];
    }
}

//146. LRU Cache
class LRUCache {
    class DLinkedNode {
      int key;
      int value;
      DLinkedNode pre;
      DLinkedNode post;
    }

    /**
     * Always add the new node right after head;
     */
    private void addNode(DLinkedNode node) {

      node.pre = head;
      node.post = head.post;

      head.post.pre = node;
      head.post = node;
    }

    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node){
      DLinkedNode pre = node.pre;
      DLinkedNode post = node.post;

      pre.post = post;
      post.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node){
      this.removeNode(node);
      this.addNode(node);
    }

    // pop the current tail. 
    private DLinkedNode popTail(){
      DLinkedNode res = tail.pre;
      this.removeNode(res);
      return res;
    }

    private HashMap<Integer, DLinkedNode> 
    cache = new HashMap<Integer, DLinkedNode>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
      this.count = 0;
      this.capacity = capacity;

      head = new DLinkedNode();
      head.pre = null;

      tail = new DLinkedNode();
      tail.post = null;

      head.post = tail;
      tail.pre = head;
    }

    public int get(int key) {

      DLinkedNode node = cache.get(key);
      if(node == null){
        return -1; // should raise exception here.
      }

      // move the accessed node to the head;
      this.moveToHead(node);

      return node.value;
    }


    public void put(int key, int value) {
      DLinkedNode node = cache.get(key);

      if(node == null){
        DLinkedNode newNode = new DLinkedNode();
        newNode.key = key;
        newNode.value = value;

        this.cache.put(key, newNode);
        this.addNode(newNode);

        ++count;

        if(count > capacity){
          // pop the tail
          DLinkedNode tail = this.popTail();
          this.cache.remove(tail.key);
          --count;
            }
        }else{
        // update the value.
        node.value = value;
        this.moveToHead(node);
      }
    }
}

//349. Intersection of Two Arrays
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        for (int j = 0; j < nums2.length; j++) {
            set2.add(nums2[j]);
        }
        set1.retainAll(set2);
        int[] res = new int[set1.size()];
        int index = 0;
        for (int num : set1) {
            res[index++] = num;
        }
        return res;
    }
}

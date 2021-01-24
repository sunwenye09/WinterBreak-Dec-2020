//138. Copy List with Random Pointer
class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        //copy the nodes
        Node walk = head;
        while (walk != null) {
            Node copyNode = new Node(walk.val);
            map.put(walk, copyNode);
            walk = walk.next;
        }
        
        //copy the links
        walk = head;
        while(walk != null) {
            Node copyNode = map.get(walk);
            copyNode.next = map.get(walk.next);
            copyNode.random = map.get(walk.random);
            walk = walk.next;
        }
        
        return map.get(head);
    }
}

//171. Anagrams
public class Solution {
    /**
     * @param strs: A list of strings
     * @return: A list of strings
     */
    public List<String> anagrams(String[] strs) {
        // write your code here
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);
            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<String>());
            }
            map.get(sortedStr).add(str);
        }
        
        List<String> result = new ArrayList<>();
        for (String sortedStr : map.keySet()) {
            if (map.get(sortedStr).size() > 1) {
                result.addAll(map.get(sortedStr));
            }
        }
        
        return result;
    }
}

//128. Longest Consecutive Sequence
class Solution {
    public int longestConsecutive(int[] nums) {
        int longestStreak = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        for (int num : set) {
            if (!set.contains(num - 1)) {
                int currentNum = num, currentStreak = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }
}

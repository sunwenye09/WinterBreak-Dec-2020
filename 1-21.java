//138. Subarray Sum
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        // write your code here
        HashMap<Integer, Integer> map = new HashMap<>();    //<prefix sum, index>
        int sum = 0;
        map.put(0, -1);
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                ans.add(map.get(sum) + 1);
                ans.add(i);
                break;
            }
            map.put(sum, i);
        }
        return ans;
    }
}

//685. First Unique Number in Data Stream
public class Solution {
    /**
     * @param nums: a continuous stream of numbers
     * @param number: a number
     * @return: returns the first unique number
     */
    public int firstUniqueNumber(int[] nums, int number) {
        // Write your code here
        int count = 1;
        boolean findTerminating = false;
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();    //<number, position>
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                map.remove(nums[i]);
            } else {
                set.add(nums[i]);
                map.put(nums[i], count++);
            }
            if (nums[i] == number) {
                findTerminating = true;
                break;
            }
        }
        
        if (!findTerminating) {
            return -1;
        }
        
        int ans = -1;
        for (int candidate : map.keySet()) {
            if (map.get(candidate) < count) {
                ans = candidate;
                count = map.get(candidate);
            }    
        }
        return ans;
    }
}

//960. First Unique Number in Data Stream II
public class DataStream {
    List<Integer> container;
    HashSet<Integer> set;
    HashMap<Integer, Integer> unique;   //<number, position>
    int position;
    public DataStream(){
        // do intialization if necessary
        container = new ArrayList<>();
        set = new HashSet<>();
        unique = new HashMap<>();
        position = 1;
    }
    /**
     * @param num: next number in stream
     * @return: nothing
     */
    public void add(int num) {
        // write your code here
        container.add(num);
        if (!set.contains(num)) {
            set.add(num);
            unique.put(num, position++);
        } else {
            unique.remove(num);
        }
    }

    /**
     * @return: the first unique number in stream
     */
    public int firstUnique() {
        // write your code here
        int ans = -1, tempPosition = position;
        for (int candidate : unique.keySet()) {
            if (unique.get(candidate) < tempPosition) {
                ans = candidate;
                tempPosition = unique.get(candidate);
            }
        }
        return ans;
    }
}

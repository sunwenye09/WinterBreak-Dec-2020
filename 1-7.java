//680. Split String
public class Solution {
    /*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        // write your code here
        List<List<String>> result = new ArrayList<>();
        if (s == null || s.length() == 0 ) {
            result.add(new ArrayList<String>());
            return result;
        }
        backtrack(s, result, new ArrayList<String>(), 0);
        return result;
    }
    private void backtrack(String str, List<List<String>> result, ArrayList<String> subList, int start) {
        if (start == str.length() - 1) {
            subList.add(str.substring(start, start + 1));
            result.add(new ArrayList<String>(subList));
            subList.remove(subList.size() - 1);
            return;
        }
        subList.add(str.substring(start, start + 1));
        if (start + 1 < str.length()) {
            backtrack(str, result, subList, start + 1);
            subList.remove(subList.size() - 1);
            subList.add(str.substring(start, start + 2));
            if (start + 2 < str.length()) {
                backtrack(str, result, subList, start + 2);
                subList.remove(subList.size() - 1);
            } else if (start + 2 == str.length()) {
                result.add(new ArrayList<String>(subList));
                subList.remove(subList.size() - 1);
                return;
            }
        }
    }
}

//570. Find the Missing Number II
public class Solution {
    /**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    public int findMissing2(int n, String str) {
        // write your code here
        HashSet<Integer> set = new HashSet<>();
        backtrack(n, str, set, 0);
        //System.out.println(set);
        int ans = n + 1;
        for (int i = 1; i <= n; i++) {
            if (!set.contains(i)) {
                ans = i;
                break;
            }
        }
        return ans;
    }
    private boolean backtrack(int n, String str, HashSet<Integer> set, int index) {
        if (index == str.length() - 1) {
            int lastNum = Integer.parseInt(str.substring(index, index + 1));
            if (lastNum <= n && lastNum > 0 && !set.contains(lastNum)) {
                set.add(lastNum);
                if (set.size() == n - 1) {
                    return true;
                }
                set.remove(lastNum);
            }
            return false;
        }
        if (str.charAt(index) == '0') {
            return false;
        }
        int singleDigit = Integer.parseInt(str.substring(index, index + 1));
        if (singleDigit <= n && singleDigit > 0 && !set.contains(singleDigit)) {
            set.add(singleDigit);
            if (index + 1 < str.length()) {
                boolean success = backtrack(n, str, set, index + 1);
                if (success) {
                    return true;
                }
            }
            set.remove(singleDigit);
        }
        
        if (index + 2 < str.length()) {
            int doubleDigit = Integer.parseInt(str.substring(index, index + 2));
            if (doubleDigit <= n && doubleDigit > 0 && !set.contains(doubleDigit)) {
                set.add(doubleDigit);
                boolean success = backtrack(n, str, set, index + 2);
                if (success) {
                    return true;
                }
                set.remove(doubleDigit);
            }
        } else if (index + 2 == str.length()) {
            int lastNum = Integer.parseInt(str.substring(index, index + 2));
            if (lastNum <= n && lastNum > 0 && !set.contains(lastNum)) {
                set.add(lastNum);
                if (set.size() == n - 1) {
                    return true;
                }
                set.remove(lastNum);
            }
        }
        return false;
    }
}

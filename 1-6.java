//78. Subsets
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        getSubsets(result, nums, new ArrayList<Integer>(), 0);
        return result;
    }
    private void getSubsets(List<List<Integer>> result, int[] nums, List<Integer> temp, int start) {
        result.add(new ArrayList<Integer>(temp));
        //System.out.println(temp);
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            getSubsets(result, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}

//90. Subsets II
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(result, nums, new ArrayList<Integer>(), 0);
        return result;
    }
    private void backtrack(List<List<Integer>> result, int[] nums, List<Integer> temp, int start) {
        result.add(new ArrayList<Integer>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            backtrack(result, nums, temp, i + 1);
            temp.remove(temp.size() - 1);
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}

//140. Word Break II
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        HashMap<String, List<String>> map = new HashMap<>();
        map.put("", new ArrayList<String>());
        map.get("").add("");
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j ++) {
                String curr = s.substring(0, i), left = s.substring(0, j), right = s.substring(j, i);
                if (dp[j] && dict.contains(right)) {
                    dp[i] = true;
                    if (!map.containsKey(curr)) {
                        map.put(curr, new ArrayList<String>());
                    }
                    for (String str : map.get(left)) {
                        if (str.equals("")) {
                            map.get(curr).add(right);
                        } else {
                            map.get(curr).add(str + " " + right);
                        }
                    }
                }
            }
        }
        
        if (dp[n]) {
            return map.get(s);
        }
        return new ArrayList<>();
    }
}

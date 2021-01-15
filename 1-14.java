//46. Permutations
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> lst = new ArrayList<>();
        dfs(nums, lst, new ArrayList<Integer>());
        return lst;
    }
    private void dfs(int[] nums, List<List<Integer>> lst, List<Integer> subList) {
        if (subList.size() == nums.length) {
            lst.add(new ArrayList<Integer>(subList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (subList.contains(nums[i])) {
                continue;
            }
            subList.add(nums[i]);
            dfs(nums, lst, subList);
            subList.remove(subList.size() - 1);
        }
    }
}

47. Permutations II
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> lst = new ArrayList<>();
        Arrays.sort(nums);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], 1);
            } else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
        }
        dfs(nums, lst, new ArrayList<Integer>(), map);
        return lst;
    }
    private void dfs(int[] nums, List<List<Integer>> lst, List<Integer> subList, HashMap<Integer, Integer> map) {
        if (subList.size() == nums.length) {
            lst.add(new ArrayList<Integer>(subList));
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(nums[i]) == 0) {
                continue;
            }
            subList.add(nums[i]);
            map.put(nums[i], map.get(nums[i]) - 1);
            dfs(nums, lst, subList, map);
            subList.remove(subList.size() - 1);
            map.put(nums[i], map.get(nums[i]) + 1);
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}

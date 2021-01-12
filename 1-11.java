//77. Combinations
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lst = new ArrayList<>();
        backtrack(n, k, lst, new ArrayList<Integer>(), 1);
        return lst;
    }
    private void backtrack(int n, int k, List<List<Integer>> lst, List<Integer> subList, int start) {
        if (k == 0) {
            lst.add(new ArrayList<Integer>(subList));
            return;
        }
        for (int i = start; i <= n - k + 1; i++) {
            subList.add(i);
            backtrack(n, k - 1, lst, subList, i + 1);
            subList.remove(subList.size() - 1);
        }
    }
}

//39. Combination Sum
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> lst = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, lst, new ArrayList<Integer>(), 0);
        return lst;
    }
    private void backtrack(int[] candidates, int target, List<List<Integer>> lst, List<Integer> subList, int start) {
        if (target == 0) {
            lst.add(new ArrayList<Integer>(subList));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > 0 && target < 0) {
                break;
            } 
            subList.add(candidates[i]);
            backtrack(candidates, target - candidates[i], lst, subList, i);
            subList.remove(subList.size() - 1);
        }
    }
}

//40. Combination Sum II
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> lst = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, lst, new ArrayList<Integer>(), 0);
        return lst;
    }
    private void backtrack(int[] candidates, int target, List<List<Integer>> lst, List<Integer> subList, int start) {
        if (target == 0) {
            lst.add(new ArrayList<Integer>(subList));
            return;
        } else if (target < 0) {
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            } 
            subList.add(candidates[i]);
            backtrack(candidates, target - candidates[i], lst, subList, i + 1);
            subList.remove(subList.size() - 1);
            while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
                i++;
            }
        }
    }
}

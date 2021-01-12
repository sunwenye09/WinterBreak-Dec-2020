//90. k Sum II
public class Solution {
    /*
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param target: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> lst = new ArrayList<>();
        backtrack(A, k, target, 0, lst, new ArrayList<Integer>());
        return lst;
    }
    private void backtrack(int[] A, int k, int target, int index, List<List<Integer>> lst, List<Integer> subList) {
        if (k == 0 && target == 0) {
            lst.add(new ArrayList<Integer>(subList));
            return;
        }
        if (k == 0 || target <= 0) {
            return;
        }
        for (int i = index; i < A.length; i++) {
            subList.add(A[i]);
            backtrack(A, k - 1, target - A[i], i + 1, lst, subList);
            subList.remove(subList.size() - 1);
        }
    }
}

//44. Wildcard Matching
class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        for (int i = 1; i < m + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = dp[i - 1] [j - 1] && ((s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?'));
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[n][m];
    }
}

//10. Regular Expression Matching

class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0]= true;
        for (int j = 1; j < m + 1; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') && dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 2] || ((p.charAt(j - 2) == '.' || s.charAt(i - 1) == p.charAt(j - 2)) && dp[i - 1][j]);
                }
            }
        }
        return dp[n][m];
    }
}

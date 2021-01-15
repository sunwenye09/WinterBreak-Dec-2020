//301. Remove Invalid Parentheses
public List<String> removeInvalidParentheses(String s) {
    List<String> ans = new ArrayList<>();
    remove(s, ans, 0, 0, new char[]{'(', ')'});
    return ans;
}

public void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
    for (int stack = 0, i = last_i; i < s.length(); ++i) {
        if (s.charAt(i) == par[0]) stack++;
        if (s.charAt(i) == par[1]) stack--;
        if (stack >= 0) continue;
        for (int j = last_j; j <= i; ++j)
            if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
        return;
    }
    String reversed = new StringBuilder(s).reverse().toString();
    if (par[0] == '(') // finished left to right
        remove(reversed, ans, 0, 0, new char[]{')', '('});
    else // finished right to left
        ans.add(reversed);
}

//51. N-Queens
class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        List<List<String>> res = new ArrayList<>();
        dfs(board, 0, res);
        return res;
    }
    private void dfs(char[][] board, int col, List<List<String>> res) {
        if (col == board[0].length) {
            res.add(construct(board));
            return;
        }
        
        for (int row = 0; row < board.length; row++) {
            if (!attack(board, row, col)) {
                board[row][col] = 'Q';
                dfs(board, col + 1, res);
                board[row][col] = '.';
            }
        }
    }
    private boolean attack(char[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'Q' && (i == row || Math.abs(i - row) == Math.abs(j - col))) {
                    return true;
                }
            }
        }
        return false;
    }
    private List<String> construct(char[][] board) {
        List<String> lst = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String str = new String(board[i]);
            lst.add(str);
        }
        return lst;
    }
}

//52. N-Queens II
class Solution {
    public int totalNQueens(int n) {
        int[][] board = new int[n][n];  //0: empty; 1: Q
        int[] res = new int[]{0};
        dfs(board, res, 0);
        return res[0];
    }
    private void dfs(int[][] board, int[] res, int col) {
        if (col == board[0].length) {
            res[0]++;
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (!attack(board, row, col)) {
                board[row][col] = 1;
                dfs(board, res, col + 1);
                board[row][col] = 0;
            }
        }
    }
    private boolean attack(int[][] board, int row, int col) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1 && (i == row || Math.abs(i - row) == Math.abs(j - col))) {
                    return true;
                }
            }
        }
        return false;
    }
}

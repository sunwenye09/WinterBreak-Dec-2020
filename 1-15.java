//567. Permutation in String
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int[] chars1 = new int[26], chars2 = new int[26];
        for (int i = 0; i < n1; i++) {
            chars1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n2; i++) {
            chars2[s2.charAt(i) - 'a']++;
            if (i >= n1) {
                chars2[s2.charAt(i - n1) - 'a']--;
            }
            if (i >= n1 - 1 && Arrays.equals(chars1, chars2)) {
                return true;
            }
        }
        return false;
    }
}

//79. Word Search
class Solution {
    public boolean exist(char[][] board, String word) {
        boolean[] ans = new boolean[]{false};
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, word, ans, 0, i, j, visited);
                if (ans[0]) {
                    return true;
                }
            }
        }
        return ans[0];
    }
    private void dfs(char[][] board, String word, boolean[] ans, int index, int row, int col, boolean[][] visited) {
        if (index == word.length()) {
            ans[0] = true;
            return;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        } else if (visited[row][col] || board[row][col] != word.charAt(index)) {
            return;
        } else if (ans[0]) {
            return;
        }
        visited[row][col] = true;
        dfs(board, word, ans, index + 1, row + 1, col, visited);
        dfs(board, word, ans, index + 1, row - 1, col, visited);
        dfs(board, word, ans, index + 1, row, col + 1, visited);
        dfs(board, word, ans, index + 1, row, col - 1, visited);
        visited[row][col] = false;
    }
}

//212. Word Search II
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (exist(board, words[i])) {
                res.add(words[i]);
            }
        }
        return res;
    }
    private boolean exist(char[][] board, String word) {
        boolean[] ans = new boolean[]{false};
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, word, ans, 0, i, j, visited);
                if (ans[0]) {
                    return true;
                }
            }
        }
        return ans[0];
    }
    private void dfs(char[][] board, String word, boolean[] ans, int index, int row, int col, boolean[][] visited) {
        if (index == word.length()) {
            ans[0] = true;
            return;
        }
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
            return;
        } else if (visited[row][col] || board[row][col] != word.charAt(index)) {
            return;
        } else if (ans[0]) {
            return;
        }
        visited[row][col] = true;
        dfs(board, word, ans, index + 1, row + 1, col, visited);
        dfs(board, word, ans, index + 1, row - 1, col, visited);
        dfs(board, word, ans, index + 1, row, col + 1, visited);
        dfs(board, word, ans, index + 1, row, col - 1, visited);
        visited[row][col] = false;
    }
}

//862. Next Closest Time
public class Solution {
    /**
     * @param time: the given time
     * @return: the next closest time
     */
    public String nextClosestTime(String time) {
        // write your code here
        int target = 0;
        int[] arr = new int[4];
        int index = 0;
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;
            }
            int digit = time.charAt(i) - '0';
            arr[index++] = digit;
            target = target * 10 + digit;
        }
        Arrays.sort(arr);
        List<Integer> lst = new ArrayList<>();
        getAllTimes(arr, lst, 0, 0);
        int candidate = 0, difference = Integer.MAX_VALUE;
        boolean update = false;
        for (int i = 0; i < lst.size(); i++) {
            int curr = lst.get(i);
            if (curr == target) {
                continue;
            } else if (curr < target && (curr + 2400 - target) < difference) {
                difference = curr + 2400 - target;
                candidate = curr;
                update = true;
            } else if (curr > target && (curr - target) < difference) {
                difference = curr - target;
                candidate = curr;
                update = true;
            }
        }
        //System.out.println(lst);
        //System.out.println(target);
        if (!update) {
            return time;
        } else {
            String hour = "" + candidate / 100, minute = "" + candidate % 100;
            if (hour.length() < 2) {
                hour = "0" + hour;
            }
            if (minute.length() < 2) {
                minute = "0" + minute;
            }
            return hour + ":" + minute;
        }
    }
    private void getAllTimes(int[] arr, List<Integer> lst, int sum, int numOfDigit) {
        if (numOfDigit == arr.length) {
            if (sum / 100 >= 24 || sum % 100 >= 60) {
                return;
            }
            lst.add(sum);
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            sum = sum * 10 + arr[i];
            getAllTimes(arr, lst, sum, numOfDigit + 1);
            sum = (sum - arr[i]) / 10;
            while (i + 1 < arr.length && arr[i] == arr[i + 1]) {
                i++;
            }
        }
    }
}

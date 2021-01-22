//17. Letter Combinations of a Phone Number
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> lst = new ArrayList<>();
        if (digits.length() == 0) {
            return lst;
        }
        HashMap<Character, String> dict = generateDict();
        dfs(digits, lst, dict, 0, new StringBuilder());
        return lst;
    }
    private void dfs(String digits, List<String> lst, HashMap<Character, String> dict, int start, StringBuilder sb) {
        if (start == digits.length()) {
            lst.add(sb.toString());
            return;
        }
            String letters = dict.get(digits.charAt(start));
            for (int j = 0; j < letters.length(); j++) {
                sb.append(letters.charAt(j));
                dfs(digits, lst, dict, start + 1, sb);
                sb.deleteCharAt(sb.length() - 1);
            }
    }
    private HashMap<Character, String> generateDict() {
        HashMap<Character, String> dict = new HashMap<>();
        dict.put('2', "abc");
        dict.put('3', "def");
        dict.put('4', "ghi");
        dict.put('5', "jkl");
        dict.put('6', "mno");
        dict.put('7', "pqrs");
        dict.put('8', "tuv");
        dict.put('9', "wxyz");
        return dict;
    }
}

//829. Word Pattern II
public class Solution {
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
     boolean match = false;
    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        HashMap<Character, String> map = new HashMap<>();
        dfs(pattern, str, map, 0, new HashSet<String>());
        return match;
    }
    private void dfs(String pattern, String str, HashMap<Character, String> map, int pIndex, HashSet<String> set) {
        if (pIndex == pattern.length() && str.length() > 0) {
            return;
        } else if (pIndex < pattern.length() && str.length() == 0) {
            return;
        } else if (pIndex == pattern.length() && str.length() == 0) {
            match = true;
            return;
        }
        
        char letter = pattern.charAt(pIndex);
        for (int i = 0; i < str.length(); i++) {
            String word = str.substring(0, i + 1);
            if (!map.containsKey(letter) && !set.contains(word)) {
                map.put(letter, word);
                set.add(word);
                dfs(pattern, str.substring(i + 1, str.length()), map, pIndex + 1, set);
                if (match) {
                    return;
                }
                set.remove(word);
                map.remove(letter);
            } else if (!map.containsKey(letter) && set.contains(word)) {
                continue;
            } else if (map.containsKey(letter) && map.get(letter).equals(word)){
                dfs(pattern, str.substring(i + 1, str.length()), map, pIndex + 1, set);
            }
        }
    }
}

//126. Word Ladder II
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ladders = new ArrayList<>();
        HashSet<String> dict = new HashSet(wordList);
        if (!dict.contains(endWord)) {
            return ladders;
        }
        Map<String, List<String>> neighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        dict.add(beginWord);
        //bfs to find distances of all nodes. end -> start
        bfs(neighbors, distance, endWord, beginWord, dict);
        //dfs to find all ladders. start -> end
        List<String> path = new ArrayList<>();
        dfs(ladders,path, beginWord, endWord, distance, neighbors);
        
        return ladders;
    }
    
    private void dfs(List<List<String>> ladders, List<String> path, String start, String end, Map<String, Integer> distance, Map<String, List<String>> neighbors) {
        path.add(start);
        if (start.equals(end)) {
            ladders.add(new ArrayList<String>(path));
        } else {
            for (String word : neighbors.get(start)) {
                if (distance.containsKey(word) && distance.get(start) - 1 == distance.get(word)) {
                    dfs(ladders, path, word, end, distance, neighbors);
                }
            }
        }
        //backtrack
        path.remove(path.size() - 1);
    }
    
    private void bfs(Map<String, List<String>> neighbors, Map<String, Integer> distance, String start, String end, HashSet<String> dict) {
        Queue<String> queue = new LinkedList<String>();
        //initiallization
        queue.offer(start);
        distance.put(start, 0);
        for (String s : dict) {
            neighbors.put(s, new ArrayList<String>());
        }
        //build neighbors and calculate distances
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            List<String> neighbor = getNeighbor(curr, dict);
            for (String word : neighbor) {
                neighbors.get(word).add(curr);
                if (distance.containsKey(word)) {
                    continue;
                }
                distance.put(word, distance.get(curr) + 1);
                queue.offer(word);
            }
        }
    }
    private List<String> getNeighbor(String word, Set<String> dict) {
        List<String> neighbor = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String s = word.substring(0, i) + c + word.substring(i + 1);
                if (dict.contains(s)) {
                    neighbor.add(s);
                }
            }
        }
        return neighbor;
    }
}

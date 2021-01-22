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

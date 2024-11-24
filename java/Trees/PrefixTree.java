package Trees;

import java.util.*;

// todo
public class PrefixTree {
    private class TrieNode {
        Map<Character, TrieNode> map = new HashMap<>();
        Boolean isWordUntil = false;
    }

    private TrieNode root;
    private int count;

    public PrefixTree() {
        root = new TrieNode();
        count = 0;
    }

    public void addWord(String word) {
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            var letter = word.charAt(i);
            if (temp.map.containsKey(letter)) temp.map.put(letter, new TrieNode());
            temp = temp.map.get(letter);
        }
        temp.isWordUntil = true;
    }

    public void removeWord(String word) {
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            var letter = word.charAt(i);
            if (temp.map.containsKey(letter)) temp.map.put(letter, new TrieNode());
            temp = temp.map.get(letter);
        }
        temp.isWordUntil = false;
    }

    public boolean containsWord(String word) {
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            var letter = word.charAt(i);
            if (temp.map.containsKey(letter)) temp.map.put(letter, new TrieNode());
            temp = temp.map.get(letter);
        }
        return temp.isWordUntil;
    }

    public int countWords() {
        return count;
    }

    public int countWords(String prefix) {
        return 0;
    }

    public boolean hasPrefix(String prefix) {
        var temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            var letter = prefix.charAt(i);
            if (!temp.map.containsKey(letter)) return false;
            temp = temp.map.get(letter);
        }
        return true;
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> ans = new LinkedList<>();
        var temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            var letter = prefix.charAt(i);
            if (!temp.map.containsKey(letter)) return ans;
            temp = temp.map.get(letter);
        }
        return ans;
    }

    private List<String> helperGetWords(TrieNode node, String prefix) {



        return null;
    }
}

package Trees;

import java.util.*;

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
            if (!temp.map.containsKey(letter)) temp.map.put(letter, new TrieNode());
            temp = temp.map.get(letter);
        }
        temp.isWordUntil = true;
        count++;
    }

    public void removeWord(String word) {
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            var letter = word.charAt(i);
            if (!temp.map.containsKey(letter)) return;
            temp = temp.map.get(letter);
        }
        temp.isWordUntil = false;
        count--;
    }

    public boolean containsWord(String word) {
        var temp = root;
        for (int i = 0; i < word.length(); i++) {
            var letter = word.charAt(i);
            if (!temp.map.containsKey(letter)) return false;
            temp = temp.map.get(letter);
        }
        return temp.isWordUntil;
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


    public int countWords() {
        return count;
    }

    public int countWords(String prefix) {
        return getWordsWithPrefix(prefix).size();
    }

    public List<String> getWordsWithPrefix(String prefix) {
        List<String> ans = new LinkedList<>();
        var temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            var letter = prefix.charAt(i);
            if (!temp.map.containsKey(letter)) return ans;
            temp = temp.map.get(letter);
        }
        if (temp.isWordUntil) ans.add(prefix);
        temp.map.forEach((k, v) -> {
            helperGetWords(v, prefix + k, ans);
        });
        return ans;
    }

    private void helperGetWords(TrieNode node, String prefix, List<String> ans) {
        if (node.isWordUntil) ans.add(prefix);
        node.map.forEach((k, v) -> {
            helperGetWords(v, prefix + k, ans);
        });
    }

}

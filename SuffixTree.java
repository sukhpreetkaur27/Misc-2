// Ran of Leetcode: Yes

/**
 * We have to search from the rear end so build a suffix tree (i.e. inverted Trie as Trie == prefix Tree).
 * <p>
 * Maintain a StringBuilder for the stream of characters.
 * <p>
 * In the worst case, the search would take O(l), l = length of the longest word in the words list
 */
public class SuffixTree {
    private Trie trie;
    private StringBuilder sb;
    private String[] words;

    public SuffixTree(String[] words) {
        this.words = words;
        this.sb = new StringBuilder();
        this.trie = new Trie();
        buildSuffixTree();
    }

    // search the stream for suffix
    public boolean suffixSearch(char ch) {
        sb.append(ch);
        return this.trie.search(sb);
    }

    // building the suffix tree
    // TC: O(n * k)
    private void buildSuffixTree() {
        for (String word : words) {
            this.trie.insertSuffix(word);
        }
    }

}

class Trie {
    class Node {
        Node[] links;
        boolean start;

        Node() {
            links = new Node[26];
        }

        void setStart(boolean start) {
            this.start = start;
        }

        boolean isStart() {
            return this.start;
        }

        // put char
        Node putKey(char ch) {
            Node node = new Node();
            links[ch - 'a'] = node;
            return node;
        }

        // get char
        Node getKey(char ch) {
            return links[ch - 'a'];
        }

        // check char
        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }
    }

    private static Node root;

    Trie() {
        root = new Node();
    }

    void insertSuffix(String word) {
        Node node = root;

        for (int i = word.length() - 1; i >= 0; i--) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node = node.putKey(ch);
            } else {
                node = node.getKey(ch);
            }
        }
        node.setStart(true);
    }

    boolean search(StringBuilder suffix) {
        Node node = root;
        for (int i = suffix.length() - 1; i >= 0; i--) {
            char ch = suffix.charAt(i);
            if (!node.containsKey(ch)) {
                return false;
            }
            node = node.getKey(ch);
            if (node.isStart()) {
                return true;
            }
        }
        return false;
    }

}
package trees;

import java.util.ArrayList;
import java.util.HashMap;

/*

Find shortest unique prefix to represent each word in the list.

Example:

Input: [zebra, dog, duck, dove]
Output: {z, dog, du, dov}
where we can see that
zebra = z
dog = dog
duck = du
dove = dov
 NOTE : Assume that no word is prefix of another. In other words, the representation is always possible.
 */

public class ShortestUPrefix {

    public ArrayList<String> prefix(ArrayList<String> A) {

        // initialize new Trie
        TrieNode prefixTree = new TrieNode(Character.MIN_VALUE);

        // add all words to Trie
        for(String s: A) prefixTree.addWord(s);

        ArrayList<String> res = new ArrayList<>();

        // get shortest unique prefix for each word and add to result
        for(String s: A) res.add(prefixTree.getMinUPrefix(s));

        return res;

    }

    // Trie is a special tree with nodes
    // each node has a value, a character frequency (which is incremented everytime the value is visited),
    // and a hashmap which leads to multiple possible nodes based on multiple possible characters
    private static class TrieNode{

        private HashMap<Character, TrieNode> children;
        private char val;
        private int charFreq;

        public TrieNode(char val){

            this.val = val;
            this.children = new HashMap<>();
            this.charFreq = 1;
        }


        // adds a word to the trie
        public void addWord(String s){

            if (s.isEmpty()) return;

                TrieNode curr = this;

            for(int i = 0; i < s.length(); i++){

                char c = s.charAt(i);

                // if none of the current node's kids have c, add c to current node's kids
                if (!curr.children.containsKey(c))
                    curr.children.put(c, new TrieNode(c));

                // if current node has c as a kid, increment its frequency
            else curr.children.get(c).charFreq++;

            // move on to the node that corresponds to c in the current node's kids (characters)
                curr = curr.children.get(c);

            }

        }



        // takes a String and returns its minimum unique prefix after examining this trie
        public String getMinUPrefix(String s){

            if (s.isEmpty()) return s;

            TrieNode curr = this;
            StringBuilder str = new StringBuilder();

            for(int i = 0; i < s.length(); i++){

                char c = s.charAt(i);

                    // the String formed till now is unique because this character
                // appears only in one word, it's unique
                    if (curr.children.get(c).charFreq == 1) {
                        str.append(c);
                        return str.toString();
                    }


                    // the String formed so far is not unique as the current character appears in more
                    // than one word, move on to the next character
                    else{
                        str.append(c);
                        curr = curr.children.get(c);
                    }

            }

            return str.toString();

        }

        @Override
        public String toString() {

            StringBuilder str = new StringBuilder();

            str.append(this.val);
            str.append(", Children: ");
            str.append(children);

            return str.toString();
        }
    }


    public static void main(String[] args) {

        ShortestUPrefix shortestUPrefix = new ShortestUPrefix();

        String[] arr = {"zebra", "dog", "duck", "dove"};

        ArrayList<String> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++) a.add(arr[i]);


//        TrieNode trieNode = new TrieNode(Character.MIN_VALUE);

//        trieNode.addWord("abc");

        System.out.println(shortestUPrefix.prefix(a));
    }
}

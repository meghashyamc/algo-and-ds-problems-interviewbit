package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*

Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

You must change exactly one character in every transformation
Each intermediate word must exist in the dictionary
Example :

Given:

start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note that we account for the length of the transformation path instead of the number of transformation itself.

 Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
 */

public class WordLadder {

    private HashMap<String, Boolean> visited = new HashMap<>();
    private HashMap<String, Integer> depths = new HashMap<>();

    public int ladderLength(String start, String end, ArrayList<String> dictV) {


        if (start.equals(end))
            return 1;


        HashMap<String, ArrayList<String>> adjacents = new HashMap<>();

        if (!dictV.contains(start))
            dictV.add(start);
        if (!dictV.contains(end))
            dictV.add(end);
        getAdjacents(dictV, adjacents);

        return ladderLength(start, end, adjacents);

    }

    // adjacents stores all Strings part of the given dictionary that can be obtained by changing one letter of the given String
    private void getAdjacents (ArrayList<String> dictV, HashMap<String, ArrayList<String>> adjacents) {


        for (int i = 0; i < dictV.size()-1; i++) {
            for (int j = i+1; j < dictV.size(); j++) {

                String s1 = dictV.get(i);
                String s2 = dictV.get(j);
                if (isAdjacent(s1, s2)) {

                    if (adjacents.containsKey(s1))
                        adjacents.get(s1).add(s2);
                    else {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(s2);
                    adjacents.put(s1, temp);
                    }


                    if (adjacents.containsKey(s2))
                        adjacents.get(s2).add(s1);
                    else {
                        ArrayList<String> temp = new ArrayList<>();
                        temp.add(s1);
                        adjacents.put(s2, temp);
                    }
                }

                else{

                    if (!adjacents.containsKey(s1)){

                        ArrayList<String> temp = new ArrayList<>();

                        adjacents.put(s1, temp);
                    }

                }

            }

        }
    }

// can you obtain s2 by changing one character of s1?
    private boolean isAdjacent(String s1, String s2){


        int change = 0;

        for(int i = 0; i < s1.length(); i++){

            if (s1.charAt(i) != s2.charAt(i)) change++;
        }

        return (change == 1);
    }

    // overloaded method that uses breadth first traversal to go from start to end
    private int ladderLength(String start, String end, HashMap<String, ArrayList<String>> adjacents) {

// breadth first traversal
        Queue<String> q = new LinkedList<>();
        q.add(start);
        // depths stores the distance of any string from start
        depths.put(start, 1);

        while(!q.isEmpty()) {

            String curr = q.poll();
            visited.put(curr, true);
            if (curr.equals(end))
                return depths.get(curr);



        if (adjacents.get(curr).isEmpty()) continue;
        // add kids (adjacents) to queue after updating their depths if they haven't visited
            for (int i = 0; i < adjacents.get(curr).size(); i++) {

                String kid = adjacents.get(curr).get(i);
                if (!visited.containsKey(kid)){

                    depths.put(kid, depths.get(curr)+1);
                    q.add(kid);

                }


            }

        }

        return 0;

    }

    public static void main(String[] args) {

        WordLadder wordLadder = new WordLadder();

        ArrayList<String> dict = new ArrayList<>();

        String s = "bbaba babaa abbaa bbbbb bbbab bbaaa bbbab aaabb babbb bbaaa bbaaa bbbba baabb abaab bbaaa bbbaa baabb abbaa aaaba abaaa abbba aaaab baaaa abaaa abaab aaabb bbaab babbb ababa ";

        StringBuilder str = new StringBuilder();

        for(int i = 0; i < s.length(); i++){

            if (s.charAt(i) != ' ')
            str.append(s.charAt(i));
            else{

                dict.add(str.toString());
                str = new StringBuilder();
            }
        }





        System.out.println(wordLadder.ladderLength("aaaab", "bbabb", dict));
    }
}

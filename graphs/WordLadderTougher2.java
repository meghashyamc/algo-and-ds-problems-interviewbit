package test.graphs;

import java.util.*;
/*
Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
If there are multiple such sequence of shortest length, return all of them. Refer to the example for more details.

Example :

Given:

start = "hit"
end = "cog"
dict = ["hot","dot","dog","lot","log"]
Return

  [
    ["hit","hot","dot","dog","cog"],
    ["hit","hot","lot","log","cog"]
  ]
 Note:
All words have the same length.
All words contain only lowercase alphabetic characters.*/


public class WordLadderTougher2 {

    private HashMap<String, Integer> cache = new HashMap<>();
    public ArrayList<ArrayList<String>> findLadders(String start, String end, ArrayList<String> dict) {


        ArrayList<ArrayList<String>> ans = new ArrayList<>();
        if (start.equals(end)){

            ArrayList<String> minTransforms1 = new ArrayList<>();
            minTransforms1.add(start);
            ans.add(minTransforms1);
            return ans;
        }


        HashMap<String, HashSet<String>> adjacents = new HashMap<>();

        if (!dict.contains(start))
            dict.add(start);
        if (!dict.contains(end))
            dict.add(end);
        getAdjacents(dict, adjacents);

        // get minimum steps in which end can be reached from start
        int minLength = ladderLength(start, end, adjacents);

        // we can't reach end ever
        if (minLength == 0) return ans;

        ArrayList<String> path = new ArrayList<>();
        path.add(start);

        // update ans array
        populateAns(ans, path, minLength, start, end, adjacents);

        return ans;
    }

    // updates ans array
    private void populateAns(ArrayList<ArrayList<String>> ans, ArrayList<String> path, int minLength, String start, String end, HashMap<String, HashSet<String>> adjacents){

        // max possible steps in reaching end have been exhausted
        if (minLength < 0) return;

        // we got a hit!
        // add path to ans array
        if (start.equals(end)){

            ArrayList<String> temp = new ArrayList<>(path);
            ans.add(temp);
            return;
        }


        HashMap<Integer, ArrayList<String>> selected = new HashMap<>();

        // select only those adjacents whose steps till end are what they should be (minPath - 1)
        for(String adj: adjacents.get(start)){

         int lens = ladderLength(adj, end, adjacents);
         if (minLength-1 == lens) {
             if (selected.containsKey(minLength))
             selected.get(minLength).add(adj);
             else {
                 ArrayList<String> temp = new ArrayList<>();
                 temp.add(adj);
                 selected.put(minLength, temp);
             }
             }
        }

        // no adjacent can reach end in given steps
        if (!selected.containsKey(minLength)) return;


        for(int i = 0; i < selected.get(minLength).size(); i++){

            ArrayList<String> newPath = new ArrayList<>(path);
            newPath.add(selected.get(minLength).get(i));
            populateAns(ans, newPath, minLength-1,selected.get(minLength).get(i), end, adjacents);
        }



    }







    // adjacents stores all Strings part of the given dictionary that can be obtained by changing one letter of the given String
    private void getAdjacents (ArrayList<String> dictV, HashMap<String, HashSet<String>> adjacents) {


        for (int i = 0; i < dictV.size()-1; i++) {
            for (int j = i+1; j < dictV.size(); j++) {

                String s1 = dictV.get(i);
                String s2 = dictV.get(j);
                if (isAdjacent(s1, s2)) {

                    if (adjacents.containsKey(s1))
                        adjacents.get(s1).add(s2);
                    else {
                        HashSet<String> temp = new HashSet<>();
                    temp.add(s2);
                    adjacents.put(s1, temp);
                    }


                    if (adjacents.containsKey(s2))
                        adjacents.get(s2).add(s1);
                    else {
                        HashSet<String> temp = new HashSet<>();
                        temp.add(s1);
                        adjacents.put(s2, temp);
                    }
                }

                else{

                    if (!adjacents.containsKey(s1)){

                        HashSet<String> temp = new HashSet<>();

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
    private int ladderLength(String start, String end, HashMap<String, HashSet<String>> adjacents) {

       if (cache.containsKey(start)) return cache.get(start);
        HashMap<String, Boolean> visited = new HashMap<>();
        HashMap<String, Integer> depths = new HashMap<>();
// breadth first traversal
        Queue<String> q = new LinkedList<>();
        q.add(start);
        // depths stores the distance of any string from start
        depths.put(start, 1);
        visited.put(start, true);


        while(!q.isEmpty()) {

            String curr = q.poll();
//            System.out.println("curr is " + curr + ", depth is " + depths.get(curr));
            if (curr.equals(end)) {
             cache.put(start, depths.get(curr));
             return cache.get(start);


            }



            if (adjacents.get(curr).isEmpty()) continue;
            // add kids (adjacents) to queue after updating their depths if they haven't visited
            for (String kid: adjacents.get(curr)) {
                if (!visited.containsKey(kid)){
                    visited.put(kid, true);

//                    System.out.println("kid " + kid + " added to queue with depth " + (depths.get(curr)+1));
                    depths.put(kid, depths.get(curr)+1);
                    q.add(kid);

                }


            }

        }

        cache.put(start, 0);
        return 0;

    }







    public static void main(String[] args) {

        WordLadderTougher2 wordLadderTougher2 = new WordLadderTougher2();

        ArrayList<String> dict = new ArrayList<>();

//        String s = "hot dot dog lot log ";
        String s = "baba abba aaba bbbb abaa abab aaab abba abba abba bbba aaab abaa baba baaa ";
        StringBuilder str = new StringBuilder();

        for(int i = 0; i < s.length(); i++){

            if (s.charAt(i) != ' ')
            str.append(s.charAt(i));
            else{

                dict.add(str.toString());
                str = new StringBuilder();
            }
        }


        System.out.println(wordLadderTougher2.findLadders("bbaa", "babb", dict));
    }
}

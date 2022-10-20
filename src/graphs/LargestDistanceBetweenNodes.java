package graphs;

/*

Find largest distance
Given an arbitrary unweighted rooted tree which consists of N (2 <= N <= 40000) nodes. The goal of the problem is to find largest distance between two nodes in a tree. Distance between two nodes is a number of edges on a path between the nodes (there will be a unique path between any pair of nodes since it is a tree). The nodes will be numbered 0 through N - 1.

The tree is given as an array P, there is an edge between nodes P[i] and i (0 <= i < N). Exactly one of the i’s will have P[i] equal to -1, it will be root node.

 Example:
If given P is [-1, 0, 0, 0, 3], then node 0 is the root and the whole tree looks like this:
          0
       /  |  \
      1   2   3
               \
                4
 One of the longest path is 1 -> 0 -> 3 -> 4 and its length is 3, thus the answer is 3. Note that there are other paths with maximal distance.
 */

import java.util.*;

public class LargestDistanceBetweenNodes {

    // modus operandi: first, find the node farthest from the root - let's call this node1
    // next, find the node farthest from node1

        private HashMap<Integer, ArrayList<Integer>> getKids = new HashMap<>();
        private HashSet<Integer> visited = new HashSet<>();
        int[] distances;
       public int solve(ArrayList<Integer> A) {

        int n = A.size();

        int root = getRoot(A);
        getKidsFromArray(A, getKids);

        distances = new int[A.size()];
           setToMinusOne(distances);
           int farthest1 = getFarthest(A, root);
        setToMinusOne(distances);
        visited.clear();

        int farthest2 = getFarthest(A, farthest1);

           return distances[farthest2];

    }

    private void setToMinusOne(int[] arr){

           for(int i = 0; i < arr.length; i++)
               arr[i] = -1;

    }



    // stores parents and their kids in a hashmap for easy access to kids
    private void getKidsFromArray(ArrayList<Integer> A, HashMap<Integer, ArrayList<Integer>> getKids){

        for(int i = 0; i < A.size(); i++){

            // the root has no parent
            if (A.get(i).equals(-1)) continue;

            if (!getKids.containsKey(A.get(i))) {
             ArrayList<Integer> temp = new ArrayList<>();

                 temp.add(i);
                getKids.put(A.get(i), temp);
            }

            else
                getKids.get(A.get(i)).add(i);

        }
    }


    private int getRoot(ArrayList<Integer> A){

        for(int i = 0; i < A.size(); i++){

            if (A.get(i).equals(-1))
                return i;
        }

        return -1;
    }

    // returns the node farthest from the node given
    private int getFarthest(ArrayList<Integer> A, int node) {


        Queue<Integer> q = new LinkedList<>();

        q.add(node);
        // stores distance of node considered from input node
        distances[node] = 0;
        visited.add(node);

        // breadth first traversal starting from node
        while(!q.isEmpty()){

        int curr = q.poll();

        // add parent to queue
        int parent = A.get(curr);
        if ((!visited.contains(parent))
            && (parent != -1)) {
            q.add(parent);
            distances[parent] = distances[curr] + 1;
            visited.add(parent);
        }

        // curr has no kids
            if (!getKids.containsKey(curr)) continue;

//            System.out.println(curr + "'s kids: " + getKids.get(curr));
            // add curr's kids to queue if not visited
            for(int i = 0; i < getKids.get(curr).size(); i++){

                int kid = getKids.get(curr).get(i);

                if (!visited.contains(kid)){

                    q.add(kid);
                    distances[kid] = distances[curr] + 1;
                    visited.add(kid);
                }
            }

        }

        int max = 0;
        int maxI = 0; // node with the greatest distance from input node

        for(int i = 0; i < distances.length; i++){

            if (max < distances[i]) {
                max = distances[i];
                maxI = i;
            }
        }

return maxI;
    }


    public static void main(String[] args) {

        ArrayList<Integer> tree = new ArrayList<>();

        int[] arr = {-1, 0, 0, 0, 3};

        for(int i = 0; i < arr.length; i++)
            tree.add(arr[i]);

        LargestDistanceBetweenNodes largestDistanceBetweenNodes = new LargestDistanceBetweenNodes();
        System.out.println(largestDistanceBetweenNodes.solve(tree));

    }


}





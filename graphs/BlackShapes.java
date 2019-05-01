
import java.util.*;

/*

Given N * M field of O's and X's, where O=white, X=black
Return the number of black shapes. A black shape consists of one or more adjacent X's (diagonals not included)

Example:

OOOXOOO
OOXXOXO
OXOOOXO

answer is 3 shapes are  :
(i)    X
     X X
(ii)
      X
 (iii)
      X
      X
Note that we are looking for connected shapes here.

For example,

XXX
XXX
XXX
is just one single connected black shape.
 */

public class BlackShapes {

   private boolean[] visited;

   // used to make sure the same edges aren't stores again and again
   private HashSet<Edge> cacheEdges = new HashSet<>();

   private class Edge{

       int src;
       int dest;
       public Edge(int src, int dest){
           this.src = src;
           this.dest = dest;
       }

       @Override
       public boolean equals(Object obj) {
           Edge temp = (Edge) obj;
           return (this.src == temp.src) && (this.dest == temp.dest);
       }

       @Override
       public int hashCode() {
           return Objects.hash(src, dest);
       }
   }
    private  class Graph
    {
        // V is number of vertices
        int V;
        private LinkedList<Integer>[] adjListArray;

        public Graph(int V)
        {
            this.V = V;

            adjListArray = new LinkedList[V];

            // Create a new linked list for each vertex
            // the linked list stores vertices adjacent to this one
            for(int i = 0; i < V ; i++){
                adjListArray[i] = new LinkedList<>();
            }
        }

        private void addEdge(int src, int dest)
        {

            Edge e = new Edge(src, dest);
            Edge e1 = new Edge(dest, src);
            if (cacheEdges.contains(e)) return;
            if (cacheEdges.contains(e1)) return;
            this.adjListArray[src].add(dest);
            this.adjListArray[dest].add(src);
            cacheEdges.add(e);
            cacheEdges.add(e1);
        }
    }




    public int black(ArrayList<String> A) {

int m = A.size();
int n = A.get(0).length();

// represents if an 'X' has been visited
 visited = new boolean[m*n];

// initially no 'X' has been visited
for(int i = 0; i < m*n; i++)
    visited[i] = false;


        Graph xGraph = new Graph(m*n);

        // form connections in the graph, if a character is X, connect it to the X above it
        // below it, to the left of it, to the right of it
        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (A.get(i).charAt(j) == 'X'){

                    int thisVertex = n*i+j;
                    int leftVertex = thisVertex-1;
                    int rightVertex = thisVertex+1;
                    int topVertex = n*(i-1) + j;
                    int botVertex = n*(i+1) + j;
                    if ((i !=0 ) && (A.get(i-1).charAt(j) == 'X')) {
                        xGraph.addEdge(thisVertex, topVertex);
                    }
                    if ((j != 0) && (A.get(i).charAt(j-1) == 'X')) {

                        xGraph.addEdge(thisVertex, leftVertex);

                    }
                    if ((i != m-1) && (A.get(i+1).charAt(j) == 'X')){
                        xGraph.addEdge(thisVertex, botVertex);


                    }


                    if ((j != n-1) && (A.get(i).charAt(j+1) == 'X')){
                        xGraph.addEdge(thisVertex, rightVertex);


                    }



                }

            }
        }



      int xCounter = 0;

        for(int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                // marks all possible connections, connections of connections etc as visited when an X is found
                if ((A.get(i).charAt(j) == 'X') && (!visited[n*i+j])){

                    addAllConnections(xGraph, i*n+j);
                        xCounter++;

                    }

                }

            }


            return xCounter;
    }

    // visited[i] is given the value true for all i that are in any way reachable from v
    private void addAllConnections(Graph xGraph, int v){


        if (xGraph.adjListArray[v].isEmpty()){

            visited[v] = true;
            return;
        }

        Stack<Integer> dfsStack = new Stack<>();

        dfsStack.push(v);

        while(!dfsStack.isEmpty()){

            int curr = dfsStack.pop();

            visited[curr] = true;
//            System.out.println("added " + curr);

            for(Integer i: xGraph.adjListArray[curr]){

                if (!visited[i])
                    dfsStack.push(i);
            }
        }

    }


    public static void main(String[] args) {

        ArrayList<String> a = new ArrayList<>();

       String[] arr = {"XOOOOOXXOX", "OOXXXXOOXX", "XXOXXOOXXO", "OXOXXXXXXO", "XOXXOXOXXX", "OOOOOOOXOO", "XOXXXOOXOX", "XXXOXOXXXO"};

        for(int i = 0; i < arr.length; i++)
        a.add(arr[i]);


        BlackShapes blackShapes = new BlackShapes();
        System.out.println(blackShapes.black(a));

        /*
        XOOOOOXXOX
        OOXXXXOOXX
        XXOXXOOXXO
        OXOXXXXXXO
        XOXXOXOXXX
        OOOOOOOXOO
        XOXXXOOXOX
        XXXOXOXXXO






         */


    }
}

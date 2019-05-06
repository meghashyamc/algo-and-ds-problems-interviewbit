package test.graphs;

import java.util.*;

/*
Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
 */

public class CloneGraph {

    private HashSet<UndirectedGraphNode> visited = new HashSet<>();
    private HashMap<UndirectedGraphNode, UndirectedGraphNode> origCloneMap = new HashMap<>();


    private static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<UndirectedGraphNode>();
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder(label + ": ");

            for(int i = 0; i < neighbors.size(); i++)
            str.append(neighbors.get(i).label + " ");

            return str.toString();
        }
    }


    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {


        Queue<UndirectedGraphNode> q1 = new LinkedList<>();

        // q1 is the queue which will hold all the nodes of the input graph
        addLevelOrderToQ(node, q1);
        visited.clear();



        UndirectedGraphNode startOrig = q1.peek();
        UndirectedGraphNode startClone = new UndirectedGraphNode(startOrig.label);
        // origCloneMap has original nodes and corresponding new clone nodes
        origCloneMap.put(startOrig, startClone);


        while (!q1.isEmpty()) {

            UndirectedGraphNode currOrig = q1.poll();

            // create a new clone of the node being considered (store in hashmap for easy access)
            if (!origCloneMap.containsKey(currOrig))
                origCloneMap.put(currOrig, new UndirectedGraphNode(currOrig.label));

            // go through neighbours of original node and stores them as neighbours of cloned node
            // take extra care about neighbours that already exist
            for (int i = 0; i < currOrig.neighbors.size(); i++) {

                UndirectedGraphNode kidOrig = currOrig.neighbors.get(i);

            UndirectedGraphNode kidClone;
                if (origCloneMap.containsKey(kidOrig))

                    kidClone = origCloneMap.get(kidOrig);

                else{
                    kidClone = new UndirectedGraphNode(kidOrig.label);
                    origCloneMap.put(kidOrig, kidClone);
                }

            origCloneMap.get(currOrig).neighbors.add(kidClone);


            }

        }
        return startClone;
    }

    // adds all nodes of given input graph to a queue
    private void addLevelOrderToQ(UndirectedGraphNode startOrig, Queue<UndirectedGraphNode> q) {



        Queue<UndirectedGraphNode> qBackUp = new LinkedList<>();
        qBackUp.add(startOrig);

        while (!qBackUp.isEmpty()) {

            // if node has already been visited, don't add it to queue
            if (visited.contains(qBackUp.peek())){
                qBackUp.poll();
                continue;
            }
            // if node has not yet been visited, add it to queue and mark as visited
            UndirectedGraphNode currNodeGet = qBackUp.poll();
            q.add(currNodeGet);

            visited.add(currNodeGet);

            // add unvisited neighbours of current node to queue
            for (int i = 0; i < currNodeGet.neighbors.size(); i++) {

                UndirectedGraphNode kid = currNodeGet.neighbors.get(i);
                if (!visited.contains(kid)) {
                    qBackUp.add(kid);


                }

            }
        }
    }

    public static void main(String[] args) {

        CloneGraph cloneGraph = new CloneGraph();

        UndirectedGraphNode orig = new UndirectedGraphNode(703);

        UndirectedGraphNode node1 = new UndirectedGraphNode(43);

        UndirectedGraphNode node2 = new UndirectedGraphNode(279);

        orig.neighbors.add(node1);
        orig.neighbors.add(node2);
        orig.neighbors.add(orig);


        node1.neighbors.add(node2);
        node1.neighbors.add(orig);

        node2.neighbors.add(node1);
        node2.neighbors.add(node2);
        node2.neighbors.add(orig);

        System.out.println(orig);
        System.out.println(node1);
        System.out.println(node2);


        UndirectedGraphNode clone = cloneGraph.cloneGraph(orig);

        System.out.println("cloned coming up");
        System.out.println(clone);
        System.out.println(clone.neighbors);

        }



    }





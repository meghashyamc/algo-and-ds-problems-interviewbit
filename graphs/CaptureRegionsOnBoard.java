package test.graphs;

/*

Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CaptureRegionsOnBoard {

private int[] connToBorder;
private int[] iPlus = {0, 0, -1, +1};
private int[] jPlus = {-1, +1, 0, 0};
public void solve(ArrayList<ArrayList<Character>> a) {

        if (a.isEmpty()) return;

        int m = a.size();
        int n = a.get(0).size();


        // cache that stores if a given position has 'O' and is connected to the border
    // if not visited yet, it stores -1
    connToBorder = new int[m* n];

    // initially, we haven't visited any character
        for(int i = 0; i < connToBorder.length; i++)
            connToBorder[i] = -1;


        int count = 0;
        // traverse top and bottom rows

    for(int i = 0;count <=1; i = m-1) {
        for (int j = 0; j < n; j++) {

            if ((a.get(i).get(j).equals('O'))
                    && (connToBorder[i * n + j] == -1))
                connectToBorder(a, i * n + j);
        }
count++;
    }

 count = 0;

    // traverse left and right cols

    for(int j = 0;count <=1; j = n-1) {
        for (int i = 0; i < m; i++) {

            if ((a.get(i).get(j).equals('O'))
                    && (connToBorder[i * n + j] == -1))
                connectToBorder(a, i * n + j);
        }
        count++;
    }

    // change all O's connected to the border to B's
    for(int i = 0; i < m; i++)
        for(int j = 0; j < n; j++){

            if (connToBorder[i*n+j] == 1)
                a.get(i).set(j, 'B');
        }

        // change all O's remaining (which are O's not connected to the border) to X's

    for(int i = 0; i < m; i++)
        for(int j = 0; j < n; j++){

            if (a.get(i).get(j).equals('O'))
                a.get(i).set(j, 'X');
        }

        // change B's back to O's

    for(int i = 0; i < m; i++)
        for(int j = 0; j < n; j++){

            if (a.get(i).get(j).equals('B'))
                a.get(i).set(j, 'O');
        }

}

    // connects all O's connected to the given position (which is a border position)
    private void connectToBorder(ArrayList<ArrayList<Character>> a, int pos){


        int m = a.size();
        int n = a.get(0).size();

        Queue<Integer> oQueue = new LinkedList<>();

        oQueue.add(pos);

        int i, j;

        // breadth first traversal of connected o's
        while(!oQueue.isEmpty()) {

            int curr = oQueue.poll();
            connToBorder[curr] = 1;
            i = curr / n; // row of curr
            j = curr % n; // col of curr

            for (int k = 0; k < 4; k++) {

                int adjI = i + iPlus[k];
                int adjJ = j + jPlus[k];
                if ((adjI < 0) || (adjI > m-1) || (adjJ < 0) || (adjJ > n-1))
                    continue;

                if ((connToBorder[adjI * n + adjJ] == -1) && a.get(adjI).get(adjJ).equals('O'))
                    oQueue.add(adjI * n + adjJ);

            }
        }



    }

    public static void main(String[] args) {



        ArrayList<ArrayList<Character>> a = new ArrayList<>();

        String[] arr = {"XOXXXXOOXX", "XOOOOXOOXX", "OXXOOXXXOO", "OXOXOOOXXO", "OXOOXXOOXX", "OXXXOXXOXO", "OOXXXXOXOO"};


        for(int i = 0; i < arr.length; i++){

            char[] arrChar = arr[i].toCharArray();

            ArrayList<Character> row = new ArrayList<>();

            for(int j = 0; j < arrChar.length; j++)
                row.add(arrChar[j]);

            a.add(row);
        }


        CaptureRegionsOnBoard captureRegionsOnBoard = new CaptureRegionsOnBoard();
        captureRegionsOnBoard.solve(a);

//        System.out.println(captureRegionsOnBoard.isConnectedToBorder(a, 13, new HashMap<>()));

        for(int i = 0; i < a.size(); i++)
        System.out.println(a.get(i));
        /*

XOXXXXOOXX
XOOOOXOOXX
OXXOOXXXOO
OXOXOOOXXO
OXOOXXOOXX
OXXXOXXOXO
OOXXXXOXOO ]


        */

    }
}

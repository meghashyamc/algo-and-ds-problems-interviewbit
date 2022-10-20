package backtracking;

import java.util.ArrayList;
import java.util.Collections;

/*

Write a program to solve a Sudoku puzzle by filling the empty cells.
Empty cells are indicated by the character '.'
You may assume that there will be only one unique solution.

http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png

A sudoku puzzle,

http://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png

and its solution numbers marked in red.

Example :

For the above given diagrams, the corresponding input to your program will be

[[53..7....], [6..195...], [.98....6.], [8...6...3], [4..8.3..1], [7...2...6], [.6....28.], [...419..5], [....8..79]]
and we would expect your program to modify the above array of array of characters to

[[534678912], [672195348], [198342567], [859761423], [426853791],  [713924856], [961537284], [287419635], [345286179]]
 */

public class Sudoku {

    public void solveSudoku(ArrayList<ArrayList<Character>> a) {

        // row is the next row with a dot
        int row = findNextDotRow(a);
        // index is the next index in the row which is a dot
        int index = findNextDotIndex(a);

        // generateBoards(...) is null if there is no result, it is a solved board otherwise
        ArrayList<ArrayList<Character>> result = generateBoards(a, row, index);

        if (result != null) {

            a.clear();


            // copy result to a
           for(int i = 0; i <= 8; i++){

               ArrayList<Character> arr = new ArrayList<>(result.get(i));
               a.add(arr);
           }
        }

    }

    // returns the solved board or null if the board can't be solved
    private ArrayList<ArrayList<Character>> generateBoards( ArrayList<ArrayList<Character>> a, int row, int index) {

        // the board is already solved, return it
        if (isSolved(a)) return a;

        ArrayList<ArrayList<ArrayList<Character>>> listOfBoards = new ArrayList<>();


        for(int i = 1; i <= 9; i++){

            // generate 9 possible boards to fill up the next dot
            ArrayList<ArrayList<Character>> board = new ArrayList<>();

            for(int j = 0; j <=8; j++){
                ArrayList<Character> arr = new ArrayList<>(a.get(j));
                board.add(arr);
            }

            // set the next dot in each board as 1, 2, 3....9
            board.get(row).set(index, Character.forDigit(i, 10));

            // if board after adding 1...9 in next dot is valid, add it to list
            if (isValid(board)) {


                listOfBoards.add(board);

            }

                  }


        // for each valid board, keep going, keep filling the next dot and the next dot...
        // if no solved board comes out of generating more boards from this one, return null
        // else return the solved board
        for(ArrayList<ArrayList<Character>> board: listOfBoards){


                ArrayList<ArrayList<Character>> result = generateBoards(board, findNextDotRow(board), findNextDotIndex(board));

                if (result != null) return result;


        }

        return null;
    }

    private boolean isValid( ArrayList<ArrayList<Character>> a){

        // check rows
        for(int row = 0; row <= 8; row++){

            if ( repeaters(a.get(row))) return false;
        }

        //check cols
        ArrayList<ArrayList<Character>> colsA = getCols(a);
        for(int col = 0; col <= 8; col++){

            if ( repeaters(colsA.get(col))) return false;
        }

        //check boxes
        ArrayList<ArrayList<Character>> boxesA = getBoxes(a);

        for(int box = 0; box <= 8; box++){

            if ( repeaters(boxesA.get(box))) return false;
        }

        return true;


    }

    // returns true if the given array has duplicates
    private boolean repeaters(ArrayList<Character> arr){

        if (arr.isEmpty()) return false;

        ArrayList<Character> copy = new ArrayList<>(arr);

        Collections.sort(copy);

        for(int i = 0; i < copy.size()-1; i++){

            if (!copy.get(i).equals('.') && (copy.get(i) == copy.get(i+1))) return true;
        }

        return false;
    }

    // returns all the columns of given board
    private  ArrayList<ArrayList<Character>> getCols( ArrayList<ArrayList<Character>> a){

        ArrayList<ArrayList<Character>> colsArray = new ArrayList<>();

        for(int i = 0; i <= 8; i++) {
            ArrayList<Character> col = new ArrayList<>();
            for (int j = 0; j <= 8; j++) {

                col.add(a.get(j).get(i));

            }

            colsArray.add(col);
        }

        return colsArray;
    }

    // returns an array that depicts the boxes of the given board
    private  ArrayList<ArrayList<Character>> getBoxes( ArrayList<ArrayList<Character>> a)

    {

        ArrayList<ArrayList<Character>> boxesArray = new ArrayList<>();

        int stI = 0, stJ = 0, endI = 2, endJ = 2;
        for(int box = 0; box <=8; box++){

            ArrayList<Character> boxA = new ArrayList<>();
            for(int i = stI; i <=endI; i++) {
                for (int j = stJ; j <= endJ; j++) {
                    boxA.add(a.get(i).get(j));

                }

            }
                boxesArray.add(boxA);

            if ((stJ+3) >= 9) {
                stI = stI + 3;
                endI = stI + 2;
                if (stI > 6) break;
            }

            stJ = (stJ+3)%9;
            endJ = stJ+2;

        }

        return boxesArray;

    }

    // returns true if the board is solved
    private boolean isSolved(ArrayList<ArrayList<Character>> a){
        // check rows
        for(int row = 0; row <= 8; row++){

            if ( !containsAllDig(a.get(row))) return false;
        }

        //check cols
        ArrayList<ArrayList<Character>> colsA = getCols(a);
        for(int col = 0; col <= 8; col++){

            if ( !containsAllDig(colsA.get(col))) return false;
        }

        //check boxes
        ArrayList<ArrayList<Character>> boxesA = getBoxes(a);

        for(int box = 0; box <= 8; box++){

            if ( !containsAllDig(boxesA.get(box))) return false;
        }

        return true;

    }

    // returns true only if the given array has all digits from 1 to 9
    private boolean containsAllDig(ArrayList<Character> arr){


        for(int i = 1; i <= 9; i++){

            if (!arr.contains(Character.forDigit(i, 10))) return false;
        }

        return true;
    }

    // returns the first row in the board that has a dot
    private int findNextDotRow( ArrayList<ArrayList<Character>> a){

        for(int row = 0; row <= 8; row++){

            if ((a.get(row)).contains('.')) return row;
        }

        return -1;
    }

    // returns the first index (row based) in the array that is a dot
    private int findNextDotIndex( ArrayList<ArrayList<Character>> a){

        int dotRow = findNextDotRow(a);

        if (dotRow == -1) return -1;

        for(int i = 0; i <= 8; i++){

            if (a.get(dotRow).get(i).equals('.')) return i;
        }

        return -1;
    }

    public static void main(String[] args) {

        String[] arr = {  "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"};


        ArrayList<ArrayList<Character>> a = new ArrayList<>();

        for(int i = 0; i <= 8; i++){

            ArrayList<Character> row = new ArrayList<>();

            for(int j = 0; j <=8; j++){

                row.add(arr[i].charAt(j));

            }

            a.add(row);
        }

        Sudoku sudoku = new Sudoku();
        sudoku.solveSudoku(a);

        System.out.println(a);




    }


}

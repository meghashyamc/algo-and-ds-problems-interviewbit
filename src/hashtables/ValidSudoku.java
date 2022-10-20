package hashtables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*

Determine if a Sudoku is valid, according to: http://sudoku.com.au/TheRules.aspx

The Sudoku board could be partially filled, where empty cells are filled with the character ‘.’.

http://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png

The input corresponding to the above configuration :


["53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"]

A partially filled sudoku which is valid.

 Note:
A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
Return 0 / 1 ( 0 for false, 1 for true ) for this problem

 */

public class ValidSudoku {


    // DO NOT MODIFY THE LIST. IT IS READ ONLY
    public int isValidSudoku(final List<String> A) {



        if (isValid(A)) return 1;
        return 0;
    }

    private boolean isValid(List<String> A){

        // check rows, they shouldn't have numbers repeating
        for(int row = 0; row <= 8; row++){

            if ( repeaters(A.get(row))) return false;
        }

        //check cols, they shouldn't have numbers repeating
        List<String> colsA = getCols(A);
        for(int col = 0; col <= 8; col++){

            if ( repeaters(colsA.get(col))) return false;
        }

        //check boxes, they shouldn't have numbers repeating
        List<String> boxesA = getBoxes(A);

        for(int box = 0; box <= 8; box++){

            if ( repeaters(boxesA.get(box))) return false;
        }

        return true;

    }

    // is a chracter getting repeated in the given input String?
    private boolean repeaters(String s){


        ArrayList<Integer> intArray = new ArrayList<>();

        for(int i = 0; i < s.length(); i++){

            if (s.charAt(i) != '.') intArray.add(Character.getNumericValue(s.charAt(i)));

        }

        Collections.sort(intArray);


        for(int i = 0; i < intArray.size()-1; i++){

            if (intArray.get(i) == intArray.get(i+1)) return true;
        }

        return false;
    }

    // obtain columns array from given sudoku
    private  ArrayList<String> getCols( List<String> a){

        ArrayList<String> colsArray = new ArrayList<>();

        for(int i = 0; i <= 8; i++) {
            StringBuilder col = new StringBuilder();
            for (int j = 0; j <= 8; j++) {

                col.append(a.get(j).charAt(i));

            }

            colsArray.add(col.toString());
        }

        return colsArray;
    }

    // obtain 9-cell square boxes from given sudoku in the form of an array
    private  ArrayList<String> getBoxes( List<String> a)

    {

        ArrayList<String> boxesArray = new ArrayList<>();

        int stI = 0, stJ = 0, endI = 2, endJ = 2;
        for(int box = 0; box <=8; box++){

            StringBuilder boxA = new StringBuilder();
            for(int i = stI; i <=endI; i++) {
                for (int j = stJ; j <= endJ; j++) {
                    boxA.append(a.get(i).charAt(j));

                }

            }
            boxesArray.add(boxA.toString());

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

    public static void main(String[] args) {

        ValidSudoku validSudoku = new ValidSudoku();

        String[] arr = {  "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.", "...419..5", "....8..79"};


        ArrayList<String> a = new ArrayList<>();

        for(int i = 0; i < arr.length; i++){

            a.add(arr[i]);

        }

        System.out.println(validSudoku.getBoxes(a));
    }




}

package arrays;

import java.util.ArrayList;

public class SetMatrixZeroes {

    public void setZeroes(ArrayList<ArrayList<Integer>> a) {

        // the first row and column denote if there is a zero
        // in that particular row or column


        // is there a zero in the first row or column?
        // this is for later use, after processing the rest of the matrix
        boolean zeroInFirstRow = zeroInFirstRow(a);
        boolean zeroInFirstCol = zeroInFirstCol(a);



        for (int row = 1; row < a.size(); row++) {

            for (int col = 0; col < a.get(0).size(); col++) {
            // set 0th digit of that 0's row as zero when you find a zero
                if (a.get(row).get(col) == 0) {
                    a.get(row).set(0, 0);
                    break;
                }

            }
        }

        for (int col = 1; col < a.get(0).size(); col++) {


            for (int row = 0; row < a.size(); row++) {

                // set 0th digit of that 0's col as zero when you find a zero

                if (a.get(row).get(col) == 0) {
                    a.get(0).set(col, 0);
                    break;
                }

            }
        }



        for(int row = 1; row < a.size(); row++){

            if (a.get(row).get(0).equals(0)){
            // set entire row as 0 if 0th index of that row is 0
                for(int i = 1; i < a.get(0).size(); i++){
                    a.get(row).set(i, 0);
                }
            }
        }

        for(int col = 1; col < a.get(0).size(); col++){

            if (a.get(0).get(col).equals(0)){
                // set entire col as 0 if 0th index of that col is 0

                for(int i = 1; i < a.size(); i++){
                    a.get(i).set(col, 0);
                }
            }
        }


        // is there a zero in the zeroeth row?
        // if so, set zeroeth rows elements as 0

        if (zeroInFirstRow) {

            for (int i = 0; i < a.get(0).size(); i++)
                a.get(0).set(i, 0);

        }

        // is there a zero in the zeroeth col?
        // if so, set zeroeth col's elements as 0

        if (zeroInFirstCol) {

            for (int i = 0; i < a.size(); i++)
                a.get(i).set(0, 0);

        }


    }

    private boolean zeroInFirstRow(ArrayList<ArrayList<Integer>> a) {

        for (Integer i : a.get(0)) {

            if (i.equals(0)) return true;
        }

        return false;

    }

    private boolean zeroInFirstCol(ArrayList<ArrayList<Integer>> a) {

        for (ArrayList<Integer> currRow : a) {

            if (currRow.get(0).equals(0)) return true;
        }

        return false;

    }

    public static void main(String[] args) {

        SetMatrixZeroes sol = new SetMatrixZeroes();


        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        ArrayList<Integer> row1 = new ArrayList<>();
        ArrayList<Integer> row2 = new ArrayList<>();
        ArrayList<Integer> row3 = new ArrayList<>();


        row1.add(1);
        row1.add(0);
        row1.add(1);

        row2.add(1);
        row2.add(0);
        row2.add(1);

        row3.add(1);
        row3.add(1);
        row3.add(1);

        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);

        System.out.println(matrix);

        sol.setZeroes(matrix);

        System.out.println(matrix);

    }
}

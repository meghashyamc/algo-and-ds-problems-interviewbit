package hashTables;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdOut;

import java.util.HashMap;

public class FourSum {

   private int[] numbers;
   private HashMap<Integer, Point2D> hashMap;

    public FourSum(int[] numbers){

       this.numbers = numbers.clone();
       hashMap = new HashMap<>();

    }


    private boolean checkSumThere(int sum){

        return hashMap.containsKey(sum);
    }
    private void insertSum(int sum, int i, int j){
        hashMap.put(sum, new Point2D(i, j));

    }

    private void findFourSums(){

        int i, j, sum;

        for(i = 0; i < numbers.length; i++)
            for(j = i+1; j < numbers.length; j++){

            int firstNum = numbers[i];
            int secondNum = numbers[j];
            sum = firstNum + secondNum;

            if (!checkSumThere(sum)) insertSum(sum, firstNum, secondNum);
            else StdOut.println("Found! " + sum + " = " + firstNum + " + " + secondNum + " = " + hashMap.get(sum).x() + " + " + hashMap.get(sum).y());
            }
    }

    public static void main(String[] args) {

        int[] a = {0, 1, -1, 100, -100, 154, -154, 25000, 12, -25000, 9};
        FourSum fourSum = new FourSum(a);

        fourSum.findFourSums();
    }
}

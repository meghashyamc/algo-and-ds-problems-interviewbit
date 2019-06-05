package test.binarySearch;

import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoArrays {

    // DO NOT MODIFY BOTH THE LISTS
    public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {
       int m = a.size();
       int n = b.size();
        int totalSize = m+n;

        int medianIndex;
        boolean even;
        double median;

        if (totalSize%2 == 0) even = true;
        else even = false;

        if (even) medianIndex = (totalSize/2) - 1;
        else medianIndex = totalSize/2;

        int i, j;

      if (a.isEmpty()) {

            if (even) return average(b.get(medianIndex), b.get(medianIndex+1));
            else return b.get(medianIndex);
        }

        else if (b.isEmpty()) {

            if (even) return average(a.get(medianIndex), a.get(medianIndex+1));
            else return a.get(medianIndex);
        }

        else if ((m == 1) && (n==1)){

          return average(a.get(0), b.get(0));
      }

        if (a.get(m-1)<= b.get(0)) {

            if (m == n) return average((double) a.get(m - 1), (double) b.get(0));
            else if (m < n){

                if (even) return average((double) b.get(medianIndex - m), (double) b.get(medianIndex-m+1));
            else return (double) b.get(medianIndex - m);
            }
            else {
                if (even) return average(a.get(medianIndex), b.get(0));
                else return (double) a.get(medianIndex);

        }

        }

        else if (b.get(n-1) <= a.get(0)){

            if (m == n) return average((double) b.get(n - 1), (double) a.get(0));
            else if (n < m){


                if (even) return (average((double) a.get(medianIndex - n), (double) a.get(medianIndex - n+1)));
                else return (double) a.get(medianIndex - n);

            }
            else {
                if (even) return average(b.get(medianIndex), a.get(0));
                else return (double) b.get(medianIndex);

            }
        }




        else{
            i = searchForI(a, b, medianIndex);

            System.out.println("final i is " + i);

            if (i == -1) {

                j = medianIndex;

                if (even) return average((double) b.get(j), (double) b.get(j+1));
                else return (double) b.get(j);
            }

            else j = medianIndex - i-1;

            if (j == -1){

                if (even) return average((double) a.get(i), (double) a.get(i+1));
                else return (double) a.get(i);
            }

            else {
                if (even) return average(greater(a.get(i), b.get(j)), smaller(a.get(i+1), b.get(j+1)));
                else return greater(a.get(i), b.get(j));
            }

        }




    }



    private double average(double a, double b) {

        return (a + b) / 2;
    }

    private double greater(int a, int b){

        if (a > b) return (double) a;
        else return (double) b;
    }

    private double smaller(int a, int b){

        if (a < b) return (double) a;
        else return (double) b;
    }

    private int searchForI(List<Integer> a, List<Integer> b, int medianIndex){


        int lo = 0;
        int hi = a.size()-1;

        int i = -1;

        while(lo <= hi){

            int mid = lo + ((hi-lo)/2);

            System.out.println("median index is "  + medianIndex);

            i = mid;
            int j = medianIndex-mid-1;
            System.out.println("lo to hi is " + lo + " to " + hi);
            System.out.println("i is " + mid);
            System.out.println("j is " + j);

            if (j == b.size()-1){

                if (i == a.size()-1) hi = mid-1;
              else{
                  if (a.get(i+1) >= b.get(j)) return mid;
                    else lo = mid+1;
                }

            }

            else if (j > b.size()-1){

            lo = mid+1;
            }

            else if (j < 0){

            hi = mid-1;
            }

            else if (i == a.size()-1){


                if (a.get(i) <= b.get(j+1)) return mid;
                else hi = mid-1;


            }

            else {

                if ((a.get(mid)<= b.get(medianIndex-mid)) && (a.get(mid+1) >= b.get(medianIndex-mid-1)))

                    return mid;

                else if ((a.get(mid)< b.get(medianIndex-mid)) && (a.get(mid+1) < b.get(medianIndex-mid-1)))
                    lo = mid+1;

                else if ((a.get(mid)> b.get(medianIndex-mid)))
                    hi = mid-1;

            }


        }
        if ( i < 0) return -1;
        else return i;
    }


    public static void main(String[] args) {


        int[] array1 = {-41, -33, -24, -21, -20, 27, 48};
        int[] array2 = {-9  };

        MedianOfTwoArrays medianOfTwoArrays = new MedianOfTwoArrays();

       ArrayList a = new ArrayList();
        ArrayList b = new ArrayList();

        for(int i = 0; i < array1.length; i++){

            a.add(array1[i]);
        }


        for(int i = 0; i < array2.length; i++){

            b.add(array2[i]);
        }



        System.out.println(medianOfTwoArrays.findMedianSortedArrays(a, b));


    }


}

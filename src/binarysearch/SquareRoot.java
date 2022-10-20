package binarysearch;

/*

Implement int sqrt(int x).

Compute and return the square root of x.

If x is not a perfect square, return floor(sqrt(x))

Example :

Input : 11
Output : 3
DO NOT USE SQRT FUNCTION FROM STANDARD LIBRARY
 */

public class SquareRoot {

    public int sqrt(int a) {

        if (a == 0) return 0;
        if (a == 1) return 1;
        if (a < 0) return -1;

long lo = 1;
long hi = a-1;

while(lo <= hi){

   long mid = lo + ((hi - lo)/2);

//    System.out.println("mid is " + mid);

   long product = mid*mid;
   long nextProduct = (mid+1)*(mid+1);

   if ((product <= a) && (nextProduct > a)){

//       System.out.println("found! returned " + mid);

       return (int) mid;
   }
   else if (product < a){

//       System.out.println("product is " + product);

       lo = mid + 1;

//       System.out.println("lo - hi:" + (mid + 1) + " to " + hi);
   }
   else{

       hi = mid - 1;
//        System.out.println("product is " + product);
//
//       System.out.println("lo - hi:" + lo + " to " + (mid-1));
   }

}

return -1;
    }


    public static void main(String[] args) {
        SquareRoot squareRoot = new SquareRoot();

        System.out.println(squareRoot.sqrt(930675566));

    }



}

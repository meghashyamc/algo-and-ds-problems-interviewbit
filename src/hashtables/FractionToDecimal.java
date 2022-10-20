package hashtables;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FractionToDecimal {

    public String fractionToDecimal(int A, int B) {

        long num = A;
        long den = B;

        // is the fraction negative?
        boolean neg = false;
        if (( num < 0) && (den > 0)) {
            neg = true;
            num = -num;
        }
        else if (( num > 0) && (den < 0)) {
            neg = true;
            den = -den;
        }
        else if (( num < 0) && (den < 0)) {
            num = -num;
            den = -den;
        }


StringBuilder str = new StringBuilder();

long floor = num/den;

if (neg) str.append('-');
str.append(floor);

// the numerator divides the denominator
if (floor*den == num) return  str.toString();

str.append(".");

// newNum means new numerator
long newNum;

if (num < den) newNum = num;

else newNum = (num%den);

long[] fracArr = new long[2];

// simplify fraction, for eg. convert 5/25 to 1/5
simplifyFrac(fracArr, newNum, den);

newNum = fracArr[0];
long newDen = fracArr[1];

// the decimal will be repeating if the fraction's denominator has prime factors
        // other than 2 and 5
if (!isRepeating(newDen)){

    // convert fraction to decimal assuming non-repeating decimal
    str.append(getDecimalNoRep(newNum, newDen));


return str.toString();

}

else{


    // convert fraction to decimal given that the decimal is repeating
    str.append(getRepeater(newNum, newDen));


    return str.toString();


}




    }

// converts given fraction to decimal, assuming it is non-repeating
    private String getDecimalNoRep(long newNum, long newDen) {

        StringBuilder str = new StringBuilder();

        while (true) {

            // keep adding 0's when the numerator is < denominator
            while (newNum < newDen) {

                newNum *= 10;
                if (newNum >= newDen) break;
                else str.append(0);
            }

            // keep adding numerator/denominator to decimal when the numerator is greater
            // set the remainder as the next numerator
            str.append(newNum / newDen);
            long rem = newNum%newDen;
            if (rem == 0) break;
            else newNum = rem;

        }

        return str.toString();

    }

    // converts fraction to decimal assuming repeating decimal
    private String getRepeater(long newNum, long newDen){


        // stores remainder and serial number (order) for that remainder
        HashMap<Long, Integer> remSerMap = new HashMap<>();
        // stores remainder and the divisor which led to that remainder
        HashMap<Long, Long> remDivMap = new HashMap<>();


        StringBuilder str = new StringBuilder();
        int count = 0;
        long rem;
        long quo;

        outerloop: while(true) {

            // this loop is executed to add 0's when numerator is < denominator and
            // to keep track of remainder and divisor in such situations
           while (newNum < newDen) {

               newNum *= 10;
               if (newNum >= newDen) break;

               else {
                   rem = newNum%newDen;

                   // if this remainder with the same divisor has not occurred earlier
                   if ((remSerMap.get(rem) == null) || (remDivMap.get(rem) != newNum)) {

                       str.append(0);

                       remSerMap.put(rem, count++);
                       remDivMap.put(rem, newNum);
                   }

                   // if this remainder with the same divisor HAS occurred earlier, we've found which part of the decimal is repeating
                   else{

                       int pos = remSerMap.get(rem);


                       str.insert(pos,'(');
                       str.append(')');

                       break outerloop;
                   }


               }


           }

            // this part is executed when we have reached a point where numerator is >= denominator and we
            // have still not found which digit the repetition starts from

           rem = newNum%newDen;
           quo = newNum/newDen;
            // if this remainder with the same divisor has not occurred earlier

            if ((remSerMap.get(rem) == null) || (remDivMap.get(rem) != newNum)){

                remSerMap.put(rem, count++);
                remDivMap.put(rem, newNum);

                newNum = rem;
                str.append(quo);
            }
            // if this remainder with the same divisor HAS occurred earlier, we've found which part of the decimal is repeating

            else {
               int pos = remSerMap.get(rem);


                str.insert(pos,'(');
                str.append(')');

                break;
            }

       }



       return str.toString();

    }

    // adds simplified version of a fraction to the given array, eg. 4/8 will lead to 1 and 2 getting added to array (as 1/2 is the simplified version)
    private void simplifyFrac(long[] fracArr, long newNum, long B){

        long gcd = gcd(newNum, B);

        newNum = newNum/gcd;
        B = B/gcd;

        fracArr[0] = newNum;
        fracArr[1] = B;


    }

    // a fraction can be represented as a non-repeating decimal only if
    // its denominator has 2 and 5 as the only prime factors
    private boolean isRepeating(long newDen){


        ArrayList<Long> factArray = new ArrayList<>();

        allFactors(factArray, newDen);

        HashSet<Long> primeSet = new HashSet<>();

        for(int i = 0; i < factArray.size(); i++){

            if (isPrime(factArray.get(i)))
                primeSet.add(factArray.get(i));

        }


        if (primeSet.contains((long)2)) primeSet.remove((long)2);
        if (primeSet.contains((long)5)) primeSet.remove((long)5);

        // did primeSet have only 2 and 5? Then return false, else return true
        return !primeSet.isEmpty();



    }

    // adds all factors of a number to the given array
    private void allFactors( ArrayList<Long> factArray, long A) {


        double rootA = Math.sqrt(A);
        for(long i = 1; i <= rootA ; i++){

            if ((A%i) == 0) {

                factArray.add(i);

            }
        }

        int noOfSmallFactors = factArray.size();
        for(int i = noOfSmallFactors-1; i >=0; i--){

            if (factArray.get(i) != rootA){

                factArray.add(A/factArray.get(i));
            }

        }


    }
// returns true if the input is a prime number
    public boolean isPrime(long A) {

        if (A <=1) return false;

        for(long i = 2; i <= Math.sqrt(A); i++){

            if(A%i == 0) return false;
        }

        return true;
    }

    // returns the gcd of the given numbers
    private long gcd(long A, long B) {


        if ((A == 0) && (B == 0)) return 0;

if (A < 0) A = -A;
if (B < 0) B = -B;

        if(B >A) {

            if (A == 0) return B;

            if (B % A == 0) return A;

            else return gcd(B % A, A);

        }

        else {

            if (A == 0) return B;
            if (B == 0) return A;
            if (A % B == 0) return B;

            else return gcd(A % B, B);

        }

    }

    public static void main(String[] args) {
    FractionToDecimal fractionToDecimal = new FractionToDecimal();

        System.out.println(fractionToDecimal.fractionToDecimal(945,103));



    }
}

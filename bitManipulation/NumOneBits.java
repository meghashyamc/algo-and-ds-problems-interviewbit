package test.bitManipulation;

/*

Write a function that takes an unsigned integer and returns the number of 1 bits it has.

Example:

The 32-bit integer 11 has binary representation

00000000000000000000000000001011
so the function should return 3.

Note that since Java does not have unsigned int, use long for Java
 */

public class NumOneBits {

    public int numSetBits(long a) {

        if (a == 0) return 0;

        int count = 0;

        for(int i = 32; i > 0; i--){

            // gets last bit
            if ((a&0b1) == 1) count++;

            // moves a to right to get new last bit
            a >>= 1;
        }

        return count;


    }

    public static void main(String[] args) {

        NumOneBits numOneBits = new NumOneBits();

        System.out.println(numOneBits.numSetBits(3));
    }
}

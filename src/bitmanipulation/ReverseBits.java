package bitmanipulation;

/*

Reverse bits of an 32 bit unsigned integer

Example 1:

x = 0,

          00000000000000000000000000000000
=>        00000000000000000000000000000000
return 0

Example 2:

x = 3,

          00000000000000000000000000000011
=>        11000000000000000000000000000000
return 3221225472

Since java does not have unsigned int, use long
 */

public class ReverseBits {
    public long reverse(long a) {

        long reversed = 0;

        for(int i = 31; i >= 0; i--){

            // gets last bit
            long currBit = (a&0b1);

            reversed += currBit*Math.pow(2, i);

            // a moved to right to get new last bit
            a >>= 1;

        }

        return reversed;

    }

    public static void main(String[] args) {

        ReverseBits reverseBits = new ReverseBits();


        long ans = reverseBits.reverse(4294967295L);
        System.out.println(Long.toBinaryString(4294967295L));

        System.out.println(ans);
        System.out.println(Long.toBinaryString(ans));

    }
}

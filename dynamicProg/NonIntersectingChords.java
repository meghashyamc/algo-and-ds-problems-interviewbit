package test.dynamicProg;

/*

Given a number N, return number of ways you can draw N chords in a circle with 2*N points such that no 2 chords intersect.
Two ways are different if there exists a chord which is present in one way and not in other.

For example,

N=2
If points are numbered 1 to 4 in clockwise direction, then different ways to draw chords are:
{(1-2), (3-4)} and {(1-4), (2-3)}

So, we return 2.
Notes:

1 ≤ N ≤ 1000
Return answer modulo 109+7.
 */

public class NonIntersectingChords {

    private long[] cache;
    private static long MOD = 1000000000+7;
    public int chordCnt(int A) {

        if (A <= 1) return 1;

        if (A == 2) return 2;

        cache = new long[A+1];

        cache[0] = 1;
        cache[1] = 1;
        cache[2] = 2;

        for(int i = 3; i <= A; i++)
            cache[i] = 0;

        // if any one chord is treated as a divider, non intersecting chords on both sides of this will be non-intersecting
        // so, if we want f(i), f(i) = f(0) x f(i-0) + f(1) x f(i-1) + f(2) x f(i-2) + f(3) x f(i-3) and so on and so forth and what have you
        for(int i = 3; i <= A; i++)
            for(int j = 0; j < i; j++)
                cache[i] += ((cache[j]%MOD)*(cache[i-j-1]%MOD)) %MOD;


        return (int) (cache[A]%MOD);

    }

    public static void main(String[] args) {

        NonIntersectingChords nonIntersectingChords = new NonIntersectingChords();

        System.out.println(nonIntersectingChords.chordCnt(36));
    }
}

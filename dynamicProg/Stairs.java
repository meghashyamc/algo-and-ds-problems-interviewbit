package test.dynamicProg;

/*

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example :

Input : 3
Return : 3

Steps : [1 1 1], [1 2], [2 1]

 */

public class Stairs {

    private int[] mem;

    public int climbStairs(int A) {

        return climbStairs(A, true);


    }

    private int climbStairs(int A, boolean firstTime){

        if (firstTime){

            mem = new int[A+1];
           for(int i = 0; i < mem.length; i++)
               mem[i] = 0;
           return climbStairs(A, false);
        }

        if (A == 1) return 1;

        if (A == 2) return 2;

        // we already know the number of steps required for this A
        if (mem[A] != 0) return mem[A];

        // this is the core algorithm: ways to climb till n = ways to climb n-2 (and then a 2-step) or ways to climb n-1 stairs (and then a 1-step)
        mem[A] = climbStairs(A-1, false) + climbStairs(A-2, false);

        return mem[A];
    }


    public static void main(String[] args) {

        Stairs stairs = new Stairs();

        System.out.println(stairs.climbStairs(10));
    }


}

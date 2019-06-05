
    package test.binarySearch;


    import java.util.ArrayList;
    import java.util.List;

    /*

    There are two sorted arrays A and B of size m and n respectively.

Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).

The overall run time complexity should be O(log (m+n)).

Sample Input

A : [1 4 5]
B : [2 3]

Sample Output

3
 NOTE: IF the number of elements in the merged array is even, then the median is the average of n / 2 th and n/2 + 1th element.
For example, if the array is [1 2 3 4], the median is (2 + 3) / 2.0 = 2.5
     */

    public class MedianOfTwoArrays1 {


        public double findMedianSortedArrays(final List<Integer> a, final List<Integer> b) {

           int m = a.size();
           int n = b.size();
            int total = m+n;

            boolean even = (total%2) == 0;
            int k;

            if (even) {
                k = total/2;
                return average(kth(a,b,m,n,k,0,0),kth(a,b,m,n,k+1,0,0));
            }

            else {

                k = total/2 + 1;
                return kth(a,b,m,n,k,0,0);
            }


        }

        private double average(double a, double b) {

            return (a + b) / 2;
        }


            //find kth element from two sorted arrays

            static int kth(List<Integer> a, List<Integer> b, int m,
                           int n, int k, int st1, int st2)
            {


                // reached end of array 1?
                if ((st1 == m) || a.isEmpty())
                {
                    return b.get(st2 + k - 1);
                }

                // reached end of array 2?
                if ((st2 == n) || b.isEmpty())
                {
                    return a.get(st1 + k - 1);
                }

                // k should not reach 0 or exceed sizes
                // of arrays
                if (k == 0 || k > (m - st1) + (n - st2))
                {
                    return -1;
                }

                // k is 1, so compare first elements of arrays and return
                if (k == 1)
                {
                    return (a.get(st1) < b.get(st2)
                            ? a.get(st1) : b.get(st2));
                }

                // we will compare k/2th elements of both arrays (if possible)
                int curr = k / 2;

                // size of array 1 is less than k / 2
                if (curr - 1 >= m - st1)
                {

                    // last element of array 1 is < k/2th element of array 2
                    if (a.get(m - 1) < b.get(st2 + curr - 1))
                    {
                        // so (k - m)th element in array 2 is the ans
                        return b.get(st2 + (k - (m - st1) - 1));
                    }
                    else
                    {
                        // remove the smallest curr elements of array 2 (starting from st2)
                        // from consideration and search for k - currth element
                        return kth(a, b, m, n, k - curr,
                                st1, st2 + curr);
                    }
                }

                // size of array 2 is less than k / 2
                if (curr - 1 >= n - st2)
                {
                    // last element of array 2 < array 1's k/2th element
                    if (b.get(n - 1) < a.get(st1 + curr - 1))
                    {
                        // so (k - n)th element in array 1 is the ans
                        return a.get(st1 + (k - (n - st2) - 1));
                    }
                    else
                    {
                        // remove the smallest curr elements of array 2 (starting from st2)
                        // from consideration and search for k - currth element
                        return kth(a, b, m, n, k - curr,
                                st1 + curr, st2);
                    }
                }
                else

                    // compare k/2th elements
                    if (a.get(curr + st1 - 1) < b.get(curr + st2 - 1))
                    {
                        // array 1's k/2th element is lesser, so remove k/2 elements starting from st1
                        // from consideration and search for k-currth element
                        return kth(a, b, m, n, k - curr,
                                st1 + curr, st2);
                    }
                    else
                    {
                        // array 2's k/2th element is lesser, so remove k/2 elements starting from st2
                        // from consideration and search for k-currth element
                        return kth(a, b, m, n, k - curr,
                                st1, st2 + curr);
                    }
            }


            public static void main(String[] args)
            {

                MedianOfTwoArrays1 medianOfTwoArrays1 = new MedianOfTwoArrays1();

                int arr1[] = {-41, -33, -24, -21, -20, 27, 48};
                int arr2[] = {-9};

                ArrayList a = new ArrayList();
                ArrayList b = new ArrayList();

                for(int i = 0; i < arr1.length; i++){

                    a.add(arr1[i]);
                }


                for(int i = 0; i < arr2.length; i++){

                    b.add(arr2[i]);
                }

                System.out.println(medianOfTwoArrays1.findMedianSortedArrays(a,b));
            }
        }

// Help has been taken from 29AjayKumar's code in writing this code.
// Thank you 29AjayKumar





import java.util.*;
public class ArrayReduction {
    public static void main(String[] args) {
        int[][] tests = {
            {1,1,3},
            {2,2,3,4,0,1,2,0}
        };

        for (int[] test : tests) {
            List<Integer> ans = reduceArray(test);
            for (int a : ans) {
                System.out.print(a+"\t");
            }
            System.out.println();
        }

    }
    /*
     * 1. Initialize Integer array to store final result
     * 2. Initialize a list of queue
     * 3. For each int in the input array
     *  3.1 if the value is smaller than the array length
     *   3.1.1 add idx of the value into its queue
     *  3.2 else
     *   3.2.1 if the int value is larger than the array length, we cannot use it to reduce with MEX
     * 4. For each int in the input array; i=0, j=-1; while i is not the end
     *  4.1 for mex=0; mex is the smallest missing element
     *   4.1.1 while the queue of mex is not empty and the front of the queue is smaller than j
     *   4.1.2 poll the front of the queue, remove the index that was already removed by previous operation
     *  4.2 if the queue of mex is empty
     *   4.2.1 add mex to the result array, this mex is the first value missing for the whole set
     *   4.2.2 set j to be i, indicate all elements before and equal to i was reduced
     *  4.3 else
     *   4.3.1 if the mex is not empty, assign i to max(i, front), next index to start iteration
     * 
     * 
     */
    public static List<Integer> reduceArray(int[] array) {
        List<Integer> ans = new ArrayList<>();

        List<Queue<Integer>> all = new ArrayList<>();
        for (int i=0;i<=array.length;i++) {
            all.add(new LinkedList<Integer>());
        }
        for (int i=0;i<array.length;i++) {
            if (array[i]<array.length) {
                all.get(array[i]).offer(i);
            }
        }

        for (int i=0, j=-1;i<array.length;i++) {
            for (int mex=0;;++mex) {
                while (!all.get(mex).isEmpty() && all.get(mex).peek() <= j) {
                    // firstly, we remove the indexes that were already removed by previous operation
                    all.get(mex).poll();
                }
                // when we get a mex that is empty, it is the answer for current iteration
                // because that is the first value missing in for the whole set
                if (all.get(mex).isEmpty()) {
                    // add the mex to answer
                    ans.add(mex);
                    // set j to be i to indicate all elements before and equal to i was reduced
                    // j here is the value of k
                    // the number of element reduced
                    j = i;
                    break;
                } else {
                    // if the mex is not empty, assign i to max(i, front)
                    i = Math.max(i, all.get(mex).peek());
                }
            }
        }
        return ans;
    }
}
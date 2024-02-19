 

public class MaxmizeArrayValue {
    public static void main(String[] args) {
        int[] test1 = {1,5,7,6};
        int[] test2 = {5,15,19};
        int[] test3 = {10,3,5,7};
        int[][] tests = {test1, test2, test3};
        for (int[] test : tests) {
            System.out.println(minimizeMaxByBS(test));
        }
    }
    public static int minimizeMaxByBS(int[] array) {
        int a = 0;
        int b = 0;
        for (int i=0;i<array.length;i++) {
            b = Math.max(b, array[i]);
        }
        while (a<b-1) {
            int m = (a+b)/2;
            if (canAchieveMax(array, m)) {
                b = m;
            } else {
                a = m+1;
            }
        }
        return canAchieveMax(array, a) ? a : b;
    }
    // determine whether this max value is achievable 
    static boolean canAchieveMax(int[] array, int m){
        // one very important assumption for this to work is that 
        // we can only perform operation that take the large part from right to compensate left part
        // thus we start from left and move to right to count the available
        // Since the part more than m can only be moved to the left, if there is no available on the left remain,
        // we can return false directly

        // assume m is the max in the whole array
        // the maximum value to be hold for this array would be array.length*m
        // at start let the available value be 0
        int avail = 0;
        for (int element : array) {
            // for each element
            if (element <= m) {
                // if the element is smaller than m
                // there are (m-element) count can be used to hold other number
                avail += m-element;
            } else if ((element-avail)<=m) {
                // if the element is larger than m
                // check whether we have any available count to cover the part large than m
                // if there this part can be covered, we can keep going
                avail -= element-m;
            } else {
                // if there is not enough available count to cover this part return false
                return false;
            }
        }
        return true;
    }
}

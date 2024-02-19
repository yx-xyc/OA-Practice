public class MinMove {
    public static void main(String[] args) {
        System.out.println(getMinimumMove(new int[] { 1, 4, 4 }));
        System.out.println(getMinimumMove(new int[] { 3, 3, 6, 3, 9 }));
        System.out.println(getMinimumMove(new int[] { 1, 4, 4 }));

    }

    public static int getMinimumMove(int[] array) {
        int l = 0, r = array.length - 1;
        int l_sum = 0, r_sum = 0;
        while (l <= r) {
            if (l_sum < r_sum) {
                l_sum += array[l];
                l++;
            } else {
                r_sum += array[r];
                r--;
            }
        }
        return Math.abs(r_sum - l_sum);
    }
}

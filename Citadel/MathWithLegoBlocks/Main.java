public class Main {
    public static void main(String[] args) {
        System.out.println(findMinimumEqualSum(new int[]{1, 0, 2}, new int[]{1, 3, 0, 0}));  // Expected output: 6
        System.out.println(findMinimumEqualSum(new int[]{0, 0, 0}, new int[]{1, 1}));  // Expected output: -1
        System.out.println(findMinimumEqualSum(new int[]{2, 5, 0, 1, 1}, new int[]{2, 1, 0, 0}));  // Expected output: 10

    }
    public static int findMinimumEqualSum(int[] a, int[] b) {
        int sum_a=0, sum_b=0, count_a0=0, count_b0=0;
        for (int i=0;i<a.length;i++) {
            sum_a += a[i];
            if (a[i]==0) {
                sum_a += 1;
                count_a0++;
            }
        }
        for (int i=0;i<b.length;i++) {
            sum_b += b[i];
            if (b[i]==0) {
                sum_b += 1;
                count_b0++;
            }
        }
        if (count_a0==0 && count_b0==0  && sum_a!=sum_b) {
            return -1;
        }
        if (count_a0==0 && sum_a<sum_b) {
            return -1;
        }
        if (count_b0==0 && sum_b<sum_a) {
            return -1;
        }
        return Math.max(sum_a, sum_b);
    }
}

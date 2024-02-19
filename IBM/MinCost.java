import java.util.*;

public class MinCost {
    public static void main(String[] args) {
        System.out.println(getMinimumCost(new int[] { 1, 3, 5, 2, 10 }) == 49);
        System.out.println(getMinimumCost(new int[] { 4, 7, 1, 4 }) == 36);
        System.out.println(getMinimumCost(new int[] { 4, 7, 7 }) == 5);
    }

    public static int getMinimumCost(int[] array) {
        int[] diff = new int[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            diff[i - 1] = Math.abs(array[i] - array[i - 1]);
        }
        Arrays.sort(diff);
        int sum = 0;
        for (int i = 0; i < diff.length; i++) {
            if (i == diff.length - 1) {
                if (diff[i] % 2 == 0) {
                    sum += 2 * (diff[i] / 2) * (diff[i] / 2);
                } else {
                    sum += (diff[i] / 2) * (diff[i] / 2);
                    sum += (diff[i] - diff[i] / 2) * (diff[i] - diff[i] / 2);
                }
            } else {
                sum += diff[i] * diff[i];
            }
        }
        return sum;
    }
}

import java.util.ArrayList;

import java.util.*;
public class LargestSubGrid {
    
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        for (int i=0;i<3;i++) {
            matrix.add(Arrays.asList(i+2,i+2,i+2));
        }
        System.out.println(largestSubgrid(matrix, 27));
    }

    private static List<List<Integer>> preSum = new ArrayList<>();
    
    private static int getSum(int row, int col) {
        if (row<0 || col<0) {
            return 0;
        }
        return preSum.get(row).get(col);
    }
    
    private static int sumRegion(int row1, int col1, int row2, int col2) {
        return getSum(row2, col2)-getSum(row1-1, col2)-getSum(row2, col1-1)+getSum(row1-1, col1-1);
    }

    private static int largestSubgrid(List<List<Integer>> grid, int k) {
        int n = grid.size();
        for (int i=0;i<n;i++) {
            preSum.add(new ArrayList<>());
            for (int j=0;j<n;j++) {
                preSum.get(i).add(getSum(i-1, j)+getSum(i, j-1)-getSum(i-1, j-1)+grid.get(i).get(j));
            }
        }
        int low = 0, high = n, ans = 0;
        while (low <= high) {
            int mid = low + (high-low)/2;
            if (mid==0) return 0;
            boolean stop = false;
            for (int i=mid-1;i<n && !stop;i++) {
                for (int j=mid-1;j<n && !stop;j++) {
                    int subSum = sumRegion(i-mid+1, j-mid+1, i, j);
                    if  (subSum > k) {
                        stop = true;
                    }
                }
            }
            if (stop) {
                high = mid - 1;
            } else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans;
    }
}

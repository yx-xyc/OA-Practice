package Cisco;

import java.util.*;

public class RowMaxColMin {
    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1,2));
        matrix.add(Arrays.asList(3,4));
        // for (List<Integer> row : matrix) {
        //     for (int n : row) {
        //         System.out.print(n+" ");
        //     }
        //     System.out.println();
        // }
        System.out.print(findRowMaxColMin(matrix));
    }

    public static int findRowMaxColMin(List<List<Integer>> matrix) {
        int rowNum = matrix.size();
        int colNum = matrix.get(0).size();

        int[] rowMaxes = new int[rowNum];
        int[] colMins = new int[colNum];
        Arrays.fill(rowMaxes, 0);
        Arrays.fill(colMins, Integer.MAX_VALUE);

        for (int i=0;i<rowNum;i++) {
            for (int j=0;j<colNum;j++) {
                rowMaxes[i] = Math.max(rowMaxes[i], matrix.get(i).get(j));
                colMins[j] = Math.min(colMins[j], matrix.get(i).get(j)); 
            }
        }

        int ans = -1;

        for (int i=0;i<rowNum;i++) {
            for (int j=0;j<colNum;j++) {
                if (matrix.get(i).get(j)==rowMaxes[i] && matrix.get(i).get(j)==colMins[j]) {
                    ans = matrix.get(i).get(j);
                    break;
                }
            }
        }
        return ans;

    }

}

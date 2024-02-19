package Cisco;

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9},
            {10,11,12}
        };
        int[][] result = rotateMatrix(matrix);
        for (int[] row : matrix) {
            for (int n : row) {
                System.out.print(n+"\t");
            }
            System.out.println();
        }

        for (int[] row : result) {
            for (int n : row) {
                System.out.print(n+"\t");
            }
            System.out.println();
        }
    } 

    public static int[][] rotateMatrix(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;

        int[][] result = new int[colNum][rowNum];
        for (int j=0;j<colNum;j++) {
            for (int i=rowNum-1;i>=0;i--) {
                result[j][rowNum-1-i] = matrix[i][j];
            }
        }


        return result;
    }

}

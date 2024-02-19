public class EditDistance {
    public static void main(String[] args) {
        
    }
    public static int minDistanceRe(String word1, String word2) {
        return minDistanceRecur(word1, word2, word1.length(), word2.length());
    }
    private static int minDistanceRecur(String word1, String word2, int word1Index, int word2Index) {
        if (word1Index==0) {
            return word2Index;
        }
        if (word2Index==0) {
            return word1Index;
        }
        if (word1.charAt(word1Index-1)==word2.charAt(word2Index-1)) {
            return minDistanceRecur(word1, word2, word1Index-1, word2Index-1);
        } else {
            int insertOperation = minDistanceRecur(word1, word2, word1Index, word2Index-1);
            int deleteOperation = minDistanceRecur(word1, word2, word1Index-1, word2Index);
            int replaceOperation = minDistanceRecur(word1, word2, word1Index-1, word2Index-1);
            return Math.min(insertOperation, Math.min(deleteOperation, replaceOperation))+1;
        }
    }
    public static int minDistanceTDDP(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i=0;i<=word1.length();i++) {
            for (int j=0;j<=word2.length();j++) {
                if (i==0) {
                    dp[i][j] = j;
                } else if (j==0) {
                    dp[i][j] = i;
                } else if (word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int insertOperation = dp[i][j-1];
                    int deleteOperation = dp[i-1][j];
                    int replaceOperation = dp[i-1][j-1];
                    dp[i][j] = Math.min(insertOperation, Math.min(deleteOperation, replaceOperation))+1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

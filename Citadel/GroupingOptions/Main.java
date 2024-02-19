public class Main {
    public static void main(String[] args) {
        System.out.println(nToKGroups(8, 3));
    }

    public static int nToKGroups(int n, int k) {
        if (n < k) {
            return 0;
        }
        int[][] dp = new int[n + 1][k + 1];
        for (int i=1;i<=n;i++) {
            dp[i][1] = 1;
        }
        dp[0][0] = 1;
        for (int i=1;i<=n;i++) {
            for (int j=2;j<=k;j++) {
                if (i>=j) {
                    dp[i][j] = dp[i-j][j] + dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[n][k];
    }
}

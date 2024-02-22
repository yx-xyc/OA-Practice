public class Main {
    private static final int MOD = 1000000007;
    
    public static int countWaysToColorHouses(int n) {
        if (n % 2 != 0) {
            return 0; // n must be even as per the problem statement
        }

        long[] dp = new long[n + 1];
        dp[1] = 3;
        dp[2] = 6; // base case, 3 colors for the first house and 2 choices for the second house

        for (int i = 4; i <= n; i += 2) {
            dp[i] = (dp[i - 2] * 3) % MOD; // 2 choices for the new house at the start and 2 choices for the end
        }

        return (int) dp[n];
    }
    
    public static void main(String[] args) {
        int n = 4;
        System.out.println("Number of ways to paint " + n + " houses is: " + countWaysToColorHouses(n));
    }
}

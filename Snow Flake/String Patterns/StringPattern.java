public class StringPattern {
    public static void main(String[] args) {
        System.out.println(calculateWays(1, 1));
        System.out.println(calculateWays(4, 1));
        System.out.println(calculateWays(4, 2));

    }
    public static int calculateWays(int wordLen, int maxVowels) {
        int N = wordLen;
        int K = maxVowels;
        int i, j;
        long mod = 1000000007;
        long[][] dp = new long[N+1][K+1];
        long sum = 1;
        for (i=1;i<=N;i++) {
            dp[i][0] = sum*21;
            dp[i][0] %= mod;
            sum = dp[i][0];

            for (j=1;j<=K;j++) {
                if (j>i) {
                    dp[i][j] = 0;
                } else if (j==i) {
                    dp[i][j] = power(5, i, mod);
                } else {
                    dp[i][j] = dp[i-1][j-1]*5;
                }
                dp[i][j] %= mod;
                sum += dp[i][j];
                sum %= mod;
            }
        }
        return (int)sum;
    }

    // raise x to the power y, in mod p
    private static long power(long x, long y, long p) {
        long res = 1;
        x = x%p;
        // power of 0 is always 0
        if (x==0) {
            return 0;
        }
        
        while (y>0) {
            if ((y&1)!=0) {
                res = (res*x)%p;
            }
            y=y>>1;
            x=(x*x)%p;
        }
        return res;
    }
}

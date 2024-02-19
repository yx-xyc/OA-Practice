public class Main{
    public int deleteAndEarn(int[] nums) {
        int[] counts = new int[10001];
        for (int num : nums) {
            counts[num] += num;
        }
        int[] dp = new int[10001];
        dp[1] = counts[1];
        for (int i=2;i<10001;i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+counts[i]);
        }
        return dp[10000];
    }
}
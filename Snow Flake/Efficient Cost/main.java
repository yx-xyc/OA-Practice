public class main {
    private static int calculateCost(int[] arr, int threshold) {
        int n = arr.length;
        int[] dp = new int[n + 1];
        int[] maxInRange = new int[n];

        // Precompute the maximum value for each subarray starting at index i and length <= threshold
        for (int i = 0; i < n; i++) {
            maxInRange[i] = arr[i];
            for (int j = i + 1; j < i + threshold && j < n; j++) {
                maxInRange[i] = Math.max(maxInRange[i], arr[j]);
            }
        }

        // Initialize DP array; we will build this up for each subarray length
        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j <= threshold && i - j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + maxInRange[i - j]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 2, 6};
        int threshold = 3;
        System.out.println("The minimum cost to partition the array: " + calculateCost(arr, threshold));
    }
}

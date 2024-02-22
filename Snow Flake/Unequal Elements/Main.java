import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static int findMaxSubsequenceLength(int[] skills, int k) {
        int n = skills.length;
        // dp[i][j] stores the maximum subsequence length considering up to the i-th skill with j changes
        int[][] dp = new int[n + 1][k + 2];
        // Initialize the array with -1 to indicate that no subsequence has been formed yet
        for (int[] row : dp) Arrays.fill(row, -1);
        // Base case: with 0 changes the subsequence length is 1
        Arrays.fill(dp[0], 1);
        
        // lastOccurrence[i] will store the last index of skill i
        int[] lastOccurrence = new int[2001]; // Considering the constraint, the skill values are up to 2000
        Arrays.fill(lastOccurrence, -1);

        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= k + 1; ++j) {
                // If we do not take the current skill
                if (j > 0) dp[i][j] = dp[i - 1][j - 1];
                // If we take the current skill
                int last = lastOccurrence[skills[i - 1]];
                if (last != -1) {
                    // If there is a previous occurrence of this skill
                    dp[i][j] = Math.max(dp[i][j], dp[last][j] + i - last);
                } else {
                    // If this is the first occurrence of the skill
                    dp[i][j] = Math.max(dp[i][j], i);
                }
            }
            // Update the last occurrence of the current skill
            lastOccurrence[skills[i - 1]] = i;
        }

        // The answer will be the maximum value from the last row of dp
        int maxLength = 0;
        for (int length : dp[n]) {
            maxLength = Math.max(maxLength, length);
        }
        return maxLength - 1; // Subtract one because we started counting from 1
    }

    public static void main(String[] args) {
        int[] skills = {1, 2, 2, 2, 3, 1};
        int k = 2;
        System.out.println("Maximum subsequence length: " + findMaxSubsequenceLength(skills, k));    }

}

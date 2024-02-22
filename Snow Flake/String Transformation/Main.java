import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final int MOD = 1000000007;
    private static Map<String, Integer> memo; // Memoization cache

    // Helper method to check if the transformation is valid
    private static boolean isValidTransformation(String src, String target) {
        // Sort both strings and compare them to check if they consist of the same characters
        char[] srcArray = src.toCharArray();
        char[] targetArray = target.toCharArray();
        Arrays.sort(srcArray);
        Arrays.sort(targetArray);
        return Arrays.equals(srcArray, targetArray);
    }

    // Main method to get the number of ways to transform src into target in k steps
    public static int getNumWays(String src, String target, int k) {
        // Initialize memoization map on first call
        if (memo == null) {
            memo = new HashMap<>();
        }
        // Check memoization map for cached result
        String key = src + "_" + k;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        if (!isValidTransformation(src, target)) {
            memo.put(key, 0);
            return 0;
        }

        // If k is 0, check if src and target are already the same
        if (k == 0) {
            int result = src.equals(target) ? 1 : 0;
            memo.put(key, result);
            return result;
        }

        // If k is 1, check if there is a single transformation possible
        if (k == 1) {
            for (int i = 1; i < src.length(); i++) {
                if ((src.substring(i) + src.substring(0, i)).equals(target)) {
                    memo.put(key, 1);
                    return 1;
                }
            }
            memo.put(key, 0);
            return 0;
        }

        // For k > 1, we recursively find all possible transformations
        int count = 0;
        for (int i = 1; i < src.length(); i++) {
            String transformed = src.substring(i) + src.substring(0, i);
            count += getNumWays(transformed, target, k - 1);
            count %= MOD;
        }

        // Cache the result before returning
        memo.put(key, count);
        return count;
    }

    public static void main(String[] args) {
        memo = new HashMap<>(); // Initialize the memoization map
        String src = "abcd";
        String target = "cdab";
        int k = 2;
        System.out.println(getNumWays(src, target, k)); // Output should be 2
    }
}

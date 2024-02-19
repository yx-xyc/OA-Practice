import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, -1, -2, 5};
        int k = 3;
        int result = Solution.shortestSubarray(nums, k);
        System.out.println(result);
    }

}
class Solution {
    public static int shortestSubarray(int[] nums, int k) {
        int length = nums.length;
        long[] prefix = new long[length+1];
        for (int i=0;i<length;i++) {
            prefix[i+1] = prefix[i] + nums[i];
        }
        int ans = 0;
        Deque<Integer> monoq = new LinkedList<>();
        for (int i=0;i<prefix.length;i++) {
            while (monoq.size()>0 && prefix[i]-prefix[monoq.getFirst()]<=k) {
                ans = Math.max(ans, i-monoq.pollFirst());
                monoq.addLast(i);
            }
            monoq.addLast(i);
        }
        return ans <= length ? ans : -1;
    }
}
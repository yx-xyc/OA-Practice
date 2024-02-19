import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 26;
        byte bn = (byte) n;
        BitSet bs = BitSet.valueOf(new byte[] {bn});
        for (int i=0;i<bs.length();i++) {
            System.out.println(bs.get(i));
            System.out.println(i);
        }

    }
}

class Solution {
    public static int balancedSum(int[] nums) {
        int sum = 0, leftsum = 0;
        for (int x: nums) sum += x;
        for (int i = 0; i < nums.length; ++i) {
            if (leftsum == sum - leftsum - nums[i]) return i;
            leftsum += nums[i];
        }
        return -1;    }
}
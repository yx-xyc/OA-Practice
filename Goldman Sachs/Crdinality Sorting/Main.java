import java.util.*;
public class Main {
    public static void main(String[] args) {
        int[] a = {0,1,2,3,4,5,6,7,8};
        int[] result = Solution.cardinalitySort(a);
        for (int i=0;i<a.length;i++) {
            System.out.println(result[i]);
        }
    }
}

class Solution {
    public static int[] cardinalitySort(int[] nums) {
        for (int i=0;i<nums.length;i++) {
            nums[i] += Integer.bitCount(nums[i])*10001;
        }
        Arrays.sort(nums);
        for (int i=0;i<nums.length;i++) {
            nums[i] = nums[i]%10001;
        }
        return nums;
    }
}
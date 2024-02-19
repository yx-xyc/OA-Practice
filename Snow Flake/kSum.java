import java.util.*;

public class kSum {
    public static void main(String[] args) {
        
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return findKSum(nums, target, 0, 4);
    }
    public List<List<Integer>> findKSum(int[] nums, long target, int start, int k) {
        // store final result
        List<List<Integer>> res = new ArrayList<>();
        // base case 1: no more num could be chosen
        if (start == nums.length) {
            return res;
        }
        // trimming
        long average_value = target/k;
        // if the smallest remaining value is larger than desired average, return
        // if the larges remaining value is smaller than desired average, return
        if (nums[start]>average_value || average_value>nums[nums.length-1]) {
            return res;
        }
        // base case 2: find all k needed nums
        if (k==2) {
            return twoSum(nums, target, start);
        }
        // normal case
        // for indexes [start, end]
        for (int i=start;i<nums.length;i++) {
            // when we encounter a new number
            if (i==start||nums[i-1]!=nums[i]) {
                // for all the subset that satisfied the target by include this new number
                for (List<Integer> subset : findKSum(nums, target-nums[i], i+1, k-1)) {
                    // add them to the result
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size()-1).addAll(subset);
                }
            }
        }
        return res;
    }
    // serve as the based case function 
    public List<List<Integer>> twoSum(int[] nums, long target, int start) {
        // store result
        List<List<Integer>> res = new ArrayList<>();
        // two pointers
        int lo = start, hi = nums.length-1;
        // loop through all possible pair
        while (lo<hi) {
            int currSum = nums[lo] + nums[hi];
            if (currSum<target || (lo>start && nums[lo]==nums[lo-1])) {
                lo++;
            } else if (currSum>target ||(hi<nums.length-1 && nums[hi]==nums[hi+1])) {
                hi--;
            } else {
                // add satisfed pair into res
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
            }
        }
        // return all satisfied pair
        return res;
    }
}

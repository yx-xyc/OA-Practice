/*
 * Click `Run` to execute the snippet below!
 */

import java.io.*;
import java.util.*;

/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the  frequency of at least one of the chosen numbers is different.

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3], [7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]

Example 3:
Input: candidates = [2], target = 1
Output: []
 */

class Solution {
  public static void main(String[] args) {
    int[] test = {2, 3, 6, 7};
    ArrayList<Integer> testCase = new ArrayList<>();
    for (int i=0;i<test.length;i++) {
      testCase.add(test[i]);
    }
    int target = 7;
    ArrayList<ArrayList<Integer>> result = get_all_combos(testCase, target);
    System.out.println(result);

  }

  public static ArrayList<ArrayList<Integer>> get_all_combos(ArrayList<Integer> candidates, int target) {
    ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    ArrayList<Integer> curList = new ArrayList<>();
    helper(candidates, target, 0, result, curList, 0);
    return result;
  }

  public static void helper(ArrayList<Integer> candidates, int target, int index, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> curList, int curSum) {
    if (index >= candidates.size()){
      return;
    }
    if (curSum==target) {
    //   System.out.println("Found 1");
      result.add(curList);
      return;
    }
    if (curSum > target) {
    //   System.out.println(curSum);
      return ;
    }
    // don't moveforward the index
    ArrayList<Integer> curCopy = new ArrayList<>();
    for (int i=0;i<curList.size();i++) {
      curCopy.add((int)curList.get(i));
    }
    curCopy.add((int)candidates.get(index));
    // curList.add(candidates.get(index));
    helper(candidates, target, index, result, curCopy, curSum+candidates.get(index));
    // curList.remove(curList.size()-1);
    helper(candidates, target, index+1, result, curList, curSum);
  }

}

package Cisco;

import java.util.*;

public class MaxForwardDiff {
    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(2,3,10,6,4,8,1,11);
        System.out.print(findMaxForwardDiff(arr));
    }

    public static int findMaxForwardDiff(List<Integer> arr) {
        if (arr.size()<=1) return -1;
        int maxDiff = 0;
        int min = arr.get(0);
        for (int i=1;i<arr.size();i++) {
            if (arr.get(i)>min) {
                maxDiff = Math.max(maxDiff, arr.get(i)-min);
            } else {
                min = arr.get(i);
            }
        }
        return maxDiff;
    }
}

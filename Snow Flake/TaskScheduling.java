import java.util.*;
import java.util.stream.IntStream;
public class TaskScheduling {
    public static void main(String[] args) {
        
        System.out.println("Expected: 1, actual: " + getMinCost(new int[] {1,1,3,4}, new int[] {3,1,2,3}));
        System.out.println("Expected: 3, actual: " + getMinCost(new int[] {1,2,3,2}, new int[] {1,2,3,2}));
        System.out.println("Expected: 4, actual: " + getMinCost(new int[] {2,3,4,2}, new int[] {1,1,1,1}));
        System.out.println("Expected: 4, actual: " + getMinCost(new int[] {2,3,4,5}, new int[] {1,1,5,3}));
        System.out.println("Expected: 10, actual: " + getMinCost(new int[] {5,6,7,8,8,10}, new int[] {1,1,1,1,1,10}));
        System.out.println("Expected: 20, actual: " + getMinCost(new int[] {10,10,10,10,10,20,20,20}, new int[] {3,3,3,3,3,6,6,6}));
        System.out.println("Expected: 18, actual: " + getMinCost(new int[] {5,6,7,8,1000000}, new int[] {1,1,1,1,1000}));
        // 1000 size example
        List<Integer> costList = List.of(6,3,9,5,1000000,2,3,4,4,5);
        List<Integer> timeList = List.of(3,1,2,3,1000,1,1,1,1,7);
        List<Integer> largeCostList = new ArrayList<>();
        List<Integer> largeTimeList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeCostList.addAll(costList);
            largeTimeList.addAll(timeList);
        }
        int[] cost = largeCostList.stream().mapToInt(Integer::intValue).toArray();
        int[] time = largeTimeList.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("Expected: 700, actual: " + getMinCost(cost, time));
        // 1000 size example 2
        int[] cost2 = IntStream.iterate(1000000, i->1000000).limit(1000).toArray();
        int[] time2 = IntStream.iterate(1000, i->1000).limit(1000).toArray();
        System.out.println("Expected: 1000000, actual: " + getMinCost(cost2, time2));
    }
    private static final long INF = Long.MAX_VALUE / 10;
    public static long getMinCost(int[] cost, int[] time) {
        List<Map<Integer, Long>> dp = new ArrayList<>();
        // for each job, create a HashMap
        for (int i=0;i<cost.length;i++) {
            dp.add(new HashMap<>());
        }
        return dfs(0, cost, 0, time, dp);
    }
    // i : current index
    // timeSoFar: how many time spent so far
    private static long dfs(int i, int[] cost, int timeSoFar, int[] time, List<Map<Integer, Long>> dp){
        int n = cost.length;
        // when idx out of bound, 
        // if timeSoFar >= 0, return 0(no extra time to add) 
        // else timeSoFar < 0, to many job scheduled on free server, return INF(not possible)
        if (i==n) return timeSoFar >= 0 ? 0 : INF;
        // cancel out these tasks scheduled on free server
        // if the time spent so far is large or equal to the number of left task
        //     all the tasks left can be scheduled to free server (no extra time to add)
        //     return 0
        if (timeSoFar >= n - i) return 0;
        // if we get a combination of (i, timeSoFar) that is appeared in dp
        //     we don't have to go to that branch further
        //     return the minimal time we can need to spent from that branch
        if (dp.get(i).containsKey(timeSoFar)) return dp.get(i).get(timeSoFar);
        // For this current idx and timeSoFar, there are only two possible scenario
        // we schedule current job on a paid server
        long resPaid = cost[i] + dfs(i+1, cost, timeSoFar+time[i], time, dp);
        // we schedule current job on a free server 
        long resFree = dfs(i+1, cost, timeSoFar-1, time, dp);
        // add current scenario (not contained in dp) into dp
        dp.get(i).put(timeSoFar, Math.min(resPaid, resFree));
        // return the result
        return dp.get(i).get(timeSoFar);
    }
}

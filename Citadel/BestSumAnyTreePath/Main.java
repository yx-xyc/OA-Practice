import java.util.*;

public class Main{
    private List<List<Integer>> adjList;
    private int[] values;
    private int maxSum;
    public static void main(String[] args) {
        Main m = new Main();
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        int[] values = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(m.bestSumAnyTreePath(parent, values)); 
        parent = new int[] {-1, 0, 1, 2, 0};
        values = new int[] {5, 7, -10, 4, 15};
        System.out.println(m.bestSumAnyTreePath(parent, values)); 

    }
    public int bestSumAnyTreePath(int[] parent, int[] values) {
        int n = parent.length;
        this.adjList = new ArrayList<>();
        for (int i=0;i<n;i++) {
            this.adjList.add(new ArrayList<>());
        }
        for (int i=1;i<n;i++) {
            this.adjList.get(parent[i]).add(i);
        }
        this.values = values;
        this.maxSum = Integer.MIN_VALUE;
        this.dfs(0);
        return this.maxSum;
    }
    private int dfs(int node) {
        int maxDownPath = this.values[node];
        int maxThroughPath = this.values[node];
        List<Integer> childrenSums = new ArrayList<>();
        for (int child: adjList.get(node)) {
            childrenSums.add(dfs(child));
        }
        childrenSums.sort((a,b)->Integer.compare(b, a));
        if (!childrenSums.isEmpty()) {
            maxDownPath = Math.max(maxDownPath, this.values[node]+childrenSums.get(0));
        }
        if (childrenSums.size()>1) {
            maxThroughPath = Math.max(maxThroughPath, this.values[node]+childrenSums.get(0)+childrenSums.get(1));
        }
        this.maxSum = Math.max(this.maxSum, maxThroughPath);
        return maxDownPath;
    }

}
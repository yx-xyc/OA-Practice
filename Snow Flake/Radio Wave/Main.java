import java.util.Arrays;

import java.util.Arrays;

import java.util.*;

public class Main {
    private static final int MAX_NODES = 100005;
    private static List<Integer>[] graph = new ArrayList[MAX_NODES];
    private static int[] frequency = new int[MAX_NODES];
    private static boolean[] visited = new boolean[MAX_NODES];
    private static int maxDistance = 0;
    public static void main(String[] args) {
        int nodes = 4;
        int[] edges_from = {1, 2, 3};
        int[] edges_to = {2, 3, 4};
        int[] freqs = {1, 3, 2, 1};
        System.out.println("The longest path is: " + longestPath(nodes, edges_from, edges_to, freqs));
    }
    public static int longestPath(int nodes, int[] edges_from, int[] edges_to, int[] freqs) {
        // Initialize graph
        for (int i = 1; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }
        // Build the graph
        for (int i = 0; i < edges_from.length; i++) {
            int u = edges_from[i];
            int v = edges_to[i];
            graph[u].add(v);
            graph[v].add(u);
        }
        // Copy frequencies
        System.arraycopy(
            freqs,     // from array 
            0,  // start index
            frequency, // to array
            1, // start index
            nodes      // number of elem to copy
        );
        // Run DFS from each node
        // For each DFS, we found the maxDistance start from current node
        // then mark all nodes as unvisited and do the same for the next node
        for (int i = 1; i <= nodes; i++) {
            Arrays.fill(visited, false);
            dfs(i, 0);
        }
        return maxDistance;
    }
    // the dfs here is actually backtracking, since we don't want any path to be omitted
    private static void dfs(int node, int distance) {
        // mark current node as visited
        visited[node] = true;
        // update the maxDistance
        maxDistance = Math.max(maxDistance, distance);
        // iterate over all the neighbor nodes
        for (int nextNode : graph[node]) {
            // if we found a node has not been visited yet and it is reachable
            // don't visit the same node multiple times to avoid infinite loop
            if (!visited[nextNode] && Math.abs(frequency[node] - frequency[nextNode]) == 1) {
                dfs(nextNode, distance + 1);
            }
        }
        // backtrack to assure all the possible paths are explored
        visited[node] = false;
    }
}

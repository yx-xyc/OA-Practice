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
        System.arraycopy(freqs, 0, frequency, 1, nodes);

        // Run DFS from each node
        for (int i = 1; i <= nodes; i++) {
            Arrays.fill(visited, false);
            dfs(i, 0);
        }

        return maxDistance;
    }

    private static void dfs(int node, int distance) {
        visited[node] = true;
        maxDistance = Math.max(maxDistance, distance);

        for (int nextNode : graph[node]) {
            if (!visited[nextNode] && Math.abs(frequency[node] - frequency[nextNode]) == 1) {
                dfs(nextNode, distance + 1);
            }
        }

        visited[node] = false;
    }
}

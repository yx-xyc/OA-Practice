// leet code version
class Main {
    private List<List<Integer>> adjList; // store the graph as adjacency list
    private int maxDiameter; // store the maxDiameter value
    private int farthestNode; // store the index of farthestNode

    public int treeDiameter(int[][] edges) {
        // create the undirected graph
        int n = edges.length + 1;
        adjList = new ArrayList<>();
        for (int i=0;i<n;i++) {
            adjList.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            // undirected graph
            // since you go from either end to the other, both edges should be added to the graph
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        
        maxDiameter = 0;
        dfs(0, -1, 0);
        maxDiameter = 0;
        dfs(farthestNode, -1, 0);
        return maxDiameter;
    }

    private void dfs(int node, int parent, int depth) {
        // if the depth is larger than teh max diameter
        // update the maxDiameter and the farthestNode
        if (depth > maxDiameter){
            maxDiameter = depth;
            farthestNode = node;
        }
        for (int child : adjList.get(node)) {
            // avoid revisit node by checking parent
            // it works since in tree structure, there is no cycle and there is only one edge between any two nodes
            if (child != parent) {
                dfs(child, node, depth+1);
            }
        }
    }
}

import java.util.*;

public class SpecialNodes {

    public static List<Integer> isSpecial(int treeNodes, List<Integer> treeFrom, List<Integer> treeTo) {
        // initialize the graph
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= treeNodes; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < treeFrom.size(); i++) {
            graph.get(treeFrom.get(i)).add(treeTo.get(i));
            graph.get(treeTo.get(i)).add(treeFrom.get(i));
        }

        // find the fist farthest endpoint
        int[] end1 = bfs(graph, 1);
        // start from the first farthest endpoint, find another farthest endpoint so that we have the diameter
        int[] end2 = bfs(graph, end1[0]);
        int diameter = end2[1];

        // calculate the distances of all nodes from end1 and end2
        int[] distanceToEnd1 = bfsDistances(graph, end1[0]);
        int[] distanceToEnd2 = bfsDistances(graph, end2[0]);

        // iterate through all the nodes, for each of them, if the distance between the node to one of the end is equal to the diameter, add 1, otherwise 0
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= treeNodes; i++) {
            if (distanceToEnd1[i] == diameter || distanceToEnd2[i] == diameter) {
                result.add(1);
            } else {
                result.add(0);
            }
        }

        return result;
    }

    // bfs to search for the farthest node
    public static int[] bfs(List<List<Integer>> graph, int start) {
        // initialize a list of boolean to store visited nodes
        boolean[] visited = new boolean[graph.size()];
        // initialize a q with LinkedList as underlying structure
        Queue<int[]> queue = new LinkedList<>();
        // add the start point into the queue
        queue.add(new int[]{start, 0});
        // setup initial status
        int farthestNode = start;
        int maxDistance = 0;
        // start bfs
        while (!queue.isEmpty()) {
            // fetch current node from the queue
            int[] current = queue.poll();
            int node = current[0];
            int distance = current[1];

            // if current node has been visited, continue to the next iteration
            if (visited[node]) {
                continue;
            }
            // otherwise, mark current node as visited
            visited[node] = true;
            // compare to the maxDistance, update is necessary
            if (distance > maxDistance) {
                maxDistance = distance;
                farthestNode = node;
            }
            // loop through all the neighbors of current node
            for (int neighbor : graph.get(node)) {
                // if the neighbor has not been visited, add it to queue with current distance + 1
                if (!visited[neighbor]) {
                    queue.add(new int[]{neighbor, distance + 1});
                }
            }
        }

        return new int[]{farthestNode, maxDistance};
    }

    // bfs to calculate the distances from all other nodes to current start`
    public static int[] bfsDistances(List<List<Integer>> graph, int start) {
        int n = graph.size();
        int[] distances = new int[n];
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        distances[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    distances[neighbor] = distances[node] + 1;
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }

    public static void main(String[] args) {
        List<Integer> treeFrom = Arrays.asList(1, 1, 2, 3);
        List<Integer> treeTo = Arrays.asList(2, 3, 4, 5);
        System.out.println(isSpecial(5, treeFrom, treeTo)); // Expected output: [1, 0, 0, 0, 1]
    }
}
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.*;

public class Main{
    private static Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
    private static int[] threads;
    public static void main(String[] args) {
        int serviceNodes1 = 3;
        List<Integer> serviceFrom1 = Arrays.asList(1, 1);
        List<Integer> serviceTo1 = Arrays.asList(2, 3);
        List<List<Integer>> currentValues1 = Arrays.asList(Arrays.asList(2, 4), Arrays.asList(3, 6));
        int[] result1 = getMinSumNodeValues(serviceNodes1, serviceFrom1, serviceTo1, currentValues1.size(), currentValues1);
        System.out.println(Arrays.toString(result1));

        int serviceNodes2 = 4;
        List<Integer> serviceFrom2 = Arrays.asList(1, 2, 2);
        List<Integer> serviceTo2 = Arrays.asList(2, 3, 4);
        List<List<Integer>> currentValues2 = Arrays.asList(Arrays.asList(1, 3), Arrays.asList(2, 4), Arrays.asList(3, 3));
        int[] result2 = getMinSumNodeValues(serviceNodes2, serviceFrom2, serviceTo2, currentValues2.size(), currentValues2);
        System.out.println(Arrays.toString(result2));
    }
    public static int[] getMinSumNodeValues(int serviceNodes, List<Integer> serviceFrom, List<Integer> serviceTo, int k, List<List<Integer>> currentValues) {
        // Initialize threads array and adjacency list
        adjacencyList = new HashMap<>();
        threads = new int[serviceNodes + 1];
        Arrays.fill(threads, -1);
        for (int thread : threads) {
            System.out.print(thread);
        }
        for (int i = 0; i < serviceFrom.size(); i++) {
            adjacencyList.computeIfAbsent(serviceFrom.get(i), x -> new ArrayList<>()).add(serviceTo.get(i));
            adjacencyList.computeIfAbsent(serviceTo.get(i), x -> new ArrayList<>()).add(serviceFrom.get(i));
        }
        // Set known thread values
        Queue<Integer> queue = new LinkedList<>();
        for (List<Integer> currentValue : currentValues) {
            int node = currentValue.get(0);
            int value = currentValue.get(1);
            threads[node] = value;
            queue.add(node);
        }
        // BFS to set thread values for all nodes
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int adj : adjacencyList.get(node)) {
                // we need to assign a value to it
                if (threads[adj] == -1) {
                    threads[adj] = getValue(adj);
                    queue.add(adj);
                }
            }
        }
        // Convert threads array to result excluding index 0
        return Arrays.copyOfRange(threads, 1, threads.length);
    }
    private static List<Integer> getPossibleValues(int currentValue) {
        return Arrays.asList(currentValue - 1, currentValue + 1);
    }
    private static int getValue(int node) {
        List<List<Integer>> allPotentialValues = new ArrayList<>();
        for (int adj : adjacencyList.get(node)) {
            if (threads[adj] != -1) {
                List<Integer> potentialValues = getPossibleValues(threads[adj]);
                allPotentialValues.add(potentialValues);
            }
        }
        Set<Integer> intersection = findIntersection(allPotentialValues);
        return Collections.min(intersection);
    }
    public static Set<Integer> findIntersection(List<List<Integer>> lists) {
        if (lists.isEmpty()) return new HashSet<>();
        Set<Integer> result = new HashSet<>(lists.get(0));
        for (int i = 1; i < lists.size(); i++) {
            Set<Integer> currentSet = new HashSet<>(lists.get(i));
            result.retainAll(currentSet);
        }
        return result;
    }
}
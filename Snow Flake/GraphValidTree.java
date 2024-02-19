public class GraphValidTree {
    public static void main(String[] args) {

    }   
    // determine whether or not the graph is fully connected
    // if it is and contains n-1 edges -> it is a tree
    // else it's not a tree
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n-1) return false;
        UnionFind unionFind = new UnionFind(n);
        for (int[] edge : edges) {
            int A = edge[0];
            int B = edge[1];
            if (!unionFind.union(A, B)) {
                return false;
            }
        }
        return true;
    }
}
class UnionFind {
    
    private int[] parent;
    private int[] size;

    public UnionFind(int n) {
        parent = new int[n];
        size = new int[n];
        for (int node=0;node<n;node++) {
            parent[node] = node;
            size[node] = 1;
        }
    }

    public int find(int A) {
        int root = A;
        while (parent[root]!=root) {
            root = parent[root];
        }
        while (A!=root){
            int oldRoot = parent[A];
            parent[A] = root;
            A = oldRoot;
        }
        return root;
    }

    public boolean union(int A, int B) {
        int rootA = find(A);
        int rootB = find(B);
        if (rootA == rootB) {
            return false;
        }
        if (size[rootA] < size[rootB]) {
            parent[rootA] = rootB;
            size[rootB] += size[rootA];
        } else {
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
        }
        return true;
    }

}
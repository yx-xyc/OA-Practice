class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
 
    TreeNode(int x)
    {
        val = x;
        left = null;
        right = null;
    }
}

public class BTStartEndPath {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        int[][] tests = {
            {5, 7},
            {4, 3},
            {1, 5}
        };
        for (int[] test : tests) {
            String ans = findPath(root, test[0], test[1]);
            System.out.println(ans);
        }
    }
    public static String findPath(TreeNode root, int start, int end) {
        root = findLCA(root, start, end);
        StringBuilder p1 = new StringBuilder();
        StringBuilder p2 = new StringBuilder();
        findPathFromRoot(root, start, p1);
        findPathFromRoot(root, end, p2);
        for (int i=0;i<p1.length();i++) {
            p1.setCharAt(i, 'P');
        }
        return p1.append(p2).toString();

    }
    public static boolean findPathFromRoot(TreeNode root, int value, StringBuilder path) {
        if (root == null) {
            return false;
        }
        if (root.val == value) {
            return true;
        }
        path.append("L");
        boolean res = findPathFromRoot(root.left, value, path);
        if (res) {
            return true;
        }
        path.deleteCharAt(path.length()-1);
        path.append("R");
        res = findPathFromRoot(root.right, value, path);
        if (res) {
            return true;
        }
        path.deleteCharAt(path.length()-1);
        return false;
    }
    static TreeNode findLCA(TreeNode root, int start, int end) {
        if (root == null) {
            return null;
        }
        if (root.val == start) {
            return root;
        }
        if (root.val == end) {
            return root;
        }
        TreeNode left = findLCA(root.left, start, end);
        TreeNode right = findLCA(root.right, start, end);
        if (left!=null && right!=null) {
            return root;
        }
        return left == null ? right : left;
    }
}
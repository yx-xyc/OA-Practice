class Main {
    private static final int[][] DIRECTIONS = {
        {1, 2}, {2, 1}, {-1, 2}, {-2, 1},
        {1, -2}, {2, -1}, {-1, -2}, {-2, -1}
    };
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        boolean[][] visited = new boolean[605][605];
        visited[302][302] = true;
        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0;i<size;i++) {
                int[] curr = queue.poll();
                if (curr[0]==x && curr[1]==y) {
                    return steps;
                }
                for (int[] dir:DIRECTIONS) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];
                    if (!visited[newX+302][newY+302]&&newX>=-2&&newY>=-2) {
                        visited[newX+302][newY+302] = true;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }
            steps++;
        }
        return -1;
    }
}
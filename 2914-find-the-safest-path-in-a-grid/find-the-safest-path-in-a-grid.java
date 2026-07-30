class Solution {

    int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();

        // Step 1: Distance from each cell to nearest thief
        int[][] dist = new int[n][n];
        for (int[] row : dist) {
            Arrays.fill(row, -1);
        }

        Queue<int[]> queue = new LinkedList<>();

        // Add all thieves to the queue
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    queue.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        // Multi-Source BFS
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            int x = cell[0];
            int y = cell[1];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        int low = 0;
        int high = 2 * n;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (canReach(dist, mid)) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return high;
    }

    private boolean canReach(int[][] dist, int safe) {
        int n = dist.length;

        if (dist[0][0] < safe) {
            return false;
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();

            int x = cell[0];
            int y = cell[1];

            if (x == n - 1 && y == n - 1) {
                return true;
            }

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n
                        && !visited[nx][ny]
                        && dist[nx][ny] >= safe) {

                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return false;
    }
}
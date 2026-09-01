import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1;
        int startC = -1;

        // Give every litter a unique number
        int[][] litterId = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);

            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }
            }
        }

        // Number of litter cells
        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (classroom[i].charAt(j) == 'L') {
                    litterId[i][j] = litterCount;
                    litterCount++;
                }
            }
        }

        // No litter -> no moves needed
        if (litterCount == 0) {
            return 0;
        }

        // Example:
        // 3 litter -> 111 -> 7
        int allCollected = (1 << litterCount) - 1;

        /*
         * visited[row][col][mask][energy]
         *
         * mask = which litter has been collected
         * energy = remaining energy
         */
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        /*
         * Queue stores:
         * [row, col, energy, mask, moves]
         */
        Queue<int[]> queue = new LinkedList<>();

        // Starting state
        queue.offer(new int[]{
                startR,
                startC,
                energy,
                0,
                0
        });

        visited[startR][startC][0][energy] = true;

        // Up, Down, Left, Right
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] current = queue.poll();

            int r = current[0];
            int c = current[1];
            int currentEnergy = current[2];
            int mask = current[3];
            int moves = current[4];

            // All litter collected
            if (mask == allCollected) {
                return moves;
            }

            // Cannot move if energy is 0
            if (currentEnergy == 0) {
                continue;
            }

            // Try all 4 directions
            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                // Outside grid
                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n) {
                    continue;
                }

                // Obstacle
                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                // One move costs 1 energy
                int newEnergy = currentEnergy - 1;

                // Reset area
                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                // Copy current mask
                int newMask = mask;

                // If we reached litter
                if (classroom[nr].charAt(nc) == 'L') {

                    int id = litterId[nr][nc];

                    // Mark this litter as collected
                    newMask = newMask | (1 << id);
                }

                // Already visited this exact state
                if (visited[nr][nc][newMask][newEnergy]) {
                    continue;
                }

                visited[nr][nc][newMask][newEnergy] = true;

                // Add new state
                queue.offer(new int[]{
                        nr,
                        nc,
                        newEnergy,
                        newMask,
                        moves + 1
                });
            }
        }

        // Impossible to collect all litter
        return -1;
    }
}
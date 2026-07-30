class Solution {

    public int minScore(int n, int[][] roads) {

        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];

            graph[u].add(new int[]{v, w});
            graph[v].add(new int[]{u, w});
        }

        boolean[] visited = new boolean[n + 1];

        return dfs(1, graph, visited, Integer.MAX_VALUE);
    }

    private int dfs(int node, List<int[]>[] graph, boolean[] visited, int ans) {

        visited[node] = true;

        for (int[] next : graph[node]) {

            int neighbor = next[0];
            int weight = next[1];

            ans = Math.min(ans, weight);

            if (!visited[neighbor]) {
                ans = dfs(neighbor, graph, visited, ans);
            }
        }

        return ans;
    }
}
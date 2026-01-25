class Solution {
    private static boolean[] visited;
    private static int curK;
    private static int answer = -1;
    private static int n;
    private static int[][] map;
    public int solution(int k, int[][] dungeons) {
        n = dungeons.length;
        curK = k;
        visited = new boolean[n];
        map = dungeons;
        for (int i = 0; i < n; i++) {
            if (curK >= map[i][0]) {
                visited[i] = true;
                curK -= map[i][1];
                dfs(i, 1);
                visited[i] = false;
                curK += map[i][1];
            }
        }
        
        return answer;
    }
    
    private static void dfs(int start, int v) {
        answer = Math.max(v, answer);
        for (int i = 0; i < n; i++) {
            if (!visited[i] && (curK >= map[i][0])) {
                visited[i] = true;
                curK -= map[i][1];
                dfs(i, v + 1);
                visited[i] = false;
                curK += map[i][1];
            }
        }
    }
}
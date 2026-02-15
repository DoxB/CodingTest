import java.util.ArrayList;

class Solution {
    private static boolean[] visited;
    private static ArrayList<Integer>[] adj;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        visited = new boolean[n];
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (computers[i][j] == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                answer++;
                dfs(i);
            }
        }
        
        return answer;
    }
    
    private static void dfs(int s) {
        visited[s] = true;
        for (int nxt : adj[s]) {
            if (!visited[nxt]) {
                dfs(nxt);
            }
        }
    }
}
import java.util.ArrayList;

class Solution {
    private static ArrayList<Integer>[] adj;
    private static boolean[] visited;
    private static int cnt;
    
    public int solution(int n, int[][] wires) {
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            adj[wires[i][0]].add(wires[i][1]);
            adj[wires[i][1]].add(wires[i][0]);
        }
        
        int answer = 101;
        for (int i = 0; i < n - 1; i++) {
            visited = new boolean[n + 1];
            cnt = 0;
            adj[wires[i][0]].remove(Integer.valueOf(wires[i][1]));
            adj[wires[i][1]].remove(Integer.valueOf(wires[i][0]));
            dfs(1);
            answer = Math.min(answer, Math.abs((n - cnt) - cnt));
            
            adj[wires[i][0]].add(wires[i][1]);
            adj[wires[i][1]].add(wires[i][0]);
        }
        
        
        
        return answer;
    }
    
    private static void dfs(int num) {
        cnt++;
        visited[num] = true;
        for (int nxt : adj[num]) {
            if (!visited[nxt]) {
                dfs(nxt);
            }
        }
    }
}
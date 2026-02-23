import java.util.ArrayList;

class Solution {
    private static ArrayList<Integer>[] win;
    private static ArrayList<Integer>[] lose;
    private static int winCnt;
    private static int loseCnt;
    private static boolean[] visited;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        // A -> B 이기는 경우
        // B -> A 지는 경우
        win = new ArrayList[n + 1];
        lose = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            win[i] = new ArrayList<>();
            lose[i] = new ArrayList<>();
        }
        for (int i = 0; i < results.length; i++) {
            int w = results[i][0];
            int l = results[i][1];
            win[w].add(l);
            lose[l].add(w);
        }
        
        for (int i = 1; i <= n; i++) {
            visited = new boolean[n + 1];
            winCnt = 0;
            loseCnt = 0;
            dfs(i, 1);
            dfs(i, 0);
            if (winCnt + loseCnt == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
    
    private static void dfs(int cur, int flag) {
        visited[cur] = true;
        if (flag == 1) {
            for (int nxt : win[cur]) {
                if (!visited[nxt]) {
                    winCnt++;
                    dfs(nxt, flag);
                }
            }
        } else {
            for (int nxt : lose[cur]) {
                if (!visited[nxt]) {
                    loseCnt++;
                    dfs(nxt, flag);
                }
            }
        }
    }
}
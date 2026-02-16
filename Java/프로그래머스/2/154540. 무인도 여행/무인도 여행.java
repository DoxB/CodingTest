import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    private static char[][] adj;
    private static boolean[][] visited;
    private static int[] mY = {1, 0, -1, 0};
    private static int[] mX = {0, 1, 0, -1};
    private static int n;
    private static int m;
    private static int cnt;
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        adj = new char[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            adj[i] = maps[i].toCharArray();
        }
        
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && adj[i][j] != 'X') {
                    cnt = 0;
                    dfs(i, j);
                    arr.add(cnt);
                }
            }
        }
        if (arr.size() == 0) {
            return new int[]{-1};
        }
        
        int[] answer = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            answer[i] = arr.get(i);
        }
        
        Arrays.sort(answer);
        return answer;
    }
    
    private static boolean isOk(int y, int x) {
        if (0 <= y && y < n && 0 <= x && x < m) {
            return true;
        }
        return false;
    }
    
    private static void dfs(int y, int x) {
        visited[y][x] = true;
        cnt += Integer.parseInt(String.valueOf(adj[y][x]));
        for (int i = 0; i < 4; i++) {
            int nxtY = y + mY[i];
            int nxtX = x + mX[i];
            if (isOk(nxtY, nxtX) && !visited[nxtY][nxtX] && adj[nxtY][nxtX] != 'X') {
                dfs(nxtY, nxtX);
            }
        }
    }
}
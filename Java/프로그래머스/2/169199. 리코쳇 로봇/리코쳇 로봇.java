class Solution {
    private static int n;
    private static int m;
    private static char[][] adj;
    private static int[][] visited;
    private static int[] start;
    private static int[] end;
    
    private static int[] mY = {1, 0, -1, 0};
    private static int[] mX = {0, 1, 0, -1};
    
    public int solution(String[] board) {
        int answer = 0;
        n = board.length;
        m = board[0].length();
        adj = new char[n][m];
        visited = new int[n][m];
        start = new int[2];
        end = new int[2];
        
        for (int i = 0; i < n; i++) {
            char[] tmp = board[i].toCharArray();
            for (int j = 0; j < m; j++) {
                if (tmp[j] == 'R') {
                    start[0] = i;
                    start[1] = j;
                } else if (tmp[j] == 'G') {
                    end[0] = i;
                    end[1] = j;
                }
                adj[i][j] = tmp[j];
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        
        dfs(start[0], start[1], 0);
        answer = visited[end[0]][end[1]];
        if (answer == Integer.MAX_VALUE) {
            return -1;
        }
        return answer;
    }
    
    private static boolean isOk(int y, int x) {
        if (0 <= y && y < n && 0 <= x && x < m && adj[y][x] != 'D') {
            return true;
        }
        return false;
    }
    
    private static void dfs(int sY, int sX, int cnt) {
        visited[sY][sX] = cnt;
        for (int i = 0; i < 4; i++) {
            int nxtY = sY;
            int nxtX = sX;
            boolean flag = false;
            while (isOk(nxtY + mY[i], nxtX + mX[i])) {
                flag = true;
                nxtY += mY[i];
                nxtX += mX[i];
            }
            
            if (flag && visited[nxtY][nxtX] > cnt + 1) {
                dfs(nxtY, nxtX, cnt + 1);
            }

        }
    }
}
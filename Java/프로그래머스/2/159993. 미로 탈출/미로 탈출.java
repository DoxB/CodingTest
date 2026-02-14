import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private static int[][] adj;
    private static boolean[][] visited;
    private static int n;
    private static int m;
    private static int[] mY = {1, 0, -1, 0};
    private static int[] mX = {0, 1, 0, -1};
    
    public int solution(String[] maps) {
        int answer = 0;
        n = maps.length;
        m = maps[0].length();
        int[] start = new int[2];
        int[] lever = new int[2];
        int[] end = new int[2];
        
        adj = new int[n][m];
        for (int i = 0; i < n; i++) {
            char[] cArr = maps[i].toCharArray();
            for (int j = 0; j < m; j++) {
                if (cArr[j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                    adj[i][j] = 1;
                } else if (cArr[j] == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                    adj[i][j] = 1;
                } else if (cArr[j] == 'E') {
                    end[0] = i;
                    end[1] = j;
                    adj[i][j] = 1;
                } else if (cArr[j] == 'O') {
                    adj[i][j] = 1;
                }
            }
        }
        
        
        visited = new boolean[n][m];
        int first = bfs(start, lever);
        if (first == -1) {
            return -1;
        }
        visited = new boolean[n][m];
        int second = bfs(lever, end);
        if (second == -1) {
            return -1;
        }
        
        answer = first + second;
        
        return answer;
    }
    
    private static boolean isOk(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m;
    }
    
    private static int bfs(int[] start, int[] target) {
        Queue<MyNode> q = new LinkedList<>();
        visited[start[0]][start[1]] = true;
        q.add(new MyNode(start[0], start[1], 0));
        while (!q.isEmpty()) {
            MyNode m = q.remove();
            if (m.y == target[0] && m.x == target[1]) {
                return m.cnt;
            }
            int cnt = m.cnt;
            for (int i = 0; i < 4; i++) {
                int nxtY = m.y + mY[i];
                int nxtX = m.x + mX[i];
                if (isOk(nxtY, nxtX) && !visited[nxtY][nxtX] && adj[nxtY][nxtX] == 1) {
                    visited[nxtY][nxtX] = true;
                    q.add(new MyNode(nxtY, nxtX, cnt + 1));
                }
            }
        }
        return -1;
    }
}

class MyNode {
    int y;
    int x;
    int cnt;
    
    public MyNode(int y, int x, int cnt) {
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}
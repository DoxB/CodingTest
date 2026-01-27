import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private static int[] mY = {1, 0, -1, 0};
    private static int[] mX = {0, 1, 0 ,-1};
    private static boolean[][] visited;
    private static int maxY;
    private static int maxX;
    private static int[][] adj;
    
    public int solution(int[][] maps) {
        int answer = 0;
        maxY = maps.length;
        maxX = maps[0].length;
        visited = new boolean[maxY][maxX];
        adj = maps;
        
        answer = bfs(0, 0);
        
        return answer;
    }
    
    private static boolean isOk(int y, int x) {
        return 0 <= y && y < maxY && 0 <= x && x < maxX;
    }
    
    private static int bfs(int startY, int startX) {
        Queue<MyNode> q = new LinkedList<>();
        q.add(new MyNode(startY, startX, 1));
        visited[startY][startX] = true;
        while (!q.isEmpty()) {
            MyNode cur = q.remove();
            for (int i = 0; i < 4; i++) {
                int nxtY = cur.y + mY[i];
                int nxtX = cur.x + mX[i];
                int nxtCnt = cur.cnt + 1;
                if (isOk(nxtY, nxtX) && !visited[nxtY][nxtX] && (adj[nxtY][nxtX] == 1)) {
                    if (nxtY == maxY - 1 && nxtX == maxX - 1) {
                        return nxtCnt;
                    }
                    q.add(new MyNode(nxtY, nxtX, nxtCnt));
                    visited[nxtY][nxtX] = true;
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
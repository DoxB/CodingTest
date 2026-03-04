import java.util.Queue;
import java.util.LinkedList;

class Solution {
    private static int[] mY = {1, 0, -1, 0};
    private static int[] mX = {0, 1, 0, -1};
    private static int n;
    private static int[][][] dp;
    
    public int solution(int[][] board) {
        n = board.length;
        // 어느 방향에서 들어온지 구분해야함. 다른 방향에서 진행중인 것이 최솟값이 될 수도 있음
        // 방문여부대신 최솟값 여부로 다음 서치를 진행하는데, 이때 들어온 방향을 구분안하면 그런케이스가 무시됨
        dp = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        
        bfs(0, 0, board);
        
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, dp[n - 1][n - 1][i]);
        }
        return answer;
    }
    
    private static void bfs(int sY, int sX, int[][] board) {
        Queue<MyCar> q = new LinkedList<>();
        q.add(new MyCar(sY, sX, 0, -1));
        while (!q.isEmpty()) {
            MyCar curCar = q.remove();
            for (int i = 0; i < 4; i++) {
                int nxtY = curCar.y + mY[i];
                int nxtX = curCar.x + mX[i];
                int nxtV = curCar.value;
                if (isOk(nxtY, nxtX) && board[nxtY][nxtX] == 0) {
                    if (curCar.direct == -1 || curCar.direct == i) {
                        nxtV += 100;
                    } else {
                        nxtV += 600;
                    }
                    
                    if (dp[nxtY][nxtX][i] > nxtV) {
                        dp[nxtY][nxtX][i] = nxtV;
                        q.add(new MyCar(nxtY, nxtX, nxtV, i));
                    }
                }
            }
        }
    }
    
    private static boolean isOk(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < n;
    }
}

class MyCar {
    int y;
    int x;
    int value;
    int direct;
    
    public MyCar(int y, int x, int value, int direct) {
        this.y = y;
        this.x = x;
        this.value = value;
        this.direct = direct;
    }
}

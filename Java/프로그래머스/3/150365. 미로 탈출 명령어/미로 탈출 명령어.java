class Solution {
    private static int[] mX = {1, 0, 0, -1};
    private static int[] mY = {0, -1, 1, 0};
    private static String[] cmd = {"d", "l", "r", "u"};
    private static int N, M, C, R, K;
    private static String answer = null;
    
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        C = c;
        R = r;
        K = k;
        
        // 필요 거리 계산 
        int need = Math.abs(c - y) + Math.abs(r - x);
        // 초과한 거리 왕복해야 제자리임 그래서 짝수여야함
        if (need > k || (k - need) % 2 != 0) {
            return "impossible";
        }
        
        dfs(y, x, "", 0);
        if (answer == null) {
            return "impossible";
        }
        return answer;
    }
    
    private static boolean isOk(int y, int x) {
        return 1 <= y && y <= M && 1 <= x && x <= N;
    }
    
    private static void dfs(int y, int x, String s, int depth) {
        if (answer != null) return;
        if (K == depth) {
            if (y == C && x == R) {
                answer = s;
            }
            return;
        }
        
        int need = Math.abs(C - y) + Math.abs(R - x);
        int cnt = K - depth;
        if (need > cnt || (cnt - need) % 2 != 0) {
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            int nxtY = y + mY[i];
            int nxtX = x + mX[i];
            if (isOk(nxtY, nxtX)) {
                dfs(nxtY, nxtX, s + cmd[i], depth + 1);
            }
        }
    }
}
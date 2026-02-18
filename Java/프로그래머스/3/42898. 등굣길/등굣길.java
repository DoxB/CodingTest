class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n + 1][m + 1];
        int mod = 1000000007;
        for (int i = 1; i <= n; i++) {
            if (!isOk(i, 1, puddles)) break;
            dp[i][1] = 1;
        }
        for (int j = 1; j <= m; j++) {
            if (!isOk(1, j, puddles)) break;
            dp[1][j] = 1;
        }
        
        for (int i = 2; i <= n; i++) {
            for (int j = 2; j <= m; j++) {
                if (isOk(i, j, puddles)) {
                    dp[i][j] = (dp[i - 1][j] % mod + dp[i][j - 1] % mod) % mod;
                }
            }
        }
        
        int answer = dp[n][m];
        return answer;
    }
    
    private static boolean isOk(int y, int x, int[][] p) {
        int n = p.length;
        if (n == 0) return true;
        
        for (int i = 0; i < n; i++) {
            if (x == p[i][0] && y == p[i][1]) return false;
        }
        return true;
    }
}
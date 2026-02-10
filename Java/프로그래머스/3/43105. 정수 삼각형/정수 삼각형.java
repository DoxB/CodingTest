class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j];
            }
        }
        
        for (int i = n - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                dp[i - 1][j] += Math.max(dp[i][j], dp[i][j + 1]);
            }
        }
        
        answer = dp[0][0];
        
        return answer;
    }
}
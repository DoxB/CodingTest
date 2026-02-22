class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        int n = sequence.length;
        long[][] dp = new long[n][2];
        
        dp[0][0] = sequence[0];
        dp[0][1] = -sequence[0];
            
        
        for (int i = 1; i < n; i++) {
            if (i % 2 == 1) {
                dp[i][0] = -sequence[i];
                dp[i][1] = sequence[i];
            } else {
                dp[i][0] = sequence[i];
                dp[i][1] = -sequence[i];
            }
            
            
            dp[i][0] = Math.max(dp[i][0], dp[i][0] + dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i][1], dp[i][1] + dp[i - 1][1]);
        }

        for (int i = 0; i < n; i++) {
            if (answer < dp[i][0]) {
                answer = dp[i][0];
            }
            if (answer < dp[i][1]) {
                answer = dp[i][1];
            }
        }
        
        
        return answer;
    }
}
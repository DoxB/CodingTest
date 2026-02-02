class Solution {
    public int solution(int n) {
        int answer = 0;
        // dp[n] = dp[n - 1] + dp[n - 2]
        // 2칸 전 세로로 두는 경우의 수는 n - 1에서 카운트 됨
        int[] dp = new int[60001];
        int mod = 1000000007;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] % mod + dp[i - 2] % mod) % mod;
        }
        
        answer = dp[n];
        return answer;
    }
}
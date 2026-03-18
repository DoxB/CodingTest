class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        // "만들어지는 금액을 기준"으로 DP
        long[] dp = new long[n + 1];
        dp[0] = 1;
        
        for (int i = 0; i < money.length; i++) {
            int value = money[i];
            for (int j = value; j <= n; j++) {
                //money을 사용해서 j를 만드는 방법 = money을 하나 쓰고 (j - money)을 만드는 방법
                dp[j] += dp[j - value];
                dp[j] %= 1000000007;
            }
        }
        
        answer = (int)dp[n];
        
        return answer;
    }
}
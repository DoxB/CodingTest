import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int mod = 1000000000;

        // 숫자 붙이기 dp
        // 1~8: 2개, 0, 9: 1개
        // dp[숫자길이n][끝번호h] = dp[n - 1][h - 1] + dp[n - 1][h + 1]
        long[][] dp = new long[101][10];
        for (int i = 1; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= n; i++) {
            dp[i][0] = dp[i - 1][1];
            dp[i][9] = dp[i - 1][8];
            for (int j = 1; j <= 8; j++) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % mod;
            }
        }

        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans = (ans + dp[n][i]) % mod;
        }

        System.out.println(ans);
    }
}
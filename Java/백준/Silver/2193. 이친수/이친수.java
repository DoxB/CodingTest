import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[91];
        // n 자리 - 맨 앞 두자리 1,0 고정
        // n - 2 : 1일때, n - 2 경우의 수 적용 가능
        // n - 2 : 0일때, 앞자리 0일때 카운팅 된 것 <- n - 1 경우에서 1 때면 됨
        // 1: 1 -> 1
        // 2: 10 -> 1
        // 3: 100, 101 -> 2
        // 4: 1000, 1001, 1010 -> 3
        // 5: 10000, 10001, 10010, 10100, 10101 ->5
        // 6: 100000, 100001, 100010, 100100, 100101, 101000, 101001, 101010 -> 8
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }

        System.out.println(dp[n]);
    }
}
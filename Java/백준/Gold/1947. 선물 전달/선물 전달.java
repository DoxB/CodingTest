import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long mod = 1000000000;
        long[] dp = new long[1000001];

        // dp[n] -> n명일 때 한개씩 선물을 나누는 경우의 수
        // 케이스 하나 생각 A -> B 전달한다.
        // 나올 수 있는 케이스 (양방향 교환, 다른 한명에게 교환)
        // 양방향 교환하면 남는 경우의 수 n - 2, 다른 한명에게 교환 n - 2
        // 해당 케이스를 자신 말고 나머지에 각각 적용 n - 1
        // dp[1] = 0, dp[2] = 1, dp[3] = 2, dp[4] = 9


        dp[1] = 0;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]) % mod;
        }

        System.out.println(dp[n]);
    }
}
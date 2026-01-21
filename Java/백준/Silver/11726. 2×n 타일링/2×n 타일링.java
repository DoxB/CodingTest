import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int mod = 10007;

        // 이전 타일 경우의 수에서 추가로 붙이는 방식으로 DP
        // 1: 1
        // 2: 2
        // 3: 3
        // n -> n - 1까지 채워져 있는 경우 세로로 한칸 붙이기
        // n -> n - 2까지 채워져 있는 경우 가로 붙이기 (세로는 한칸 붙일때 카운팅 됨)
        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % mod;
        }

        System.out.println(dp[n]);
    }
}
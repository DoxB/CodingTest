import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] time = new int[n + 1];
        int[] income = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            income[i] = Integer.parseInt(st.nextToken());
        }

        // n일차 기준 시작했을 때 벌 수 있는 최대 돈
        int[] dp = new int[n + 2];
        for (int i = n; i >= 1; i--) {
            if (time[i] + i > n + 1) {
                dp[i] = dp[i + 1];
            } else {
                dp[i] = Math.max(dp[i + 1], income[i] + dp[time[i] + i]);
            }
        }

        System.out.println(dp[1]);
    }
}
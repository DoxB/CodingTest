import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long max = 0;
        long[][] dp = new long[n][m];
        for (int i = 0; i < n; i++) {
            String mline = br.readLine();
            for (int j = 0; j < m; j++) {
                dp[i][j] = Long.parseLong(String.valueOf(mline.charAt(j)));
                if (dp[i][j] == 1 && i > 0 && j > 0) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + dp[i][j];
                }
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }

        System.out.println(max * max);
    }
}